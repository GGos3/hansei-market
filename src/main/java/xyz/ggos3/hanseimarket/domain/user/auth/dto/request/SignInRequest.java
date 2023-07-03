package xyz.ggos3.hanseimarket.domain.user.auth.dto.request;

public record SignInRequest(
        String id,
        String password
) {
}