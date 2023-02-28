package com.test.luntan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.luntan.model.LoginUser;
import com.test.luntan.service.LoginService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆模块
 */

@RestController
public class LoginConroller {

    @Resource
    private LoginService loginService;

    /**
     * 登陆流程
     * @return
     */
    @RequestMapping("loginUser")
    public String login(@RequestBody LoginUser user){
//        @RequestParam(value = "username",required = false)String username,
//        @RequestParam(value = "password",required = false)String password
        JSONObject jsonObject = new JSONObject();
        LoginUser user1 = loginService.getLoginUserByNameAndPassword(user.getUsername(),user.getPassword());
        if(user1!= null){
            jsonObject.put("msg","success");
            jsonObject.put("login",user1);
        }else{
            jsonObject.put("msg","error");
        }
        return jsonObject.toJSONString();
    }

    /**
     * 注册功能
     * @param user
     * @return
     */
    @RequestMapping("register")
    public  HashMap<String,Object>  register(@RequestBody  LoginUser user){
        HashMap<String,Object> map = loginService.regester(user);
        if(map.containsKey("msg") && "success".equals(map.get("msg"))){
            map.remove("login");
            map.put("login",user);
        }
        return  map;
    }

    /**
     * 找回密码功能
     * 根据email 找回对应的账号
     */
    @RequestMapping("findPassword")
    public String findPassword(@RequestBody Map<String,String> reqMap ){
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isEmpty(reqMap.get("email"))){
            jsonObject.put("msg","邮件不能为空");
            return jsonObject.toJSONString();
        }
        HashMap<String,Object> map = loginService.findPassword(reqMap.get("email"));
        return   JSONObject.parse(JSON.toJSONString(map)).toString();
    }

    @RequestMapping("resetPasswordByEamil")
    public HashMap<String,Object> resetPasswordByEamil(@RequestBody Map<String,String> map
//            @Param("email")String email
//            ,@Param("email")String password
//            ,@Param("email")String rePassword
    ){
       return loginService.resetPasswordByEamil(map.get("email"),map.get("password"),map.get("rePassword"));
    }




    @GetMapping("isLogin")
    public String isLogin(HttpServletRequest request){
        JSONObject jsonObject =new JSONObject();
        Object user =  request.getSession().getAttribute("login");
        if(null !=user){
            jsonObject.put("msg","success");
            jsonObject.put("data",user);
        }else {
            jsonObject.put("msg","error");
        }
        return  jsonObject.toJSONString();
    }

    /**
     * 退出，舍弃
     * @param session
     */
    @GetMapping("logout")
    public void logOut(HttpSession session){
        session.removeAttribute("login");
    }


}
