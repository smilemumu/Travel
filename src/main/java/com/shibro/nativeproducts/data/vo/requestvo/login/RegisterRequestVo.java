package com.shibro.nativeproducts.data.vo.requestvo.login;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import lombok.Data;

@Data
public class RegisterRequestVo extends BaseRequestVo {
    private String userName;
    private String password;
}
