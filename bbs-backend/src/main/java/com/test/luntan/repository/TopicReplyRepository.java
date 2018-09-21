package com.test.luntan.repository;

import com.test.luntan.model.Topic;
import com.test.luntan.model.TopicReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录模块的数据层面
 */
@Repository
public interface TopicReplyRepository extends JpaRepository<TopicReply, Integer>, JpaSpecificationExecutor<TopicReply> {

    TopicReply findOneById(String id);

}
