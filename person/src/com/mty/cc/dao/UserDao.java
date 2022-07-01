package com.mty.cc.dao;

import com.mty.cc.po.User;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface UserDao {

    //获取列表
    public List<User> getUserList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addUser(User user);

    //删除
    public int deleteUser(@Param("id") String id);

    //修改
    public int updateUser(User user);

    //根据ID查询
    public User findUserById(@Param("id") String id);

    //查询所有
    public List<User> getAll();

    //按照条件查询
    public List<User> queryFilter(Map mp);

}
