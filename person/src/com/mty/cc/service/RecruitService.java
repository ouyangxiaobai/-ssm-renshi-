package com.mty.cc.service;

import com.mty.cc.po.Recruit;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface RecruitService {

    //分页查询
    public PageInfo<Recruit> findPageInfo(Integer pageIndex, Integer pageSize, Map mp);

    //添加
    public int addRecruit(Recruit recruit);

    //删除
    public int deleteRecruit(String id);

    //修改
    public int updateRecruit(Recruit recruit);

    //根据ID查询
    public Recruit findRecruitById(String id);

    //查询所有
    public List<Recruit> getAll();

    //按照条件查询
    public List<Recruit> queryFilter(Map mp);
}
