package com.mty.cc.controller;

import com.mty.cc.po.Leaves;
import com.mty.cc.po.PageInfo;
import com.mty.cc.po.User;
import com.mty.cc.service.LeavesService;
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
public class LeavesController {

    // 依赖注入
    @Autowired
    private LeavesService leavesService;


    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findLeaves")
    public String findLeaves(Integer pageIndex, Integer pageSize, String username,Model model,HttpServletRequest request) {
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
        PageInfo<Leaves> pageList = leavesService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        return "LeavesList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addLeaves" ,method = RequestMethod.POST)
    @ResponseBody
    public String addLeaves( @RequestBody Leaves leaves,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        String type = (String)session.getAttribute("type");
        if(type.equals("03")){
            User user = (User)session.getAttribute("ad");
            leaves.setUid(user.getId());
            leaves.setStatus("01");
        }
        int d = leavesService.addLeaves(leaves);
        return "LeavesList";
    }

    /**
     * 更新状态
     */
    @RequestMapping( "/updateStatus")
    @ResponseBody
    public String updateStatus(String id, String status) {
        try{
            Leaves leaves = new Leaves();
            leaves.setId(id);
            leaves.setStatus(status);
            int d = leavesService.updateLeaves(leaves);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 删除
     */
    @RequestMapping( "/deleteLeaves")
    @ResponseBody
    public String deleteLeaves(String id) {
        int d = leavesService.deleteLeaves(id);
        return "LeavesList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateLeaves")
    public String updateLeaves( Leaves leaves) {
        int d = leavesService.updateLeaves(leaves);
        return "redirect:/findLeaves";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findLeavesById")
    public String findLeavesById(String id,Model model,HttpServletRequest request) {
        Leaves leaves= leavesService.findLeavesById(id);
        model.addAttribute("leaves",leaves);
        return "LeavesEdit";
    }


}
