package com.mty.cc.service;

import com.mty.cc.po.Notice;
import com.mty.cc.po.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 用户Service层接口
 * @author: mty
 */
public interface NoticeService {

    //分页查询
    public PageInfo<Notice> findPageInfo(Integer pageIndex, Integer pageSize);

    //添加
    public int addNotice(Notice notice);

    //删除
    public int deleteNotice(String id);

    //修改
    public int updateNotice(Notice notice);

    //根据ID查询
    public Notice findNoticeById(String id);

    //查询所有
    public List<Notice> getAll();

    //按照条件查询
    public List<Notice> queryFilter(Map mp);
}
