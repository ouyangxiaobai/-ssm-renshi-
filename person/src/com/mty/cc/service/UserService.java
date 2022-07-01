package com.mty.cc.service;

import com.mty.cc.po.User;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface UserService {

    //分页查询
    public PageInfo<User> findPageInfo(Integer pageIndex, Integer pageSize,Map mp);

    //添加
    public int addUser(User user);

    //删除
    public int deleteUser(String id);

    //修改
    public int updateUser(User user);

    //根据ID查询
    public User findUserById(String id);

    //查询所有
    public List<User> getAll();

    //按照条件查询
    public List<User> queryFilter(Map mp);
}
