package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice //모든 익셉션이 발생하면 이 클래스로 들어옴
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class) //IllegalArgumentException이 발생하면 그 에러를 e에다 넣어줘서 return함
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
}
