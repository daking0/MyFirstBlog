package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // 빈등록 (IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.
//Controller에서 특정 권한이 있는 유저만 접근을 허용하려면 @PreAuthorize 어노테이션을 사용하는데, 해당 어노테이션을 활성화 시키는 어노테이션이다.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean // IoC가 되요!!
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/"); // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
    }
}
//package com.cos.blog.config;
//
//import com.cos.blog.config.auth.PrincipalDetailService;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration //빈등록 (IoC관리)
//@EnableWebSecurity //security 필터 등록
//@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소를 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private PrincipalDetailService principalDetailService;
//    @Bean
//    public BCryptPasswordEncoder encodePWD(){
//        return new BCryptPasswordEncoder();
//    }
//
//    //시큐리티가 대신 로그인해주는데 password를 가로채기 하는데,
//    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @ConditionalOnDefaultWebSecurity
//    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() //csrf 토큰 비활성화(테스트 시에는 걸어두는게 좋다)
//                .authorizeRequests()
//                   .antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")
//                     .permitAll()
//                    .anyRequest()
//                  .authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/auth/loginForm")
//                    .loginProcessingUrl("/auth/loginProc") //springSecurity 가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인
//                    .defaultSuccessUrl("/"); //정상적으로 로그인 되었을 때는 기본주소로 이동
//        return http.build();
//    }
//
//}