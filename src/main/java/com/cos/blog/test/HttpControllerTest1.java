package com.cos.blog.test;


//@Controller 사용자가 요청하면 html을 응답

//@RestController 사용자가 요청하면 data를 응답

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest1 {

//    @GetMapping("http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username){
//        return "get 요청"+id+" & "+username;
//    }
    @GetMapping("http/get")
    public String getTest_2(Member member){
        return "get 요청 "+ member.getId()+" & "+member.getUsername()+" & "+member.getPassword() +" & " + member.getEmail();
    }
//    @PostMapping("http/post")
//    public String postTest(Member member){
//        return "get 요청 "+ member.getId()+" & "+member.getUsername()+" & "+member.getPassword() +" & " + member.getEmail();
//    }
    @PostMapping("http/post")
    public String postTest(@RequestBody String text){
        return text;
    }
    @PutMapping("http/put")
    public String putTest(){
        return "put 요청";
    }

    @DeleteMapping("http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

    @GetMapping("/http/lombok")
    public String LombokTest(Member member){
        Member m = Member.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .username(member.getUsername()).build();
        System.out.printf(m.getUsername());
        m.setUsername("test");

        System.out.printf(m.getId()+" , "+m.getPassword()+" , "+m.getUsername()+" , "+m.getEmail());
        return "gg";

    }
}
