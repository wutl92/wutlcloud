package com.wutl.usercenter.dao;

import com.wutl.modelcenter.sys.user.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 13:57
 */
@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user")
    List<SysUser> findList();

    @Select("select * from sys_user where username = #{username}")
    SysUser getUserByUsername(String username);
}
