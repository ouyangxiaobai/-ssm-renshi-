package com.mty.cc.dao;

import com.mty.cc.po.Salary;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface SalaryDao {

    //获取列表
    public List<Salary> getSalaryList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addSalary(Salary salary);

    //删除
    public int deleteSalary(@Param("id") String id);

    //修改
    public int updateSalary(Salary salary);

    //根据ID查询
    public Salary findSalaryById(@Param("id") String id);

    //查询所有
    public List<Salary> getAll();

    //按照条件查询
    public List<Salary> queryFilter(Map mp);

}
