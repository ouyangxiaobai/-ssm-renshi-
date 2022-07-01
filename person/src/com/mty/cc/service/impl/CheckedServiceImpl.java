package com.mty.cc.service.impl;

import com.mty.cc.dao.CheckedDao;
import com.mty.cc.po.Checked;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.CheckedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.mty.cc.util.*;

/**
 * 用户Service接口实现类
 * @author: mty
 */
@Service("checkedService")
@Transactional
public class CheckedServiceImpl implements CheckedService {

    @Autowired
    private CheckedDao checkedDao;


    //分页查询
    @Override
    public PageInfo<Checked> findPageInfo(Integer pageIndex, Integer pageSize,Map mp) {
        PageInfo<Checked> pi = new PageInfo<Checked>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        mp.put("currentPage",(pi.getPageIndex()-1)*pi.getPageSize());
        mp.put("pageSize",pi.getPageSize());
        //获取总条数
        Integer totalCount = checkedDao.totalCount(mp);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //按条件查询
            List<Checked> checkedList =	checkedDao.getCheckedList(mp);
            pi.setList(checkedList);
        }
        return pi;
    }


    //添加
    @Override
    public int addChecked(Checked checked) {
        checked.setId(RandomIdUtil.getRandomIdByUUID());
        return checkedDao.addChecked(checked);
    }


    //根据id删除
    @Override
    public int deleteChecked(String id) {
        return checkedDao.deleteChecked(id);
    }


    //修改宿舍信息
    @Override
    public int updateChecked(Checked checked) {
        return checkedDao.updateChecked(checked);
    }

    //根据ID查询
    @Override
    public Checked findCheckedById (String id){
        Checked d = checkedDao.findCheckedById(id);
        return  d;
    }

    //查询所有
    @Override
    public List<Checked> getAll(){
        List<Checked> checkedList = checkedDao.getAll();
        return checkedList;
    }

    //按照条件查询
    @Override
    public List<Checked> queryFilter(Map mp){
        List<Checked> checkedList = checkedDao.queryFilter(mp);
        return checkedList;
    }
}
