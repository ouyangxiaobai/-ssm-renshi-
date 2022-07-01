package com.mty.cc.service.impl;

import com.mty.cc.dao.LeavesDao;
import com.mty.cc.po.Leaves;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.LeavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("leavesService")
@Transactional
public class LeavesServiceImpl implements LeavesService {

    @Autowired
    private LeavesDao leavesDao;


    //分页查询
    @Override
    public PageInfo<Leaves> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Leaves> pi = new PageInfo<Leaves>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = leavesDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Leaves> leavesList =	leavesDao.getLeavesList(mp);
            pi.setList(leavesList);
        }
        return pi;
    }


    //添加
    @Override
    public int addLeaves(Leaves leaves) {
        leaves.setId(RandomIdUtil.getRandomIdByUUID());
        return leavesDao.addLeaves(leaves);
    }


    //根据id删除
    @Override
    public int deleteLeaves(String id) {
        return leavesDao.deleteLeaves(id);
    }


    //修改宿舍信息
    @Override
    public int updateLeaves(Leaves leaves) {
        return leavesDao.updateLeaves(leaves);
    }

    //根据ID查询
    @Override
    public Leaves findLeavesById (String id){
        Leaves d = leavesDao.findLeavesById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Leaves> getAll(){
        List<Leaves> leavesList = leavesDao.getAll();
        return leavesList;
    }

    //按照条件查询
    @Override
    public List<Leaves> queryFilter(Map mp){
        List<Leaves> leavesList = leavesDao.queryFilter(mp);
        return leavesList;
    }
}
