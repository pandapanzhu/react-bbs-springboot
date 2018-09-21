package com.test.luntan.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.luntan.service.PlateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 首页交互界面
 */
@RestController
public class PlateController {

    @Resource
    private PlateService plateService;

    /**
     * 拿到板块列表
     */
    @RequestMapping("/getPlate")
    public String getPlate (){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",plateService.getAllPlate());
        //获得各个板块的列表
        return jsonObject.toJSONString();
    }




}
