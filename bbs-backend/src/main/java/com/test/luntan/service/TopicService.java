package com.test.luntan.service;

import com.alibaba.fastjson.JSONObject;
import com.test.luntan.model.Topic;
import com.test.luntan.model.TopicReply;
import org.springframework.data.domain.Page;

public interface TopicService {
    JSONObject getTopicById(int id,int replyPageNum);

    Topic addTopic(Topic topic);

    Page<Topic> getTopicByPlateId(String plateId, String topicName, int pageNum);
}
