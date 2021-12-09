package com.wutl.auth2center.config;

import com.wutl.auth2center.config.service.MyUserDetailsService;
import com.wutl.auth2center.handler.MyAuthLogoutSuccessHandler;
import com.wutl.auth2center.handler.MyAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/6 12:42
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MyAuthLogoutSuccessHandler logoutSuccessHandler;
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        String pwd = new BCryptPasswordEncoder().encode("123456");//对密码进行加密
////        manager.createUser(User.withUsername("user_1").password(pwd).authorities("USER").build());
////        manager.createUser(User.withUsername("user_2").password(pwd).authorities("USER").build());
//        return userDetailsService;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*", "/css/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/token/form").failureHandler(myAuthenticationFailureHandler)
                .and().logout().logoutSuccessUrl("/logout").deleteCookies("JJSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
