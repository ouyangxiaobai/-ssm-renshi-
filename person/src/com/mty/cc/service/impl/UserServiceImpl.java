package com.mty.cc.service.impl;

import com.mty.cc.dao.UserDao;
import com.mty.cc.po.User;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    //分页查询
    @Override
    public PageInfo<User> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<User> pi = new PageInfo<User>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = userDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<User> userList =	userDao.getUserList(mp);
            pi.setList(userList);
        }
        return pi;
    }


    //添加
    @Override
    public int addUser(User user) {
        user.setId(RandomIdUtil.getRandomIdByUUID());
        return userDao.addUser(user);
    }


    //根据id删除
    @Override
    public int deleteUser(String id) {
        return userDao.deleteUser(id);
    }


    //修改宿舍信息
    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    //根据ID查询
    @Override
    public User findUserById (String id){
        User d = userDao.findUserById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<User> getAll(){
        List<User> userList = userDao.getAll();
        return userList;
    }

    //按照条件查询
    @Override
    public List<User> queryFilter(Map mp){
        List<User> userList = userDao.queryFilter(mp);
        return userList;
    }
}
