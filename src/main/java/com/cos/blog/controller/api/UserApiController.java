package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    // HttpSession session 은 import해서도 사용할 수 있고
    // or @Autowired로 받아올 수도 있다(이 경우에는 param에 작성 안해도 사용 가능)
//    @Autowired
//    private HttpSession session;

    @PostMapping("/api/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        user.setRoles(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

        // 실제로 db에 insert를 하고 아래에서 return 되면 된다,
//        user.setRoles(RoleType.USER);
//        int result = userService.회원가입(user);
//        return new ResponseDto<Integer>(HttpStatus.OK,result); //자바오브젝트를 json으로 리턴해줌(jackson
    }



}
