package cn.bdqn.sys.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.sys.entity.AsRole;
import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.service.IAsRoleService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-01
 */
@Controller
@RequestMapping("/sys/as-role")
public class AsRoleController {

	@Autowired
	private IAsRoleService rService;

	/*
	 * @RequestMapping("/roleList")
	public String roleList(HttpServletRequest rs) {
		List<AsRole> roleList = rService.list();
		rs.setAttribute("roleList", roleList);
		System.out.println(roleList);
		return "userlist";
	}

	@RequestMapping("/roleList1")
	public String showList1(HttpServletRequest req) {
		List<AsRole> roleList = rService.list();
		System.out.println(roleList);
		req.setAttribute("roleList",roleList);
		return "premission";
		
	}
	*/
	
	public List<AsRole> showList() {
		List<AsRole> roleList = rService.list();
		return roleList;
	}
	@RequestMapping("/showRole")
	public String showRole(HttpServletRequest rs) {
		List<AsRole> roleList = rService.list();
		rs.setAttribute("roleList", roleList);
		System.out.println(roleList);
		return "rolelist";
	}

	@RequestMapping("/addRole")
	@ResponseBody
	public String addRole(AsRole role, HttpSession session) {

		AsUser user = (AsUser) session.getAttribute("user");
		role.setCreatedBy(user.getUserCode());

		Date date = new Date();
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		role.setCreationTime(localDateTime);

		boolean flag = rService.save(role);
		System.out.println(flag);
		if (flag) {
			return "success";
		} else {
			return "repeat";
		}
	}

	@RequestMapping("/updateRole")
	@ResponseBody
	public String updateRole(AsRole role) {
		boolean flag = rService.updateById(role);
		if (flag) {
			return "success";
		} else {
			return "repeat";
		}
	}

	@RequestMapping("/delrole")
	@ResponseBody
	public String delrole(Long id) {
		boolean flag = rService.removeById(id);
		if (flag) {
			return "success";
		} else {
			return "failed";
		}
	}

}
