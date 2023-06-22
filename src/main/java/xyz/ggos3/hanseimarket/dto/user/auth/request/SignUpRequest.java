package xyz.ggos3.hanseimarket.dto.user.auth.request;

public record SignUpRequest(
        String userId,
        String userPassword,
        String name,
        String studentCode,
        String phoneNumber
) {
}
