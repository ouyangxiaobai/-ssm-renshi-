package com.mty.cc.service.impl;

import com.mty.cc.dao.AdminDao;
import com.mty.cc.po.Admin;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    //分页查询
    @Override
    public PageInfo<Admin> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Admin> pi = new PageInfo<Admin>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = adminDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Admin> adminList =	adminDao.getAdminList(mp);
            pi.setList(adminList);
        }
        return pi;
    }


    //添加
    @Override
    public int addAdmin(Admin admin) {
        admin.setId(RandomIdUtil.getRandomIdByUUID());
        return adminDao.addAdmin(admin);
    }


    //根据id删除
    @Override
    public int deleteAdmin(String id) {
        return adminDao.deleteAdmin(id);
    }


    //修改宿舍信息
    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }

    //根据ID查询
    @Override
    public Admin findAdminById (String id){
        Admin d = adminDao.findAdminById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Admin> getAll(){
        List<Admin> adminList = adminDao.getAll();
        return adminList;
    }

    //按照条件查询
    @Override
    public List<Admin> queryFilter(Map mp){
        List<Admin> adminList = adminDao.queryFilter(mp);
        return adminList;
    }
}
