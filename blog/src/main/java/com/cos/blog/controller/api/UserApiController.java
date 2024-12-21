package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController //데이터만 리턴해줄 것이기 떄문에 restcontroller쓸거임
public class UserApiController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		//실제로 DB에 insert를 하고 아래헤 리턴이 되면 됌 
		System.out.println("UserApiController: save 호출됌");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //status가 200이면 통신이 잘 됐다는거임
	}
}
