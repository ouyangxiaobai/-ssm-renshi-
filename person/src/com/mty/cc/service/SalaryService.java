package com.mty.cc.service;

import com.mty.cc.po.Salary;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface SalaryService {

    //分页查询
    public PageInfo<Salary> findPageInfo(Integer pageIndex, Integer pageSize,Map mp);

    //添加
    public int addSalary(Salary salary);

    //删除
    public int deleteSalary(String id);

    //修改
    public int updateSalary(Salary salary);

    //根据ID查询
    public Salary findSalaryById(String id);

    //查询所有
    public List<Salary> getAll();

    //按照条件查询
    public List<Salary> queryFilter(Map mp);
}
