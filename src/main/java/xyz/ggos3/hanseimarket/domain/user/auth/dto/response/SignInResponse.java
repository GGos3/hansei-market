package xyz.ggos3.hanseimarket.domain.user.auth.dto.response;

public record SignInResponse(
        String userId,
        String token
) {
}