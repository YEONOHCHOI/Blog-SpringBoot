package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를 
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 함 그때 저장되는게 PrincipalDetail 얘가 저장이 됌 
public class PrincipalDetail implements UserDetails{
	private User user; //컴포지션이라고 함 객체를 품고 있는거 extends는 상속

	
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴함 (true: 만료안됌)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겼있지 않았는지 리턴함 (true: 안잠김)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴한다(true:만료안됌)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 사용가능한지 리턴한다(true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//계정의 권한 목록을 리턴한다(권한이 원래는 여러개라 for문 돌아야하는데 우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				return "Role_" + user.getRole(); //Role_USER이런식으로 나와줘야됌 공식임 스프링에서 규칙임 Role_이거
//			}
//			
//		});
		
		//람다식표현
		collectors.add(()->{return "Role_" + user.getRole();});
		
		return collectors;
	}
	
	
}
