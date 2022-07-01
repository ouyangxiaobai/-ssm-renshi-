package com.mty.cc.dao;

import com.mty.cc.po.Leaves;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface LeavesDao {

    //获取列表
    public List<Leaves> getLeavesList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addLeaves(Leaves leaves);

    //删除
    public int deleteLeaves(@Param("id") String id);

    //修改
    public int updateLeaves(Leaves leaves);

    //根据ID查询
    public Leaves findLeavesById(@Param("id") String id);

    //查询所有
    public List<Leaves> getAll();

    //按照条件查询
    public List<Leaves> queryFilter(Map mp);

}
