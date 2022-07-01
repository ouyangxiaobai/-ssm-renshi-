package com.mty.cc.controller;

import com.mty.cc.po.Department;
import com.mty.cc.po.Salary;
import com.mty.cc.po.PageInfo;
import com.mty.cc.po.User;
import com.mty.cc.service.SalaryService;
import com.mty.cc.service.UserService;
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
public class SalaryController {

    // 依赖注入
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private UserService userService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findSalary")
    public String findSalary(Integer pageIndex, Integer pageSize,String username, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        mp.put("username",username);
        String type = (String)session.getAttribute("type");
        if(type.equals("03")){
            User user = (User)session.getAttribute("ad");
            mp.put("uid",user.getId());
        }
        PageInfo<Salary> pageList = salaryService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        List<User> uLists = userService.getAll();
        model.addAttribute("uLists",uLists);
        return "SalaryList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addSalary" ,method = RequestMethod.POST)
    @ResponseBody
    public String addSalary( @RequestBody Salary salary) {
        int d = salaryService.addSalary(salary);
        return "SalaryList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteSalary")
    @ResponseBody
    public String deleteSalary(String id) {
        int d = salaryService.deleteSalary(id);
        return "SalaryList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateSalary")
    public String updateSalary( Salary salary) {
        int d = salaryService.updateSalary(salary);
        return "redirect:/findSalary";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findSalaryById")
    public String findSalaryById(String id,Model model,HttpServletRequest request) {
        Salary salary= salaryService.findSalaryById(id);
        model.addAttribute("salary",salary);
        List<User> uLists = userService.getAll();
        model.addAttribute("uLists",uLists);
        return "SalaryEdit";
    }


}
