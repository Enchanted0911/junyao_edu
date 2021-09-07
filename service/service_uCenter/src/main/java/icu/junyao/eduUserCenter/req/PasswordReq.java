package icu.junyao.eduUserCenter.req;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author wu
 */
@Data
public class PasswordReq {
    private String id;
    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!")
    private String oldPassword;
    @Pattern(regexp = "^[A-Za-z0-9._~!@#$^&*]{6,20}$", message = "密码格式错误!")
    private String newPassword;
}
