package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

import jakarta.servlet.DispatcherType;

//회원가입을 위한 함수
@Configuration // IoC가 된다 - 함수와 리턴값을 스프링이 관리 - 필요할 떄 마다 가져와서 쓰면됌
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService PrincipalDetailService;
		
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
	// 시큐리티가 대신 로그인을 하는데 password를 가로채기를 하는데
	//해당 패스워드가 뭘로 해쉬가 되서 회원가입이 됐는지 알아야
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교가 가능
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(PrincipalDetailService).passwordEncoder(encodePWD());
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable().cors().disable() //csrf토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/","/auth/**","/js/**", "/css/**", "/image/**").permitAll()
					.anyRequest().authenticated()
					)
						.formLogin(form -> form.loginPage("/auth/loginForm").permitAll()
								.loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소로 오는 요청을 가로채서 대신 로그인해줌
								.defaultSuccessUrl("/")//성공했을 떄 가는 곳
					);
					return http.build();
	}
}

