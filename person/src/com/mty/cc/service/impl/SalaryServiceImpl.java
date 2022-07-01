package com.mty.cc.service.impl;

import com.mty.cc.dao.SalaryDao;
import com.mty.cc.po.Salary;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("salaryService")
@Transactional
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryDao salaryDao;


    //分页查询
    @Override
    public PageInfo<Salary> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Salary> pi = new PageInfo<Salary>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = salaryDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Salary> salaryList =	salaryDao.getSalaryList(mp);
            pi.setList(salaryList);
        }
        return pi;
    }


    //添加
    @Override
    public int addSalary(Salary salary) {
        salary.setId(RandomIdUtil.getRandomIdByUUID());
        return salaryDao.addSalary(salary);
    }


    //根据id删除
    @Override
    public int deleteSalary(String id) {
        return salaryDao.deleteSalary(id);
    }


    //修改宿舍信息
    @Override
    public int updateSalary(Salary salary) {
        return salaryDao.updateSalary(salary);
    }

    //根据ID查询
    @Override
    public Salary findSalaryById (String id){
        Salary d = salaryDao.findSalaryById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Salary> getAll(){
        List<Salary> salaryList = salaryDao.getAll();
        return salaryList;
    }

    //按照条件查询
    @Override
    public List<Salary> queryFilter(Map mp){
        List<Salary> salaryList = salaryDao.queryFilter(mp);
        return salaryList;
    }
}
