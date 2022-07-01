package com.mty.cc.service.impl;

import com.mty.cc.dao.DepartmentDao;
import com.mty.cc.po.Department;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;


    //分页查询
    @Override
    public PageInfo<Department> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Department> pi = new PageInfo<Department>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = departmentDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Department> departmentList =	departmentDao.getDepartmentList(mp);
            pi.setList(departmentList);
        }
        return pi;
    }


    //添加
    @Override
    public int addDepartment(Department department) {
        department.setId(RandomIdUtil.getRandomIdByUUID());
        return departmentDao.addDepartment(department);
    }


    //根据id删除
    @Override
    public int deleteDepartment(String id) {
        return departmentDao.deleteDepartment(id);
    }


    //修改宿舍信息
    @Override
    public int updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    //根据ID查询
    @Override
    public Department findDepartmentById (String id){
        Department d = departmentDao.findDepartmentById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Department> getAll(){
        List<Department> departmentList = departmentDao.getAll();
        return departmentList;
    }

    //按照条件查询
    @Override
    public List<Department> queryFilter(Map mp){
        List<Department> departmentList = departmentDao.queryFilter(mp);
        return departmentList;
    }
}
