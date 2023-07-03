package xyz.ggos3.hanseimarket.domain.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.user.domain.User;
import xyz.ggos3.hanseimarket.common.dto.ApiResponse;
import xyz.ggos3.hanseimarket.domain.admin.dto.request.GetUserInfoRequest;
import xyz.ggos3.hanseimarket.common.security.annotation.AdminAuthorize;
import xyz.ggos3.hanseimarket.domain.admin.service.AdminService;

@Tag(name = "관리자용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@AdminAuthorize
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "회원 조회")
    @PostMapping("/user-info")
    public ApiResponse<User> getUserInfo(@RequestBody GetUserInfoRequest request) {
        return ApiResponse.success(adminService.findUser(request.userId()));
    }
}
