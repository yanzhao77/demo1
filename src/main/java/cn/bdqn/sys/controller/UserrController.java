package cn.bdqn.sys.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.bdqn.sys.entity.AsRole;
import cn.bdqn.sys.entity.Userr;
import cn.bdqn.sys.service.IAsRoleService;
import cn.bdqn.sys.service.IAsUserService;
import cn.bdqn.sys.service.IUserrService;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-04
 */
@Controller
@RequestMapping("/sys/userr")
public class UserrController {

	@Autowired
	private IUserrService userService;
	@Autowired
	private IAsRoleService roleService;
	
	@RequestMapping("/findUser")
	public String findUser(HttpServletRequest req,Userr user1,Page page1) {
		IPage userPage = userService.findUser(user1,page1);
		req.setAttribute("userPage", userPage);
		req.setAttribute("pageCount", userPage.getPages());
		List<AsRole> roleList = roleService.list();
		req.setAttribute("roleList", roleList);
		req.setAttribute("oldUser",user1);
		return "userlist";
	}
	
	
	
}
