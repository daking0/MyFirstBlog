package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public void 회원가입(User user){
        String rawPassword = user.getPassword(); //1234 원문 비밀번호
        String encPassword = encoder.encode(rawPassword); //해쉬 비밀번호
        user.setPassword(encPassword);
        user.setRoles(RoleType.USER);
        userRepository.save(user);

    }

    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 영속성컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        // Select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update 날려준다.
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        //회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다.
        //영속화 된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 자동으로 날려준다.

    }
}
