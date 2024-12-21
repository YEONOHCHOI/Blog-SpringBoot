package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


//스프링이 컴포넌트 스캔을 통해서 빈에 등록해줌(IoC(메모리에 띄워줌)
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional //하나의 트랜잭션으로 묶기
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //원문
		String encPassword = encoder.encode(rawPassword);//해쉬로 바뀜
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
}
