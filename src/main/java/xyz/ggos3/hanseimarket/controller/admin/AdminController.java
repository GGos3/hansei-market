package xyz.ggos3.hanseimarket.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.dto.ApiResponse;
import xyz.ggos3.hanseimarket.dto.admin.request.GetUserInfoRequest;
import xyz.ggos3.hanseimarket.security.annotation.AdminAuthorize;
import xyz.ggos3.hanseimarket.service.admin.AdminService;

@Tag(name = "관리자용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@AdminAuthorize
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "회원 조회", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(content = @Content(schema = @Schema(implementation = User.class)))})
    @PostMapping("/user-info")
    public ApiResponse getUserInfo(@RequestBody GetUserInfoRequest request) {
        return ApiResponse.success(adminService.findUser(request.userId()));
    }
}
