package com.mty.cc.controller;


import com.mty.cc.po.Admin;
import com.mty.cc.po.Department;
import com.mty.cc.po.Position;
import com.mty.cc.po.User;
import com.mty.cc.service.AdminService;
import com.mty.cc.service.DepartmentService;
import com.mty.cc.service.PositionService;
import com.mty.cc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

	// 依赖注入
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private DepartmentService departmentService;
    /**
     * 去登录
     */
    @RequestMapping(value = "/gologin")
    public String gologin() {
        return "login";
    }

	/**
	 * 登录
	 * 将提交数据(username,password)写入Admin对象
	 */
	@RequestMapping(value = "/login")
	public String login(User user, Model model, HttpSession session, HttpServletRequest request) {
		if(user.getUsername()==null || user.getUsername().length()<=0 ){
			model.addAttribute("msg", "请输入登录名！");
			return "login";
		}
		if(user.getPassword()==null || user.getPassword().length()<1){
			model.addAttribute("msg", "请输入密码！");
			return "login";
		}
		if(user.getType()==null || user.getType().length()<1){
			model.addAttribute("msg", "请选择人员类型！");
			return "login";
		}
		Map mp = new HashMap();
		mp.put("username",user.getUsername());
		mp.put("password",user.getPassword());
		if(user.getType().equals("01")){
			List<Admin> ad = adminService.queryFilter(mp);
			if(ad!=null && ad.size()==1){
				session.setAttribute("ad", ad.get(0));
				session.setAttribute("type", "01");
				return "homepage1";
			}else{
				model.addAttribute("msg", "请确定账户信息是否正确！");
				return "login";
			}
		}else if(user.getType().equals("02")){
			List<Admin> ad = adminService.queryFilter(mp);
			if(ad!=null && ad.size()==1){
				session.setAttribute("ad", ad.get(0));
				session.setAttribute("type", "02");
				return "homepage2";
			}else{
				model.addAttribute("msg", "请确定账户信息是否正确！");
				return "login";
			}
		}else{
			List<User> ad = userService.queryFilter(mp);
			if(ad!=null && ad.size()==1){
				session.setAttribute("ad", ad.get(0));
				session.setAttribute("type", "03");
				return "homepage3";
			}else{
				model.addAttribute("msg", "请确定账户信息是否正确！");
				return "login";
			}
		}
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "login";
	}


	/**
	 * 查询个人信息
	 */
	@RequestMapping(value = "/info")
	public String info(User user, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("ad") == null){
			session.setAttribute("msg", "对不起，请登录！");
			return "login";
		}
		List<Department> dLists = departmentService.getAll();
		model.addAttribute("dLists",dLists);
		List<Position> pLists = positionService.getAll();
		model.addAttribute("pLists",pLists);
		return "queryInfo";
	}



	/**
	 * 进入修改
	 */
	@RequestMapping(value = "/updateInfo")
	public String updateInfo(User user, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("ad") == null){
			session.setAttribute("msg", "对不起，请登录！");
			return "login";
		}
		List<Department> dLists = departmentService.getAll();
		model.addAttribute("dLists",dLists);
		List<Position> pLists = positionService.getAll();
		model.addAttribute("pLists",pLists);
		return "updateInfo";
	}



	/**
	 * 修改信息
	 */
	@RequestMapping( value = "/updateInfoAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String updateInfoAdmin(User user,Model model,HttpServletRequest request,HttpSession session1) {
		HttpSession session = request.getSession();
		if(session.getAttribute("ad") == null){
			session.setAttribute("msg", "对不起，请登录！");
			return "202";
		}
		if(user.getUsername().length()<1){
			return "203";
		}
		if(user.getPassword().length()<1){
			return "204";
		}
		String type = (String) session.getAttribute("type");
		if(type.equals("01") || type.equals("02")){
			Admin admin1 = (Admin) session.getAttribute("ad");
			if(!admin1.getPassword().equals(user.getPassword())){
				return "201";
			}
			if(!"".equals(user.getPasswords())){
				user.setPassword(user.getPasswords());
			}
			Admin admin = new Admin();
			admin.setId(user.getId());
			admin.setUsername(user.getUsername());
			admin.setPassword(user.getPassword());
			int a = adminService.updateAdmin(admin);
		}else{
			if(!isMobile(user.getPhone())){
				return "205";
			}
			User user1 = (User) session.getAttribute("ad");
			if(!user1.getPassword().equals(user.getPassword())){
				return "201";
			}
			if(!"".equals(user.getPasswords())){
				user.setPassword(user.getPasswords());
			}
			int a = userService.updateUser(user);
		}
		return "200";
	}


	/**
	 * 前往注册
	 */
	@RequestMapping(value = "/register")
	public String register(Model model, HttpSession session) {
		return "register";
	}



	/**
	 * 注册校验
	 */
	@RequestMapping(value = "/registerSub")
	public String registerSub( User user, Model model, HttpSession session, HttpServletRequest request) {
		List<User> userList = userService.getAll();
		for(int i = 0;i<userList.size();i++){
			if(userList.get(i).getUsername().equals(user.getUsername())){
				model.addAttribute("msg", "用户名重复，请重新输入！");
				return "register";
			}
		}
		if(user.getUsername()==null || user.getUsername().equals("")){
			model.addAttribute("msg", "用户名未填写！");
			return "register";
		}
		if(user.getPassword()==null || user.getPassword().length()<6){
			model.addAttribute("msg", "密码不能为空且大约6位！");
			return "register";
		}
		if(!isMobile(user.getPhone())){
			model.addAttribute("msg", "手机号格式错误！");
			return "register";
		}
		userService.addUser(user);
		model.addAttribute("msg", "注册成功,请前往登录！");
		return "register";
	}

	/**
	 * 判断是否是手机号
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}


}
