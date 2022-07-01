package com.mty.cc.controller;

import com.mty.cc.po.Recruit;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 控制层
 * @author: mty
 */
@Controller
public class RecruitController {

    // 依赖注入
    @Autowired
    private RecruitService recruitService;


    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findRecruit")
    public String findRecruit(Integer pageIndex, Integer pageSize, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        PageInfo<Recruit> pageList = recruitService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        return "RecruitList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addRecruit" ,method = RequestMethod.POST)
    @ResponseBody
    public String addRecruit( @RequestBody Recruit recruit) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        recruit.setPtime(time);
        int d = recruitService.addRecruit(recruit);
        return "RecruitList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteRecruit")
    @ResponseBody
    public String deleteRecruit(String id) {
        int d = recruitService.deleteRecruit(id);
        return "RecruitList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateRecruit")
    public String updateRecruit( Recruit recruit) {
        int d = recruitService.updateRecruit(recruit);
        return "redirect:/findRecruit";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findRecruitById")
    public String findRecruitById(String id,Model model,HttpServletRequest request) {
        Recruit recruit= recruitService.findRecruitById(id);
        model.addAttribute("recruit",recruit);
        return "RecruitEdit";
    }


}
