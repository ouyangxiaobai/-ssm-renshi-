package com.mty.cc.controller;

import com.mty.cc.po.Clock;
import com.mty.cc.po.PageInfo;
import com.mty.cc.po.User;
import com.mty.cc.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 控制层
 * @author: mty
 */
@Controller
public class ClockController {

    // 依赖注入
    @Autowired
    private ClockService clockService;


    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findClock")
    public String findClock(Integer pageIndex, Integer pageSize,String username, Model model,HttpServletRequest request) {
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
        PageInfo<Clock> pageList = clockService.findPageInfo(pageIndex,pageSize,mp);
        model.addAttribute("pageList",pageList);
        Map mps = new HashMap();
        Date date = new Date();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        String time = fmt.format(date);
        mps.put("time",time);
        List<Clock> clocks = clockService.queryFilter(mps);
        if(clocks!=null && clocks.size()==1){
            Clock clock = clocks.get(0);
            if(clock.getQtime()==null || clock.getQtime().equals("")){
                model.addAttribute("status","02");
            }else{
                model.addAttribute("status","03");
            }
        }else{
            model.addAttribute("status","01");
        }
        return "ClockList";
    }


    /**
     * 上班打卡
     */
    @RequestMapping(value = "/addClock" ,method = RequestMethod.POST)
    @ResponseBody
    public String addClock( @RequestBody Clock clock,HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        User user = (User)session.getAttribute("ad");
        clock.setUid(user.getId());
        Date date = new Date();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = fmt.format(date);
        clock.setPtime(time);
        int d = clockService.addClock(clock);
        return "ClockList";
    }

    /**
     * 下班打卡
     */
    @RequestMapping(value = "/appClock" ,method = RequestMethod.POST)
    @ResponseBody
    public String appClock( @RequestBody Clock clock,HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        if(session.getAttribute("ad") == null){
            session.setAttribute("msg", "对不起，请登录！");
            return "login";
        }
        Map mps = new HashMap();
        Date date = new Date();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        DateFormat fmt2 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pp = fmt.format(date);
        String time = fmt2.format(date);
        mps.put("time",pp);
        List<Clock> clocks = clockService.queryFilter(mps);
        if(clocks!=null && clocks.size()==1){
            Clock clockc = clocks.get(0);
            clockc.setQtime(time);
            clockService.updateClock(clockc);
        }
        return "ClockList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteClock")
    @ResponseBody
    public String deleteClock(String id) {
        int d = clockService.deleteClock(id);
        return "ClockList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateClock")
    public String updateClock( Clock clock) {
        int d = clockService.updateClock(clock);
        return "redirect:/findClock";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findClockById")
    public String findClockById(String id,Model model,HttpServletRequest request) {
        Clock clock= clockService.findClockById(id);
        model.addAttribute("clock",clock);
        return "ClockEdit";
    }


}
