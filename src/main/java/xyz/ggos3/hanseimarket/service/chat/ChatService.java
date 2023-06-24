package xyz.ggos3.hanseimarket.service.chat;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.chat.ChatRoom;
import xyz.ggos3.hanseimarket.domain.chat.repository.ChatRoomRepository;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisSubscriber redisSubscriber;
    private final ChatRoomRepository chatRoomRepository;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    private Map<String, ChannelTopic> topics;
    private HashOperations<String, String, String> hashOpsEnterInfo;
    public static final String ENTER_INFO = "ENTER_INFO";
    private static final String CHAT_ROOMS = "CHAT_ROOM";

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        hashOpsEnterInfo=redisTemplate.opsForHash();

        topics = new HashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomByUuid(UUID uuid) {
        return chatRoomRepository.findById(uuid).orElseThrow();
    }

    public ChatRoom createChatRoom(Item item, AuthUser customer, AuthUser store) {
        ChatRoom chatRoom = ChatRoom.builder().customer(customer).store(store).item(item).build();

        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getId().toString(), chatRoom);
        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    public void enterChatRoom(String roomId) {
        ChannelTopic topic = Optional
                .of(topics.get(roomId))
                .orElse(new ChannelTopic(roomId));

        redisMessageListener.addMessageListener(redisSubscriber, topic);
        topics.put(roomId, topic);
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

    public List<ChatRoom> getCustomerEnterRooms(AuthUser customer){
        return chatRoomRepository.findChatRoomsByCustomer(customer);
    }

    public List<ChatRoom> getStoreEnterRooms(AuthUser store){
        return chatRoomRepository.findChatRoomsByStore(store);
    }

    public void deleteById(ChatRoom chatRoom){
        chatRoomRepository.delete(chatRoom);
    }

    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    public void setUserEnterInfo(String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    public String getUserEnterRoomId(String sessionId) {
        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    public void removeUserEnterInfo(String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
    }

}
