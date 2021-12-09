package com.wutl.syscenter.service.user;

import com.wutl.modelcenter.sys.user.SysUser;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 14:18
 */
@FeignClient("user-center")
public interface ISysUserService {

    @PostMapping(value = "/user/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<SysUser> getUserList();

    @PutMapping(path = "/user/getUserByUsername/{username}")
    @Cacheable(cacheNames = "user-cache::username", key = "#username"
            , condition = "#username.length > 0", unless = "#result  ==  null ")
    SysUser getUserByUsername(@PathVariable("username") String username);

}
