package com.test.luntan.repository;

import com.test.luntan.model.Plate;
import com.test.luntan.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录模块的数据层面
 */
@Repository
public interface PlateRepository extends JpaRepository<Plate, Integer>, JpaSpecificationExecutor<Plate> {

}
