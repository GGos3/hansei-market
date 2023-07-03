package xyz.ggos3.hanseimarket.domain.user.auth.dto.request;

public record SignUpRequest(
        String userId,
        String userPassword,
        String name,
        String studentCode,
        String phoneNumber
) {
}
