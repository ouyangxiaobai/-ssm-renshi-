package com.mty.cc.controller;

import com.mty.cc.po.User;
import com.mty.cc.po.Works;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.UserService;
import com.mty.cc.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 控制层
 * @author: mty
 */
@Controller
public class WorksController {

    // 依赖注入
    @Autowired
    private WorksService worksService;
    @Autowired
    private UserService userService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findWorks")
    public String findWorks(Integer pageIndex, Integer pageSize,String username, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        mp.put("username",username);
        PageInfo<Works> pageList = worksService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        List<User> uLists = userService.getAll();
        model.addAttribute("uLists",uLists);
        return "WorksList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addWorks" ,method = RequestMethod.POST)
    @ResponseBody
    public String addWorks( @RequestBody Works works) {
        int d = worksService.addWorks(works);
        return "WorksList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteWorks")
    @ResponseBody
    public String deleteWorks(String id) {
        int d = worksService.deleteWorks(id);
        return "WorksList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateWorks")
    public String updateWorks( Works works) {
        int d = worksService.updateWorks(works);
        return "redirect:/findWorks";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findWorksById")
    public String findWorksById(String id,Model model,HttpServletRequest request) {
        Works works= worksService.findWorksById(id);
        model.addAttribute("works",works);
        List<User> uLists = userService.getAll();
        model.addAttribute("uLists",uLists);
        return "WorksEdit";
    }


}
