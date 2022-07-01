package com.mty.cc.service.impl;

import com.mty.cc.dao.RecruitDao;
import com.mty.cc.po.Recruit;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("recruitService")
@Transactional
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitDao recruitDao;


    //分页查询
    @Override
    public PageInfo<Recruit> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Recruit> pi = new PageInfo<Recruit>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = recruitDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Recruit> recruitList =	recruitDao.getRecruitList(mp);
            pi.setList(recruitList);
        }
        return pi;
    }


    //添加
    @Override
    public int addRecruit(Recruit recruit) {
        recruit.setId(RandomIdUtil.getRandomIdByUUID());
        return recruitDao.addRecruit(recruit);
    }


    //根据id删除
    @Override
    public int deleteRecruit(String id) {
        return recruitDao.deleteRecruit(id);
    }


    //修改宿舍信息
    @Override
    public int updateRecruit(Recruit recruit) {
        return recruitDao.updateRecruit(recruit);
    }

    //根据ID查询
    @Override
    public Recruit findRecruitById (String id){
        Recruit d = recruitDao.findRecruitById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Recruit> getAll(){
        List<Recruit> recruitList = recruitDao.getAll();
        return recruitList;
    }

    //按照条件查询
    @Override
    public List<Recruit> queryFilter(Map mp){
        List<Recruit> recruitList = recruitDao.queryFilter(mp);
        return recruitList;
    }
}
