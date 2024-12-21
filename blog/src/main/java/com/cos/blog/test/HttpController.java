package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청->응답(html)파일 하고싶으면
//Controller

//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다
//사용자가 요청 ->응답을 해주는게 컨트롤러 (데이터)
@RestController
public class HttpController {
	// localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//builder @Build애노테이션을 통해 쓸 수 있음
		Member m = Member.builder().userName("ssar").password("1234").email("ssar@nate.com").build(); //id뺴고 데이터 넣기
		System.out.println( Tag + "getter: " + m.getUserName());
		m.setUserName("cos");
		System.out.println( Tag + "setter: " + m.getUserName());
		return "lombok test 완료";
	}
	
	private static final String Tag = "HttpController";
	
	//http://localhost:8080/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m ) {
		return "get 요청 " + m.getId()  + ", "+ m.getUserName() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	//http://localhost:8080/http/post(insert)
	@PostMapping("/http/post")//text/plain, application/json (마임타입)
	public String postTest(@RequestBody Member m ){//스프링부트 MessageConverter {
		return "post 요청 " + m.getId()  + ", "+ m.getUserName() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	//http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 " + m.getId()  + ", "+ m.getUserName() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
