package com.wutl.syscenter.service.user.imp;

import com.wutl.modelcenter.sys.user.SysUser;
import com.wutl.syscenter.service.user.ISysUserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 14:18
 */
@Component
public class ISysUserServiceFallback implements ISysUserService {


    @Override
    public List<SysUser> getUserList() {
        System.out.println("=======getUserList========");
        return new ArrayList<>();
    }

    @Override
    public SysUser getUserByUsername(String username) {
        System.out.println("=======getUserByUsername========");
        return new SysUser();
    }
}
