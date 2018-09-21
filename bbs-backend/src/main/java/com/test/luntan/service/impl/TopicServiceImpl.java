package com.test.luntan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.test.luntan.biz.TopicBiz;
import com.test.luntan.biz.TopicReplyBiz;
import com.test.luntan.model.Topic;
import com.test.luntan.model.TopicReply;
import com.test.luntan.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicBiz topicBiz;

    @Resource
    private TopicReplyBiz topicReplyBiz;

    @Override
    public JSONObject getTopicById(int id,int replyPageNum) {
        JSONObject jsonObject = topicBiz.findOneById(id);
        //根绝topic的ID.拿到对应的回复,需进行分页
        Page<TopicReply> replies = topicReplyBiz.getReplyByTopic(id,replyPageNum-1);
        jsonObject.put("reply",replies);
        return   jsonObject;
    }

    @Override
    public Topic addTopic(Topic topic) {
        return topicBiz.addTopic(topic);
    }

    @Override
    public Page<Topic> getTopicByPlateId(String plateId, String topicName, int pageNum) {
        return topicBiz.getTopicForPlate(plateId,topicName,pageNum);
    }
}
