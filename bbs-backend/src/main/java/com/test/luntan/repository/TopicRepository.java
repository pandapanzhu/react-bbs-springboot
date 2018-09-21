package com.test.luntan.repository;

import com.test.luntan.model.LoginUser;
import com.test.luntan.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录模块的数据层面
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>, JpaSpecificationExecutor<Topic> {


    Topic findOneById(int id);

    List<Topic> findByPlateId(String plateId);

}
