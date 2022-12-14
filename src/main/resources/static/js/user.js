let index = {
    init: function(){
        $("#btn-save").on("click",()=>{ // 화살표함수 쓰는 이유는 this를 바인딩하기 위해서
            this.save();
        });
    },

    save:function(){

        let data = {
            username: $("#username").val(),
            password: $("#pwd").val(),
            email: $("#email").val()
        };

        // ajax호출시 default가 비동기 호출
        // ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        $.ajax({
            type:"POST",
            url:"/blog/api/user",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
//            dataType:"json" // 요청을 서버로해서 응답 왔을 때 기본적으로 모든 것이 문자열인데, 생긴게 JSON이라면 => javascript object로 변경해줌
        }).done(function(resp){
            alert("회원가입 완료되었습니다.");
            location.href = "/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();
