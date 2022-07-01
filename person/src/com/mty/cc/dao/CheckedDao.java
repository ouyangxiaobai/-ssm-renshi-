package com.mty.cc.dao;

import com.mty.cc.po.Checked;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * DAO层接口
 * @author: mty
 */
public interface CheckedDao {

    //获取列表
    public List<Checked> getCheckedList(Map mp);

    //获取总条数
    public Integer totalCount(Map mp);

    //添加
    public int addChecked(Checked checked);

    //删除
    public int deleteChecked(@Param("id") String id);

    //修改
    public int updateChecked(Checked checked);

    //根据ID查询
    public Checked findCheckedById(@Param("id") String id);

    //查询所有
    public List<Checked> getAll();

    //按照条件查询
    public List<Checked> queryFilter(Map mp);

}
