package com.mty.cc.controller;

import com.mty.cc.po.Department;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.DepartmentService;
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
public class DepartmentController {

    // 依赖注入
    @Autowired
    private DepartmentService departmentService;


    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findDepartment")
    public String findDepartment(Integer pageIndex, Integer pageSize,String name, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        mp.put("name",name);
        PageInfo<Department> pageList = departmentService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        return "DepartmentList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addDepartment" ,method = RequestMethod.POST)
    @ResponseBody
    public String addDepartment( @RequestBody Department department) {
        int d = departmentService.addDepartment(department);
        return "DepartmentList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteDepartment")
    @ResponseBody
    public String deleteDepartment(String id) {
        int d = departmentService.deleteDepartment(id);
        return "DepartmentList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateDepartment")
    public String updateDepartment( Department department) {
        int d = departmentService.updateDepartment(department);
        return "redirect:/findDepartment";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findDepartmentById")
    public String findDepartmentById(String id,Model model,HttpServletRequest request) {
        Department department= departmentService.findDepartmentById(id);
        model.addAttribute("department",department);
        return "DepartmentEdit";
    }


}
