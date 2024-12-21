package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//ORM -> JAVA(다른언어) Object를 -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //insert시에 null인 필드 제외
@Entity // 클래스를 테이블화 시키는 것 / User 클래스가 Mysql에 테이블이 생성이 됌 자동으로 
public class User {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//넘버링 전략이 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //오라클로 얘기하면 시퀀스 / mysql에서는 auto_increment
	
	@Column(unique = true, nullable = false, length = 30) // not null, unique
	private String username; // 아이디
	
	@Column(nullable = false, length = 200)//해쉬 => 비밀번호 암호화 할거임
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email; 
	
	//@ColumnDefault("user")
	//DB는 RoleType이란게 없다 그래서 알려줘야함
	//이러면 Enum에 있는 2개만 쓰도록 타입이 강제됌
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋음 도메인을 만들 수 있으니까  //ADMIN, MANAGER, USER 권한 부여하는거
	
	@CreationTimestamp //시간이 자동으로 입력됌
	private Timestamp createDate;
}
