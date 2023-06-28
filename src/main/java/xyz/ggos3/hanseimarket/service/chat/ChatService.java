package xyz.ggos3.hanseimarket.service.chat;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.chat.ChatRoom;
import xyz.ggos3.hanseimarket.domain.chat.repository.ChatRoomRepository;
import xyz.ggos3.hanseimarket.domain.item.Item;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUserRepository;
import xyz.ggos3.hanseimarket.dto.chat.reqeuest.CreateRoomRequest;
import xyz.ggos3.hanseimarket.dto.chat.response.ChatMessageResponse;
import xyz.ggos3.hanseimarket.dto.chat.response.ChatRoomResponse;
import xyz.ggos3.hanseimarket.service.item.ItemService;
import xyz.ggos3.hanseimarket.service.user.auth.AuthUserService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final RedisMessageListenerContainer redisMessageListener;
    private final AuthUserService authUserService;
    private final ItemService itemService;
    private final RedisSubscriber redisSubscriber;
    private final ChatRoomRepository chatRoomRepository;
    private final AuthUserRepository authUserRepository;

    @Transactional
    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    @Transactional
    public ChatRoom findRoomByUuid(UUID uuid) {
        return chatRoomRepository.findById(uuid).orElseThrow(IllegalAccessError::new);
    }

    @Transactional
    public ChatRoomResponse getRoomInfo(UUID roomId) {
        return new ChatRoomResponse(findRoomByUuid(roomId), getRoomMessage(roomId));
    }

    @Transactional
    public List<ChatRoomResponse> getRoomByUser(String userId) {
        AuthUser user = authUserService.findByUuid(userId);
        return Optional.of(Stream.concat(
                chatRoomRepository.findChatRoomsByStore(user).stream(),
                chatRoomRepository.findChatRoomsByCustomer(user).stream())
                .map(chatRoom ->
                        new ChatRoomResponse(
                                chatRoom, getRoomMessage(chatRoom.getId())))
                        .distinct()
                        .collect(Collectors.toList()))
                .orElseThrow(IllegalAccessError::new);
    }


    @Transactional
    public List<ChatMessageResponse> getRoomMessage(UUID rooId) {
        return findRoomByUuid(rooId).getChatMessages().stream()
                .map(ChatMessageResponse::new)
                .toList();
    }

    @Transactional
    public ChatRoomResponse createChatRoom(String uuid, CreateRoomRequest request) {
        Item item = itemService.findItemById(request.itemId());
        AuthUser customer = authUserService.findByUuid(uuid);
        AuthUser store = authUserService.findByUserId(item.getUser().getUserId());
        ChatRoom chatRoom = ChatRoom.builder()
                .customer(customer)
                .store(store)
                .item(item)
                .build();

        chatRoomRepository.save(chatRoom);
        enterChatRoom(chatRoom.getId());

        return new ChatRoomResponse(chatRoom, getRoomMessage(chatRoom.getId()));
    }

    public void enterChatRoom(UUID roomId) {
        ChannelTopic topic = getTopic(roomId);
        redisMessageListener.addMessageListener(redisSubscriber, topic);
    }

    @Transactional
    public ChannelTopic getTopic(UUID roomId) {
        return new ChannelTopic(findRoomByUuid(roomId).getId().toString());
    }

    public void deleteById(ChatRoom chatRoom) {
        chatRoomRepository.delete(chatRoom);
    }
}
