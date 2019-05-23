package cn.bdqn.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.bdqn.sys.entity.AsRole;
import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.entity.Userr;
import cn.bdqn.sys.service.IAsRoleService;
import cn.bdqn.sys.service.IAsUserService;
import cn.bdqn.sys.vo.userR;
import cn.bdqn.utils.MD5;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
@Controller
@RequestMapping("/")
public class AsUserController {

	@Autowired
	private IAsUserService userService;
	@Autowired
	private IAsRoleService roleService;

	@RequestMapping("/")
	public String login() {
		return "login";
	}

	@RequestMapping("/login")
	public String login(AsUser user, HttpSession session) {

		System.out.println(user.getUserCode() + "%%%%" + user.getUserPassword());
		AsUser us = userService.login(user);

		if (null != us) {
			session.setAttribute("user", us);
			return "main";
		} else {
			return "login";
		}

	}

	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		AsUser us = (AsUser) session.getAttribute("user");
		userService.updateUserLastLoginTime(us);
		session.removeAttribute("user");
		session.invalidate();
		return "login";
	}

	@RequestMapping("/updateuserpassword")
	@ResponseBody
	public String updatepassword(HttpSession session, String userPassword) {
		
		AsUser user = (AsUser) session.getAttribute("user");
		String p = MD5.MD5Encode(userPassword);
		if (user.getUserPassword().equals(p)) {
			user.setUserPassword(MD5.MD5Encode(userPassword));
			userService.updateById(user);
			return "success";
		} else {
			return "oldpwddif";
		}
	}
	@RequestMapping("/userAllList")
	public String userAllList(HttpServletRequest req, Integer current) {
		userR u=new userR();
		if (current==null) {
			current=1;
		}
		IPage<userR> userPage = userService.getAllUserforPage(u,current);
		req.setAttribute("userPage", userPage);
		req.setAttribute("pageCount", userPage.getPages());
		List<AsRole> roleList = roleService.list();
		req.setAttribute("roleList", roleList);
		
		return "userlist";
	}
   
	
    @ResponseBody
	@RequestMapping("/searchuser")
	public List<AsUser> FuzzySearchUserByUserCode(HttpServletRequest sr,AsUser use) {
		List<AsUser> user = userService.FuzzySearchUserByUserCode(use.getUserCode());
		sr.setAttribute("user", user);
		return user;
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(HttpSession session, AsUser user) {
		System.out.println(user.getUserName());
		int flag = userService.addUser(session, user);
		System.out.println(flag);
		if (flag == 1) {
			return "success";
		} else {
			return "repeat";
		}
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(HttpSession session, AsUser user) {
		System.out.println(user.getUserName());
		int flag = userService.updateUser(session, user);
		System.out.println(flag);
		if (flag == 1) {
			return "success";
		} else {
			return "repeat";
		}
	}
	
	@RequestMapping("/delUser")
	@ResponseBody
	public String delUser(Long id) {
		System.out.println(id);
		int flag = userService.delUser(id);
		System.out.println(flag);
		if (flag == 1) {
			return "success";
		} else {
			return "repeat";
		}
	}
}
