package cn.bdqn.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.bdqn.sys.entity.AsAccount;
import cn.bdqn.sys.entity.AsAccountdetail;
import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.service.IAsAccountdetailService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
@Controller
@RequestMapping("/sys/as-accountdetail")
public class AsAccountdetailController {

	@Autowired
	private IAsAccountdetailService adetailService;

	@RequestMapping("/accountdetail")
	public String showUserAccount(HttpSession session,HttpServletRequest request, Page page1) {
		AsUser user=(AsUser) session.getAttribute("user");
		IPage page = page1;
		page = adetailService.getasAccountdetail(page1, user.getId());
		request.setAttribute("page", page);
		request.setAttribute("pages",page.getPages());
		return "accountdetail";
	}
	
	@RequestMapping("/opeaccount")
	@ResponseBody
	public String addAccountdetail(AsAccountdetail accountdetail,HttpSession session) {
		System.out.println(accountdetail);
		boolean flag=adetailService.addAccountdetail(accountdetail);
		if(flag) {
			return "success";
		}
		return null;
	
	}
}
