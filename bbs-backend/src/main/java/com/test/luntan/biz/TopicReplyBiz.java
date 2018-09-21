package com.test.luntan.biz;

import com.test.luntan.model.TopicReply;
import com.test.luntan.repository.TopicReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Service
public class TopicReplyBiz {

    @Resource
    private TopicReplyRepository topicReplyRepository;

    /**
     * 根据板块拿到主题，每页15条
     * @param topicId
     * @return
     */
    public Page<TopicReply> getReplyByTopic(int topicId, Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum, 2, Sort.Direction.ASC, "replyDate");

        Page<TopicReply> page = topicReplyRepository.findAll(new Specification<TopicReply>() {
            @Override
            public Predicate toPredicate(Root<TopicReply> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("topicId").as(String.class),topicId);
                return criteriaBuilder.and(predicate);
            }
        },pageable);
        return  page;
    }
}
