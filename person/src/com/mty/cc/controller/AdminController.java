package com.mty.cc.controller;

import com.mty.cc.po.Admin;
import com.mty.cc.po.PageInfo;
import com.mty.cc.po.User;
import com.mty.cc.service.AdminService;
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
public class AdminController {

    // 依赖注入
    @Autowired
    private AdminService adminService;


    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findAdmin")
    public String findAdmin(Integer pageIndex, Integer pageSize, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        PageInfo<Admin> pageList = adminService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        return "AdminList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addAdmin" ,method = RequestMethod.POST,produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String addAdmin( @RequestBody Admin admin,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        List<Admin> adminList = adminService.getAll();
        for(int i = 0;i<adminList.size();i++){
            if(adminList.get(i).getUsername().equals(admin.getUsername())){
                return "用户名重复，请重新输入！";
            }
        }
        admin.setType("02");
        int d = adminService.addAdmin(admin);
        return "200";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteAdmin")
    @ResponseBody
    public String deleteAdmin(String id) {
        int d = adminService.deleteAdmin(id);
        return "AdminList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateAdmin")
    public String updateAdmin( Admin admin) {
        int d = adminService.updateAdmin(admin);
        return "redirect:/findAdmin";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findAdminById")
    public String findAdminById(String id,Model model,HttpServletRequest request) {
        Admin admin= adminService.findAdminById(id);
        model.addAttribute("admin",admin);
        return "AdminEdit";
    }


}
