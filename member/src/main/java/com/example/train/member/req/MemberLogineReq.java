package com.example.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author Ali
 * @date 2023-07-24 08:19
 */

public class MemberLogineReq {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
    private String mobile;

    @NotBlank(message = "短信验证码不能为空")
    private String code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MemberLogineReq{" +
                "mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
