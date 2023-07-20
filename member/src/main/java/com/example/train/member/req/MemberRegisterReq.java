package com.example.train.member.req;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Ali
 * @date 2023-07-20 07:39
 */

public class MemberRegisterReq {

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
