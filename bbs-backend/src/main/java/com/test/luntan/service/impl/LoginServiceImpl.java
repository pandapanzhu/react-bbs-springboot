package com.test.luntan.service.impl;

import com.test.luntan.biz.LoginBiz;
import com.test.luntan.model.LoginUser;
import com.test.luntan.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;

import static com.test.luntan.util.EmailUtil.sendFindPasswordEmail;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginBiz loginBiz;
    @Override
    public LoginUser getLoginUserByNameAndPassword(String userName,String password) {
        return loginBiz.getLoginUserByNameAndPassword(userName,password);
    }

    @Override
    public HashMap<String,Object> regester(LoginUser user) {
        HashMap<String,Object> map = new HashMap<>();
        //先判断用户名是否注册
        if(StringUtils.isEmpty(user.getUsername())){
            map.put("msg","username can not be null");
            return map;
        }
        if(StringUtils.isEmpty(user.getEmail())){
            map.put("msg","email can not be null");
            return map;
        }
        if(StringUtils.isEmpty(user.getPassword())){
            map.put("msg","Password can not be null");
            return map;
        }

        LoginUser name= loginBiz.getByUserName(user.getUsername());
        if(name != null){
            map.put("msg","username has exist");
            return map;
        }

        LoginUser email = loginBiz.getByEmail(user.getEmail());
        if(email != null){
            map.put("msg","email has exist");
            return map;
        }

        LoginUser saveUser =  loginBiz.save(user);
        if(saveUser != null){
            map.put("msg","success");
            return map;
        }

        return map;
    }

    @Override
    public HashMap<String,Object>  findPassword(String email) {
        HashMap<String,Object> map = new HashMap<>();
        //先查看这个Email是否存在
        LoginUser loginUser = loginBiz.getByEmail(email);
        if(null == loginUser){
            map.put("msg","email does noe exist");
        }else {//存在
            //根据Email发送邮件
             sendFindPasswordEmail(email,loginUser.getUsername());
            map.put("msg","success");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> resetPasswordByEamil(String email, String password, String rePassword) {
        HashMap<String,Object> map = new HashMap<>();
        if(!password.equals(rePassword)){//两个密码不统一
            map.put("msg","the password must be unified");
        }
        LoginUser loginUser = loginBiz.getByEmail(email);
        if(loginUser == null){
            map.put("msg","email does noe exist");
        }else{
            loginUser.setPassword(password);
            loginBiz.save(loginUser);
            map.put("msg","success");
        }
        return  map;
    }
}
