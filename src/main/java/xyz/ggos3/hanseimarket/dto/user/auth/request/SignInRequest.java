package xyz.ggos3.hanseimarket.dto.user.auth.request;

public record SignInRequest(
        String id,
        String password
) {
}