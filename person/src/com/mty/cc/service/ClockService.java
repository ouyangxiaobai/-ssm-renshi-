package com.mty.cc.service;

import com.mty.cc.po.Clock;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface ClockService {

    //分页查询
    public PageInfo<Clock> findPageInfo(Integer pageIndex, Integer pageSize,Map mp);

    //添加
    public int addClock(Clock clock);

    //删除
    public int deleteClock(String id);

    //修改
    public int updateClock(Clock clock);

    //根据ID查询
    public Clock findClockById(String id);

    //查询所有
    public List<Clock> getAll();

    //按照条件查询
    public List<Clock> queryFilter(Map mp);
}
