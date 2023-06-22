package xyz.ggos3.hanseimarket.dto.user.auth.response;

public record SignInResponse(
        String userId,
        String token
) {
}