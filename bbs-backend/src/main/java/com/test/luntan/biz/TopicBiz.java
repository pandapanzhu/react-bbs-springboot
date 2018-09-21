package com.test.luntan.biz;

import com.alibaba.fastjson.JSONObject;
import com.test.luntan.model.Topic;
import com.test.luntan.model.TopicReply;
import com.test.luntan.repository.TopicReplyRepository;
import com.test.luntan.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicBiz {

    @Resource
    private TopicRepository topicRepository;

    @Resource
    private TopicReplyRepository replyRepository;

    /**
     * 根据板块拿到主题，每页15条
     * @param plateId
     * @return
     */
    public Page<Topic> getTopicForPlate(String plateId,String topicName,Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum, 2, Sort.Direction.ASC, "publicDate");

        Page<Topic> page = topicRepository.findAll(new Specification<Topic>() {
            @Override
            public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(StringUtils.isEmpty(topicName)){//查询条件为空，
                    list.add(criteriaBuilder.like(root.get("topicName").as(String.class), "%" + topicName + "%"));
                }
                list.add(criteriaBuilder.equal(root.get("plateId").as(String.class),plateId));
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return  page;
    }


    public JSONObject findOneById(int id) {
        JSONObject jsonObject = new JSONObject();
        //拿到topic
        Topic topic = topicRepository.findOneById(id);
        jsonObject.put("data",topic);

        return jsonObject;
    }

    public Topic addTopic(Topic topic) {
        return topicRepository.save(topic);
    }
}
