package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //빈등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌 떄 username이랑 password라는 두개의 변수를 가로채는데 
	//password 부분 처리는 알아서 함 그래서 
	//username이 DB에 있는지만 확인해주면 됨
	//이걸 만드는 이유는 우리가 들고 있는 username과 password값을 넣어주려고 그런거임 이게 없으면
	//스프링에서 자동으로 만들어주는 username password써야됨
	@Override          //로그인이 진행될 떄 이 함수가 자동으로 실행 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
				});
		
		return new PrincipalDetail(principal); //시큐리티 세션에 유저 정보가 저장이 됨
	}

}
