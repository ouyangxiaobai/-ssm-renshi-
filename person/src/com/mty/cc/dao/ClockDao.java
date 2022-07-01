package com.mty.cc.dao;

import com.mty.cc.po.Clock;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface ClockDao {

    //获取列表
    public List<Clock> getClockList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addClock(Clock clock);

    //删除
    public int deleteClock(@Param("id") String id);

    //修改
    public int updateClock(Clock clock);

    //根据ID查询
    public Clock findClockById(@Param("id") String id);

    //查询所有
    public List<Clock> getAll();

    //按照条件查询
    public List<Clock> queryFilter(Map mp);

}
