package xyz.ggos3.hanseimarket.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ggos3.hanseimarket.domain.chat.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
