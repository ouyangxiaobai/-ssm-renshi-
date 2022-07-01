package com.mty.cc.service;

import com.mty.cc.po.Works;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface WorksService {

    //分页查询
    public PageInfo<Works> findPageInfo(Integer pageIndex, Integer pageSize,Map mp);

    //添加
    public int addWorks(Works works);

    //删除
    public int deleteWorks(String id);

    //修改
    public int updateWorks(Works works);

    //根据ID查询
    public Works findWorksById(String id);

    //查询所有
    public List<Works> getAll();

    //按照条件查询
    public List<Works> queryFilter(Map mp);
}
