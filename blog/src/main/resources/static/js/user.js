let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ //function(){}을 쓰지 않고, ()+-=>{}를 쓰는 이유는 this를 바인딩하기 위해서임 
			this.save();
		});
	},
	
		save: function(){
			//alert('user의 save함수 호출됌')	
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
			};
			
			//console.log(data);
			
			// ajax호출시 default가 비동기 호출
			//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청을 할거임
			//ajax가 통신으 성공하고 나서 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환해줌
			$.ajax({//회원가입 수행 요청
				type: "POST", //POST면 insert라는 걸 알기 떄문에 url를 user까지만 적는 사람이 많음
				url: "/auth/joinProc",
				data: JSON.stringify(data),//<-http body데이터임 근데 이거를 날릴 떈 MIME타입이 필요함
				contentType: "application/json; charset=utf-8", //MIME타입(body데이터의 타입)
				dataType: "json"//요청에 대한 응답이 왔을 때 기본적으로 buffer로 오니까 String임 문자열(생긴데 json이라면)=>javascript오브젝트로 바꿔줌
			}).done(function(resp){ //성공
			    alert("회원가입이 완료되었습니다.")
			    //console.log(resp);
			    location.href = "/";
				
			}).fail(function(error){ //실패
				alert(JSON.stringify(error));
				
			}); 
		},
}

index.init();