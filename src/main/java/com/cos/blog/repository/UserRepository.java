package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로
// BEAN으로 등록이 되기 때문에 @Repositoy 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}

// JPA naming 전략

// 두가지 방식으로 쿼리를 실행할 수 있다.

//첫번째
// Select * from user where username=username(첫번째 param) and password=password(두번째 param))
//    User findByUsernameAndPassword(String username, String password);

//두번째
//    @Query(value ="Select * from user where username=? and password=?",nativeQuery = true)
//    User login(String username, String password);