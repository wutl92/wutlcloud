package com.wutl.auth2center.service;

import com.wutl.auth2center.service.fallback.ISysUserServiceFallback;
import com.wutl.modelcenter.sys.user.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 14:18
 */
@FeignClient(value = "user-center", fallback = ISysUserServiceFallback.class)
public interface ISysUserService {

    @PostMapping(value = "/user/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<SysUser> getUserList();

    @PutMapping(path = "/user/getUserByUsername/{username}")
    SysUser getUserByUsername(@PathVariable("username") String username);

}
