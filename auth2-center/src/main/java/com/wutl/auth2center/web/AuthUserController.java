package com.wutl.auth2center.web;

import com.wutl.auth2center.service.ISysUserService;
import com.wutl.modelcenter.sys.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 16:27
 */
@RequestMapping("/api")
@Controller
public class AuthUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @GetMapping(value = "/getCurrentUser", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public SysUser getCurrentUser(Principal principal) {
        String name = principal.getName();
        SysUser userByUsername = iSysUserService.getUserByUsername(name);
        return userByUsername;
    }
}
