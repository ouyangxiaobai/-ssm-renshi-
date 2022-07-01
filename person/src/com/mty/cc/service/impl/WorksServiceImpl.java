package com.mty.cc.service.impl;

import com.mty.cc.dao.WorksDao;
import com.mty.cc.po.Works;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("worksService")
@Transactional
public class WorksServiceImpl implements WorksService {

    @Autowired
    private WorksDao worksDao;


    //分页查询
    @Override
    public PageInfo<Works> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Works> pi = new PageInfo<Works>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = worksDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Works> worksList =	worksDao.getWorksList(mp);
            pi.setList(worksList);
        }
        return pi;
    }


    //添加
    @Override
    public int addWorks(Works works) {
        works.setId(RandomIdUtil.getRandomIdByUUID());
        return worksDao.addWorks(works);
    }


    //根据id删除
    @Override
    public int deleteWorks(String id) {
        return worksDao.deleteWorks(id);
    }


    //修改宿舍信息
    @Override
    public int updateWorks(Works works) {
        return worksDao.updateWorks(works);
    }

    //根据ID查询
    @Override
    public Works findWorksById (String id){
        Works d = worksDao.findWorksById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Works> getAll(){
        List<Works> worksList = worksDao.getAll();
        return worksList;
    }

    //按照条件查询
    @Override
    public List<Works> queryFilter(Map mp){
        List<Works> worksList = worksDao.queryFilter(mp);
        return worksList;
    }
}
