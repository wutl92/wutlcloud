package com.wutl.auth2center.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/6 10:29
 */
@Controller
@RequestMapping
public class LoginController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }


    @GetMapping(value = "/logout")
    public String logout() {
        return "logout";
    }
}
