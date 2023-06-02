package xyz.ggos3.hanseimarket.domain.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String studentCode;
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    public User() {
    }

    public User(String name, String studentCode, String phoneNumber) {
        this.name = name;
        this.studentCode = studentCode.toUpperCase();
        this.phoneNumber = phoneNumber;
    }

    public Class getStudentClass() {
        char getClassCode = this.studentCode.charAt(0);

        return switch (getClassCode) {
            case 'H' -> Class.해킹보안과;
            case 'N' -> Class.네트워크보안과;
            case 'G' -> Class.게임과;
            case 'C' ->  Class.클라우드보안과;
            case 'M' -> Class.메타버스게임과;
            default -> throw new IllegalStateException("학과 정보를 찾을 수 없습니다. 학과코드: " + getClassCode);
        };
    }
}
