package com.mty.cc.service.impl;

import com.mty.cc.dao.PositionDao;
import com.mty.cc.po.Position;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("positionService")
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;


    //分页查询
    @Override
    public PageInfo<Position> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Position> pi = new PageInfo<Position>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = positionDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Position> positionList =	positionDao.getPositionList(mp);
            pi.setList(positionList);
        }
        return pi;
    }


    //添加
    @Override
    public int addPosition(Position position) {
        position.setId(RandomIdUtil.getRandomIdByUUID());
        return positionDao.addPosition(position);
    }


    //根据id删除
    @Override
    public int deletePosition(String id) {
        return positionDao.deletePosition(id);
    }


    //修改宿舍信息
    @Override
    public int updatePosition(Position position) {
        return positionDao.updatePosition(position);
    }

    //根据ID查询
    @Override
    public Position findPositionById (String id){
        Position d = positionDao.findPositionById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Position> getAll(){
        List<Position> positionList = positionDao.getAll();
        return positionList;
    }

    //按照条件查询
    @Override
    public List<Position> queryFilter(Map mp){
        List<Position> positionList = positionDao.queryFilter(mp);
        return positionList;
    }
}
