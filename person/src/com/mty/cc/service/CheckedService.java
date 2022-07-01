package com.mty.cc.service;

import com.mty.cc.po.Checked;
import com.mty.cc.po.PageInfo;
import java.util.*;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface CheckedService {

    //分页查询
    public PageInfo<Checked> findPageInfo(Integer pageIndex, Integer pageSize, Map mp);

    //添加
    public int addChecked(Checked checked);

    //删除
    public int deleteChecked(String id);

    //修改
    public int updateChecked(Checked checked);

    //根据ID查询
    public Checked findCheckedById(String id);

    //查询所有
    public List<Checked> getAll();

    //按照条件查询
    public List<Checked> queryFilter(Map mp);
}
