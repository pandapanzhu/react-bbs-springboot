package com.test.luntan.biz;

import com.test.luntan.model.LoginUser;
import com.test.luntan.repository.LoginRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginBiz {
    @Resource
    private LoginRepository loginRepository;

    /**
     * 登录操作
     * @param userName
     * @param password
     * @return
     */
   public LoginUser getLoginUserByNameAndPassword(String userName, String password){
      return loginRepository.findOneByUsernameAndPassword(userName,password);//待修改，需加密密码
    }

    /**
     * 判断emails是否被注册
     * @param email
     * @return
     */
    public LoginUser getByEmail(String email){
       return loginRepository.findOneByEmail(email);
    }

    /**
     * 判断用户名是否被注册
     * @param username
     * @return
     */
    public  LoginUser getByUserName(String username){
        return loginRepository.findOneByUsername(username);
    }


    /**
     * 注册用户
     * @param user
     * @return
     */
    public LoginUser regseter(LoginUser user) {
        return  loginRepository.save(user);
    }

    public LoginUser save(LoginUser user) {
        return loginRepository.save(user);
    }
}
