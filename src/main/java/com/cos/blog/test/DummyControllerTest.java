package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired
    private UserRepository userRepository;

    // http의 body에 username, password, email 데이터를 가지고 요청
    // body type을 x-www-form- 으로 하면,
    // @Requestparam 등으로 따로 지정하지 않아도 key=value 로 인식되도록함
//    @PostMapping("/dummy/join")
//    public String join(String username, String password, String email){
//        return "회원가입 완료";
//    }


    // save함수는 id를 전달하지 않으면 insert
    // id를 전달하면 해당 id에 대한 데이터가 있으면 update 데이터가 없으면 insert

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            return "없는 id라서 삭제 실패";
        }


        return "id : "+id + " 삭제 되었습니다.";
    }

    @Transactional //함수 종료시 자동 commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser){

        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("수정에 실패하였습니다..");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(requestUser);
        return user;
    }
    @PostMapping("/dummy/join")
    public String join(User user){
        user.setRoles(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한 페이지 당 2건의 데이터를 리턴 (폐이징)
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser =userRepository.findAll(pageable);
        List<User> users =pagingUser.getContent();

        // pagingUser.isFirst() , pagingUser.isLast()

        return users;
    }
    // {id}주소로 파라미터를 전달받을 수 있음
//    @GetMapping("/dummy/user/{id}")
//    public User detail(@PathVariable int id){
//        //findById의 return타입은 optional이다.
//        //만약 찾는 값이 null이면 optional로 user객체 감싸온 후에 null인지 판단 후 return
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            // 만약 값이 없다면 빈 객체를 user에 넣어줌
//            // -> null은 아니니까
//            @Override
//            public User get() {
//                return new User();
//            }
//        });
//    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        //findById의 return타입은 optional이다.
        //만약 찾는 값이 null이면 optional로 user객체 감싸온 후에 null인지 판단 후 return
        //IllegalArgumentException 을 return
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("id "+id+" : 해당 유저는 없습니다.");
            }
        });
        // 웹브라우저가 요청 -> user 객체로 리턴해줌
        // user 객체 = 자바 오브젝트 라서 json으로 변경해서 던져야 웹브라우저가 이해함
        // springboot 는 MessageConverter라는 애가 응답시에 자동 작동해서,
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return user;
    }


}
