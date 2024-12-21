package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment사용
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(columnDefinition = "LONGTEXT")
	@Lob //대용량 데이터
	private String content;//섬머노트 라이브러리 쓸거임 일반적인 글이 디자인이 되서 들어가는거임 html태그가 섞여서 그러면 글자 용량이 커짐
	
	@ColumnDefault("0")
	private int count; //조회수
	
	//유저 클래스와 자동으로 포른키가 됌
	@ManyToOne(fetch = FetchType.EAGER) // Many = 보드 , One = User 한 명의 유저는 여러개의 게시글을 쓸 수 있음 
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다 그래서 FK를 사용함 , 자바는 오브젝트를 저장할 수 있음 그래서 충돌이 남 
	
	
	@OneToMany (mappedBy = "board", fetch = FetchType.EAGER) // mappedBy가 적혀있으면 연관관계의 주인이 아니다 난 FK가 아니에요 DB에 칼럼을 만들지 마세요
	//나는 그냥 보드를 Select할 떄 Join문을 통해서 값을 얻기 위해서 있는 것 뿐입니다 이런 뜻임
	//하나의 게시글은 여러개의 답변을 가질 수 있음 
	private List<Reply> reply; // 답글은 여러개를 달 수 있음 하지만 유저는 하나여야됌 그래서 reply에서 콜렉션을 씀
	
	@CreationTimestamp //인서트 되거나 업데이트 될 때 자동으로 시간이 들어감 
	private Timestamp createDate;
}
