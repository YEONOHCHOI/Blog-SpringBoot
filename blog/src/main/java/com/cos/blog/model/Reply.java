package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 클래스를 테이블화 시키는 것 / User 클래스가 Mysql에 테이블이 생성이 됌 자동으로 
public class Reply {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//넘버링 전략이 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //오라클로 얘기하면 시퀀스 / mysql에서는 auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;//섬머노트 라이브러리 쓸거임 일반적인 글이 디자인이 되서 들어가는거임 html태그가 섞여서 그러면 글자 용량이 커짐
	
	@ManyToOne //여러개의 답변은 하나의 게시글에 존재가능
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne // 여러개의 답변을 하나의 유저가 쓸 수 있다
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
