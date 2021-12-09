package com.wutl.usercenter.web;

import com.sun.istack.internal.NotNull;
import com.wutl.modelcenter.sys.user.SysUser;
import com.wutl.usercenter.dao.SysUserMapper;
import com.wutl.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 14:00
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public List<SysUser> list(HttpServletRequest request) {
        Map<String, Object> userMap = new HashMap<>();
        List<SysUser> list = userService.findList();
        return list;
    }

    @RequestMapping(value = "/getUserByUsername/{username}")
    @ResponseBody
    public SysUser getUserByUsername(@PathVariable("username") String username) {
        SysUser sysUser = userService.findUserByUsername(username);
        return sysUser;
    }

    @RequestMapping(value = "/auth")
    public void authDemo(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.length() < 1) {
            try {
                response.addHeader("WWW-Authenticate", "Basic realm=wutl");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter printWriter = new PrintWriter(response.getOutputStream());
                printWriter.write("Http Status 401: " + "暂无权限");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("user/auth请求成功");
        }
    }
}
