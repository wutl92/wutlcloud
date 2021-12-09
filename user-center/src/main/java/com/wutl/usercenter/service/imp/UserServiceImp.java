package com.wutl.usercenter.service.imp;

import com.wutl.modelcenter.sys.user.SysUser;
import com.wutl.usercenter.dao.SysUserMapper;
import com.wutl.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/10 9:25
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据用户名查找用户
     *
     * @param username :
     * @return: {@link SysUser}
     * @Author: Wutl
     * @Date: 2021/11/10 9:24
     */
    @Override
    public SysUser findUserByUsername(String username) {
        SysUser user = userMapper.getUserByUsername(username);
        return user;
    }

    /**
     * 查询所有用户
     *
     * @return: {@link List < SysUser>}
     * @Author: Wutl
     * @Date: 2021/11/10 9:27
     */
    @Override
    public List<SysUser> findList() {
        List<SysUser> list = userMapper.findList();
        return list;
    }

}
