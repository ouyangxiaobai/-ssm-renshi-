package com.mty.cc.service;

import com.mty.cc.po.Department;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface DepartmentService {

    //分页查询
    public PageInfo<Department> findPageInfo(Integer pageIndex, Integer pageSize,Map mp);

    //添加
    public int addDepartment(Department department);

    //删除
    public int deleteDepartment(String id);

    //修改
    public int updateDepartment(Department department);

    //根据ID查询
    public Department findDepartmentById(String id);

    //查询所有
    public List<Department> getAll();

    //按照条件查询
    public List<Department> queryFilter(Map mp);
}
