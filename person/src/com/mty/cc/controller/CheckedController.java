package com.mty.cc.controller;

import com.mty.cc.po.Checked;
import com.mty.cc.po.PageInfo;
import com.mty.cc.po.User;
import com.mty.cc.service.CheckedService;
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
public class CheckedController {

    // 依赖注入
    @Autowired
    private CheckedService checkedService;
    @Autowired
    private UserService userService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findChecked")
    public String findChecked(Integer pageIndex, Integer pageSize, String username ,Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        mp.put("username",username);
        PageInfo<Checked> pageList = checkedService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        List<User> uLists = userService.getAll();
        model.addAttribute("uLists",uLists);
        return "CheckedList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addChecked" ,method = RequestMethod.POST)
    @ResponseBody
    public String addChecked( @RequestBody Checked checked) {
        int d = checkedService.addChecked(checked);
        return "CheckedList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteChecked")
    @ResponseBody
    public String deleteChecked(String id) {
        int d = checkedService.deleteChecked(id);
        return "CheckedList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateChecked")
    public String updateChecked( Checked checked) {
        int d = checkedService.updateChecked(checked);
        return "redirect:/findChecked";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findCheckedById")
    public String findCheckedById(String id,Model model,HttpServletRequest request) {
        Checked checked= checkedService.findCheckedById(id);
        model.addAttribute("checked",checked);
        List<User> uLists = userService.getAll();
        model.addAttribute("uLists",uLists);
        return "CheckedEdit";
    }


}
