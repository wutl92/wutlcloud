package com.wutl.usercenter.service;

import com.wutl.modelcenter.sys.user.SysUser;

import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/10 9:24
 */

public interface UserService {
    /**
     * 根据用户名查找用户
     *
     * @param username:
     * @return: {@link SysUser}
     * @Author: Wutl
     * @Date: 2021/11/10 9:24
     */
    SysUser findUserByUsername(String username);

    /**
     * 查询所有用户
     *
     * @return: {@link List< SysUser>}
     * @Author: Wutl
     * @Date: 2021/11/10 9:27
     */
    List<SysUser> findList();

}
