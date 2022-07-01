package com.mty.cc.controller;

import com.mty.cc.po.Notice;
import com.mty.cc.po.PageInfo;
import com.mty.cc.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 控制层
 * @author: mty
 */
@Controller
public class NoticeController {

    // 依赖注入
    @Autowired
    private NoticeService noticeService;


    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findNotice")
    public String findNotice(Integer pageIndex, Integer pageSize, Model model, HttpServletRequest request) {
        PageInfo<Notice> pageList = noticeService.findPageInfo(pageIndex,pageSize);
        model.addAttribute("pageList",pageList);
        return "NoticeList";
    }


    /**
     * 添加
     */
    @RequestMapping(value = "/addNotice" ,method = RequestMethod.POST)
    @ResponseBody
    public String addNotice( @RequestBody Notice notice) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM:dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        notice.setPtime(time);
        int d = noticeService.addNotice(notice);
        return "NoticeList";
    }


    /**
     * 删除
     */
    @RequestMapping( "/deleteNotice")
    @ResponseBody
    public String deleteNotice(String id) {
        int d = noticeService.deleteNotice(id);
        return "NoticeList";
    }


    /**
     * 修改
     */
    @RequestMapping( "/updateNotice")
    public String updateNotice( Notice notice) {
        int d = noticeService.updateNotice(notice);
        return "redirect:/findNotice";
    }


    /**
     * 按照ID查询
     */
    @RequestMapping( "/findNoticeById")
    public String findNoticeById(String id, Model model, HttpServletRequest request) {
        Notice notice= noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "NoticeEdit";
    }


}
