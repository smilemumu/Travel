package com.shibro.travel.data.vo.requestvo.login;

import com.shibro.travel.data.vo.BaseRequestVo;
import lombok.Data;

@Data
public class RegisterRequestVo extends BaseRequestVo {
    private String userName;
    private String password;
}
