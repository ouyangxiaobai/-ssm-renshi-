package com.mty.cc.dao;

import com.mty.cc.po.Works;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface WorksDao {

    //获取列表
    public List<Works> getWorksList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addWorks(Works works);

    //删除
    public int deleteWorks(@Param("id") String id);

    //修改
    public int updateWorks(Works works);

    //根据ID查询
    public Works findWorksById(@Param("id") String id);

    //查询所有
    public List<Works> getAll();

    //按照条件查询
    public List<Works> queryFilter(Map mp);

}
