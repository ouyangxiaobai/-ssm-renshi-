package com.mty.cc.service.impl;

import com.mty.cc.dao.ClockDao;
import com.mty.cc.po.Clock;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("clockService")
@Transactional
public class ClockServiceImpl implements ClockService {

    @Autowired
    private ClockDao clockDao;


    //分页查询
    @Override
    public PageInfo<Clock> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Clock> pi = new PageInfo<Clock>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = clockDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Clock> clockList =	clockDao.getClockList(mp);
            pi.setList(clockList);
        }
        return pi;
    }


    //添加
    @Override
    public int addClock(Clock clock) {
        clock.setId(RandomIdUtil.getRandomIdByUUID());
        return clockDao.addClock(clock);
    }


    //根据id删除
    @Override
    public int deleteClock(String id) {
        return clockDao.deleteClock(id);
    }


    //修改宿舍信息
    @Override
    public int updateClock(Clock clock) {
        return clockDao.updateClock(clock);
    }

    //根据ID查询
    @Override
    public Clock findClockById (String id){
        Clock d = clockDao.findClockById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Clock> getAll(){
        List<Clock> clockList = clockDao.getAll();
        return clockList;
    }

    //按照条件查询
    @Override
    public List<Clock> queryFilter(Map mp){
        List<Clock> clockList = clockDao.queryFilter(mp);
        return clockList;
    }
}
