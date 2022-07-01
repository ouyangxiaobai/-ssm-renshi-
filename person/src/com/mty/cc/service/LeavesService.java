package com.mty.cc.service;

import com.mty.cc.po.Leaves;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface LeavesService {

    //分页查询
    public PageInfo<Leaves> findPageInfo(Integer pageIndex, Integer pageSize, Map mp);

    //添加
    public int addLeaves(Leaves leaves);

    //删除
    public int deleteLeaves(String id);

    //修改
    public int updateLeaves(Leaves leaves);

    //根据ID查询
    public Leaves findLeavesById(String id);

    //查询所有
    public List<Leaves> getAll();

    //按照条件查询
    public List<Leaves> queryFilter(Map mp);
}
