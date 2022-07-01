package com.mty.cc.dao;

import com.mty.cc.po.Position;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface PositionDao {

    //获取列表
    public List<Position> getPositionList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addPosition(Position position);

    //删除
    public int deletePosition(@Param("id") String id);

    //修改
    public int updatePosition(Position position);

    //根据ID查询
    public Position findPositionById(@Param("id") String id);

    //查询所有
    public List<Position> getAll();

    //按照条件查询
    public List<Position> queryFilter(Map mp);

}
