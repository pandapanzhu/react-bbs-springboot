package com.test.luntan.repository;

import com.test.luntan.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 登录模块的数据层面
 */
@Repository
public interface LoginRepository extends JpaRepository<LoginUser, Integer>, JpaSpecificationExecutor<LoginUser> {


    LoginUser findOneByUsernameAndPassword(String username,String password);

    LoginUser findOneByEmail(String email);

    LoginUser findOneByUsername(String username);
}
