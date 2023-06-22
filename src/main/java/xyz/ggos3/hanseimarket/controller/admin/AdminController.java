package xyz.ggos3.hanseimarket.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.admin.request.GetUserInfoRequest;
import xyz.ggos3.hanseimarket.security.annotation.AdminAuthorize;
import xyz.ggos3.hanseimarket.service.user.UserService;

@Tag(name = "관리자용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@AdminAuthorize
public class AdminController {
    private UserService userService;

    @Operation(summary = "회원 정보조회")
    public ApiResponse getUserInfo(@RequestBody GetUserInfoRequest request) {
        return ApiResponse.success(userService.findUser(request.userId()));
    }
}
