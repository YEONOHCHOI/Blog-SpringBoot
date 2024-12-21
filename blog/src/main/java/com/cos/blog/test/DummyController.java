package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

//html파일이 아니라 data를 리턴해주는 controller
@RestController //회원가입이 잘 됐나 안됐나만 해줄 수 있게 넣어준거임
public class DummyController {
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다 해당 Id는 DB에 없습니다";
		}
		return "삭제 되었습니다: " + id;
	}
	
	//save함수는 id를 전달하지 않으면 insert를 해주고 
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해줌
	//email, password 수정
	@Transactional //이걸 걸면 save함수 안써도 update가 된다 //함수 종료시 자동 commit
	@PutMapping("/dummy/user/{id}")
	//json데이터 요청 -> spring 이 Java object로 변환해서 받아줌 이떄 MessageConverter에 Jackson라이브러리가 변환해서 받아줌
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("password: " + requestUser.getPassword());
		System.out.println("email: " + requestUser.getEmail());
		//람다식
		//영속화
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		//변경
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(requestUser); //save는 원래 insert할 때 쓰는거임 그래서 업데이트 할 떄는 save를 안씀 
		
		//더티 체킹을 이용한 업데이트
		return user;
		
	}
	
	//유저 전체 받기
	//한 건이 아니라 여러개 받을거니까 List타입으로 받을거임
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터 리턴받기 // 전략이 2건씩 sort는id로 하고 id를 최신순으로 들고올거임
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent(); //결과는 리스트로 이렇게 받는게 좋음
		return users;
	}
	
	//user 리턴받기 
	//{"id"} 주소로 파라미터를 전달 받을 수 있습니다 
	//http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user의 4번을 찾으면 내가 DB에서 못찾아오게되면 user가 null이 되는데 
		//그럼 return null이 되잖아 그럼 프로그램이 문제가 되잖아
		// Optional로 너의 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		
		//람다식 
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
		public IllegalArgumentException get() {
		// TODO Auto-generated method stub
		return new IllegalArgumentException("해당 사용자는 없습니다");
			}
					
	   });
		//요청을 웹 브라우저에서 했음 
		//user객체 = 자바 오브젝트 
		//웹 브라우저가 이해할 수 있는 데이터로 변환 -> json
		//스프링부트는 = messageConverter라는 애가 응답시에 자동 작동함 
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서 
		//user오브젝트를 json으로 변환 후 브라우저에게 던져줍니다 
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email데이터를 가지고 (요청)
	@PostMapping("/dummy/join") //insert할거니까 PostMapping
	public String join(User user) { //함수의 파라미터에서 전달 받을 수 있음 //key = value형태
		System.out.println("id: " + user.getId());
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());
		System.out.println("role: " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}
