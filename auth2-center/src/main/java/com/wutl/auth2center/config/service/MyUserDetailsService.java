package com.wutl.auth2center.config.service;

import com.wutl.auth2center.service.ISysUserService;
import com.wutl.modelcenter.sys.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/10 14:28
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
        list.add(simpleGrantedAuthority);
        return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), list);
    }
}
