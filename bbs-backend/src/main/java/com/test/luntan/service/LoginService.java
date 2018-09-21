package com.test.luntan.service;

import com.test.luntan.model.LoginUser;

import java.util.HashMap;

/**
 * 登录接口
 *
 */
public interface LoginService {
    LoginUser getLoginUserByNameAndPassword(String userName,String password);

    HashMap<String,Object> regester(LoginUser user);

    HashMap<String,Object>  findPassword(String email);

    HashMap<String, Object> resetPasswordByEamil(String email, String password, String rePassword);
}
