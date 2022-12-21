package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// 자동으로
// BEAN으로 등록이 되기 때문에 @Repositoy 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
