package xyz.ggos3.hanseimarket.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ggos3.hanseimarket.domain.chat.ChatRoom;
import xyz.ggos3.hanseimarket.domain.user.auth.AuthUser;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    List<ChatRoom> findChatRoomsByCustomer(AuthUser customer);

    List<ChatRoom> findChatRoomsByStore(AuthUser store);
}
