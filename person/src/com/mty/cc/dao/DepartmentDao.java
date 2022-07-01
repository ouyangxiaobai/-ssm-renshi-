package com.mty.cc.dao;

import com.mty.cc.po.Department;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface DepartmentDao {

    //获取列表
    public List<Department> getDepartmentList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addDepartment(Department department);

    //删除
    public int deleteDepartment(@Param("id") String id);

    //修改
    public int updateDepartment(Department department);

    //根据ID查询
    public Department findDepartmentById(@Param("id") String id);

    //查询所有
    public List<Department> getAll();

    //按照条件查询
    public List<Department> queryFilter(Map mp);

}
