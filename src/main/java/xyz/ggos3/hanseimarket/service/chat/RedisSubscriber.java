package xyz.ggos3.hanseimarket.service.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.chat.ChatMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messageTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
            messageTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getChatRoom().getId(), roomMessage);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
