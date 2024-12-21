package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//해당 Jpa레파지토리는 User테이블을 관리하는 레파지임 그리고 프라이머리키는 숫자야
//DAO라고 생각하면 됌
//자동으로 빈으로 등록이 됌 
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{ 
	//네이밍컬임 실제로는 select * from user WHERE username = 1?; 이렇게 동작함 
	Optional<User> findByUsername(String username);
}

//User findByUsernameAndPassword(String username, String password);
//JPA Naming 전략
//Select*From user Where username = ?1 And password = ?2이게 자동으로 실행됌
// 물음표에 username이랑 password가 딱딱 들어감 이게 네이밍전략


//네이티브쿼리	
//@Query(value="Select*From user Where username = ?1 And password = ?2", nativeQuery = true)
// User login(String username, String password);	
