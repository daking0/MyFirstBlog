package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        // 실제로 db에 insert를 하고 아래에서 return 되면 된다,
        return new ResponseDto<Integer>(HttpStatus.OK,1); //자바오브젝트를 json으로 리턴해줌(jackson
    }
}
