package com.wutl.syscenter.web.user;

import com.wutl.modelcenter.sys.common.ResultVo;
import com.wutl.modelcenter.sys.user.SysUser;
import com.wutl.syscenter.service.user.ISysUserService;
import com.wutl.syscenter.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/12 9:45
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ISysUserService userService;

    @GetMapping(value = "/getUserByUsername")
    @ResponseBody
    public ResultVo getUserByUsername(String username) {
        SysUser user = userService.getUserByUsername(username);
        return ResultVo.returnSuccess(user);
    }
}
