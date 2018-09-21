package com.test.luntan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.luntan.model.Topic;
import com.test.luntan.model.TopicReply;
import com.test.luntan.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class TopicController {

    @Resource
    private TopicService topicService;

    /**
     * 根据板块拿到主题--需进行分页
     * @return
     */
    @RequestMapping("getTopicByPlate")
    public Page<Topic> getTopicByPlate(
            @RequestBody Map<String,Object> reqMap
//            @RequestParam(value = "plateId",required = false)String plateId,
//            @RequestParam(value = "name",required = false) String name,
//            @RequestParam(value = "pageNum",required = false)String pageNum
    ){
        //string zhuan Int
//        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(reqMap));
        int num =0;
        if(!StringUtils.isEmpty(reqMap.get("pageNum")))
            num=  Integer.parseInt(reqMap.get("pageNum").toString());
        //根据Plate的Id获得主题
        return topicService.getTopicByPlateId(reqMap.get("plateId").toString(),reqMap.get("name").toString(),num);
    }

    /**
     * 点击进入一个帖子
     */
    @GetMapping("getTopic/{id}/{replyPageNum}")
    public String  getTopicById(@PathVariable("id") int id, @PathVariable int replyPageNum){
        JSONObject jsonObject = topicService.getTopicById(id,replyPageNum);
        if(jsonObject.get("data") != null ){
            jsonObject.put("msg","success");
        }else{
            jsonObject.put("msg","error");
        }
        return jsonObject.toString();
    }

    /**
     * 新建帖子
     * @param topic
     */
    @PostMapping("addTopic")
    public String addTopic(@RequestBody Topic topic ){
        JSONObject jsonObject = new JSONObject();
        topicService.addTopic(topic);
        jsonObject.put("msg","success");
        return jsonObject.toJSONString();
    }

    @PostMapping("getReply")
    public  String getTopicReply(){
        JSONObject jsonObject =new JSONObject();

        return jsonObject.toJSONString();
    }

}
