package com.mty.cc.controller;

import com.mty.cc.po.Department;
import com.mty.cc.po.Position;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.DepartmentService;
import com.mty.cc.service.PositionService;
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
public class PositionController {

    // 依赖注入
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findPosition")
    public String findPosition(Integer pageIndex, Integer pageSize,String name, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        mp.put("name",name);
        PageInfo<Position> pageList = positionService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        List<Department> dLists = departmentService.getAll();
        model.addAttribute("dLists",dLists);
        return "PositionList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addPosition" ,method = RequestMethod.POST)
    @ResponseBody
    public String addPosition( @RequestBody Position position) {
        int d = positionService.addPosition(position);
        return "PositionList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deletePosition")
    @ResponseBody
    public String deletePosition(String id) {
        int d = positionService.deletePosition(id);
        return "PositionList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updatePosition")
    public String updatePosition( Position position) {
        int d = positionService.updatePosition(position);
        return "redirect:/findPosition";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findPositionById")
    public String findPositionById(String id,Model model,HttpServletRequest request) {
        Position position= positionService.findPositionById(id);
        model.addAttribute("position",position);
        List<Department> dLists = departmentService.getAll();
        model.addAttribute("dLists",dLists);
        return "PositionEdit";
    }


}
