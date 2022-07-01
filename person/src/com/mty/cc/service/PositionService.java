package com.mty.cc.service;

import com.mty.cc.po.Position;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface PositionService {

    //分页查询
    public PageInfo<Position> findPageInfo(Integer pageIndex, Integer pageSize, Map mp);

    //添加
    public int addPosition(Position position);

    //删除
    public int deletePosition(String id);

    //修改
    public int updatePosition(Position position);

    //根据ID查询
    public Position findPositionById(String id);

    //查询所有
    public List<Position> getAll();

    //按照条件查询
    public List<Position> queryFilter(Map mp);
}
