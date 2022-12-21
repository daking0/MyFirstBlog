package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, username과 password 변수 2개를 가로채는데 password는 알아서 처리함
    // 우리는 username이 DB에 있는지만 확인해주면 된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
                });
        //바로 user타입의 principal을 return할 수 없고, PrincipalDetail을 새로 생성해서 return
        return new PrincipalDetail(principal); // 이 때 시큐리티의 세션에 유저 정보가 저장된다
    }
}
