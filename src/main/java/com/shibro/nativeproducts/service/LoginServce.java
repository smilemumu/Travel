package com.shibro.nativeproducts.service;

import com.alibaba.fastjson.JSON;
import com.shibro.nativeproducts.data.entity.UserInfo;
import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.requestvo.login.*;
import com.shibro.nativeproducts.persistence.UserInfoMapper;
import com.shibro.nativeproducts.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Service
public class LoginServce {


    private static final Logger LOG = LoggerFactory.getLogger(LoginServce.class);

    @Resource
    private UserInfoMapper userInfoMapper;

    public BaseResponseVo updateUser(UpdateUserRequestVo requestVo) {
        return null;
    }

    public BaseResponseVo deleteUser(DeleteUserRequestVo requestVo) {
        return null;
    }

    public BaseResponseVo registerUser(RegisterRequestVo requestVo) {
        UserInfo userInfo = userInfoMapper.isExistsUserName(JSON.parseObject(JSON.toJSONString(requestVo),UserInfo.class));
        if(Objects.isNull(userInfo)){
            try{
                userInfoMapper.insertSelective(JSON.parseObject(JSON.toJSONString(requestVo),UserInfo.class));
                return BaseResponseVo.successResponseVo();
            }catch (Exception e){
                LOG.error("用户注册失败{}",e);
                return BaseResponseVo.failResponseVo(ErrorCodeEnum.SYSTEM_ERROR);
            }
        }else{
            return BaseResponseVo.failResponseVo(ErrorCodeEnum.REGISTER_REPEAT_NAME);
        }
    }

    public BaseResponseVo logout(LogoutRequestVo requestVo,HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ses.invalidate();
        return BaseResponseVo.successResponseVo();
    }

    public BaseResponseVo login(LoginRequestVo requestVo, HttpServletRequest request) {
        try{
            //验证token
            String sessionToken = (String) request.getSession().getAttribute("token");
            if(StringUtils.isEmpty(sessionToken)){
                UserInfo userInfo= userInfoMapper.selectByUserInfo(JSON.parseObject(JSON.toJSONString(requestVo),UserInfo.class));
                if(Objects.nonNull(userInfo)){
                    sessionToken = TokenUtil.generateToken(userInfo.getUserName());
                    request.getSession().setAttribute("token",sessionToken);
                    request.getSession().setAttribute("userName",userInfo.getUserName());
                    return BaseResponseVo.successResponseVo(new LoginRespoonseVo(sessionToken));
                }else{
                    return BaseResponseVo.failResponseVo(ErrorCodeEnum.LOGIN_FAIL);
                }
            }else{
                LOG.info("重复登录");
                String generateToken = TokenUtil.generateToken((String) request.getSession().getAttribute("userName"));
                return BaseResponseVo.successResponseVo(new LoginRespoonseVo(generateToken));
            }
        }catch (Exception e){
            LOG.error("登陆异常{}",e);
            return BaseResponseVo.failResponseVo(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }


}
