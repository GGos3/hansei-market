package xyz.ggos3.hanseimarket.dto.user.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private final String name;
    private final String studentCode;
    private final String phoneNumber;

    public UserCreateRequest(String name, String studentCode, String phoneNumber) {
        this.name = name;
        this.studentCode = studentCode;
        this.phoneNumber = phoneNumber;
    }
}
