package com.mty.cc.service.impl;

import com.mty.cc.dao.NoticeDao;
import com.mty.cc.po.Notice;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.NoticeService;
import com.mty.cc.util.RandomIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;


    //分页查询
    @Override
    public PageInfo<Notice> findPageInfo(Integer pageIndex, Integer pageSize) {
        PageInfo<Notice> pi = new PageInfo<Notice>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount = noticeDao.totalCount();
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Notice> noticeList =	noticeDao.getNoticeList((pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(noticeList);
        }
        return pi;
    }


    //添加s
    @Override
    public int addNotice(Notice notice) {
        notice.setId(RandomIdUtil.getRandomIdByUUID());
        return noticeDao.addNotice(notice);
    }


    //根据id删除
    @Override
    public int deleteNotice(String id) {
        return noticeDao.deleteNotice(id);
    }


    //修改宿舍信息
    @Override
    public int updateNotice(Notice notice) {
        return noticeDao.updateNotice(notice);
    }

    //根据ID查询
    @Override
    public Notice findNoticeById (String id){
        Notice d = noticeDao.findNoticeById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Notice> getAll(){
        List<Notice> noticeList = noticeDao.getAll();
        return noticeList;
    }

    //按照条件查询
    @Override
    public List<Notice> queryFilter(Map mp){
        List<Notice> noticeList = noticeDao.queryFilter(mp);
        return noticeList;
    }
}
