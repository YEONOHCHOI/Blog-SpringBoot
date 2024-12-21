package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //데이터를 리턴할게 아니라 파일을 리턴할 것이기 떄문에 넣는 것 // 해당경로 이하의 있는 파일 리턴
public class TempController {
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일을 리턴할 때 기본경로가 : src/main/resources/static
		//리턴명을 :/home.html
		//풀경로:src/main/resources/static/home.html
		return "/home.html";
	}
	
	// http://localhost:8000/blog/temp/jsp
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix: /WEB-INF/views/
		//suffix: .jsp
		//풀네임: /WEB-INF/views/test.jsp
		
		return "test";
	}

}
