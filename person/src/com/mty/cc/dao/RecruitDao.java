package com.mty.cc.dao;

import com.mty.cc.po.Recruit;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface RecruitDao {

    //获取列表
    public List<Recruit> getRecruitList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addRecruit(Recruit recruit);

    //删除
    public int deleteRecruit(@Param("id") String id);

    //修改
    public int updateRecruit(Recruit recruit);

    //根据ID查询
    public Recruit findRecruitById(@Param("id") String id);

    //查询所有
    public List<Recruit> getAll();

    //按照条件查询
    public List<Recruit> queryFilter(Map mp);

}
