package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data//게터세터
@NoArgsConstructor //빈 생성자
public class Member { //데이터가 변경되지 않게 final로 설정 불변성 근데 비밀번호 바꿀려면 final붙히면 안됌
	private int id;
	private String userName;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String userName, String password, String email) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
}
