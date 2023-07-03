package xyz.ggos3.hanseimarket.domain.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userPassword;

    private String name;
    private String studentCode;
    private String phoneNumber;
    private int tradeCount = 0;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.enable;

    @CreationTimestamp
    private Timestamp createdDate;

    public User() {
    }

    public User(String userId, String userPassword, String name, String studentCode, String phoneNumber) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.studentCode = studentCode;
        this.phoneNumber = phoneNumber;
    }

    public void disable() {
        this.status = UserStatus.disable;
    }

    public void enable() {
        this.status = UserStatus.enable;
    }

    public void increaseTradeCount() {
        ++this.tradeCount;
    }

    public SchoolClass getStudentClass() {
        char getClassCode = this.studentCode.charAt(0);

        return switch (getClassCode) {
            case 'H' -> SchoolClass.해킹보안과;
            case 'N' -> SchoolClass.네트워크보안과;
            case 'G' -> SchoolClass.게임과;
            case 'C' ->  SchoolClass.클라우드보안과;
            case 'M' -> SchoolClass.메타버스게임과;
            default -> throw new IllegalStateException("학과 정보를 찾을 수 없습니다. 학과코드: " + getClassCode);
        };
    }
}
