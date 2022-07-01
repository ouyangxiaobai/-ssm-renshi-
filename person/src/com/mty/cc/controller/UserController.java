package com.mty.cc.controller;

import com.mty.cc.po.*;
import com.mty.cc.service.DepartmentService;
import com.mty.cc.service.PositionService;
import com.mty.cc.service.UserService;
import com.sun.xml.internal.ws.dump.LoggingDumpTube;
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
public class UserController {

    // 依赖注入
    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findUser")
    public String findUser(Integer pageIndex, Integer pageSize,String username, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mp = new HashMap();
        mp.put("username",username);
        PageInfo<User> pageList = userService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        List<Department> dLists = departmentService.getAll();
        model.addAttribute("dLists",dLists);
        List<Position> pLists = positionService.getAll();
        model.addAttribute("pLists",pLists);
        return "UserList";
    }


    /**
     * 根据上级查找下级
     */
    @RequestMapping(value = "/querySmall" ,method = RequestMethod.POST)
    @ResponseBody
    public List<DicVO> querySmall(@RequestBody String did) {
        String str1=did.substring(0, did.indexOf("="));
        String str2=did.substring(str1.length()+1, did.length());
        Map mp = new HashMap();
        mp.put("did",str2);
        List<Position> smalls = positionService.queryFilter(mp);
        List<DicVO> dicVOS = new ArrayList<DicVO>();
        if(smalls!=null && smalls.size()>0){
            for(int i = 0;i<smalls.size();i++){
                DicVO dicVO = new DicVO();
                dicVO.setId(smalls.get(i).getId());
                dicVO.setName(smalls.get(i).getName());
                dicVOS.add(dicVO);
            }
        }
        return dicVOS;
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST,produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String addUser( @RequestBody User user,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        List<User> userList = userService.getAll();
        for(int i = 0;i<userList.size();i++){
            if(userList.get(i).getUsername().equals(user.getUsername())){
                return "用户名重复，请重新输入！";
            }
        }
        int d = userService.addUser(user);
        return "200";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteUser")
    @ResponseBody
    public String deleteUser(String id) {
        int d = userService.deleteUser(id);
        return "UserList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateUser")
    public String updateUser( User user) {
        int d = userService.updateUser(user);
        return "redirect:/findUser";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findUserById")
    public String findUserById(String id,Model model,HttpServletRequest request) {
        User user= userService.findUserById(id);
        model.addAttribute("user",user);
        List<Department> dLists = departmentService.getAll();
        model.addAttribute("dLists",dLists);
        List<Position> pLists = positionService.getAll();
        model.addAttribute("pLists",pLists);
        return "UserEdit";
    }


}
