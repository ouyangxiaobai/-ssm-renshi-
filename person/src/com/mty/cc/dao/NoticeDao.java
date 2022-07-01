package com.mty.cc.dao;

import com.mty.cc.po.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: mty
 */
public interface NoticeDao {

    //获取列表
    public List<Notice> getNoticeList(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    //获取总条数
    public Integer totalCount();

    //添加
    public int addNotice(Notice notice);

    //删除
    public int deleteNotice(@Param("id") String id);

    //修改
    public int updateNotice(Notice notice);

    //根据ID查询
    public Notice findNoticeById(@Param("id") String id);

    //查询所有
    public List<Notice> getAll();

    //按照条件查询
    public List<Notice> queryFilter(Map mp);

}
