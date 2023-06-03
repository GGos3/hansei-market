package xyz.ggos3.hanseimarket.vo.user;

import lombok.Getter;

@Getter
public class UserCreateVO {
    private final String name;
    private final String studentCode;
    private final String phoneNumber;

    public UserCreateVO(String name, String studentCode, String phoneNumber) {
        this.name = name;
        this.studentCode = studentCode;
        this.phoneNumber = phoneNumber;
    }
}
