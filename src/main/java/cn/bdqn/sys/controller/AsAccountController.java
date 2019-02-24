package cn.bdqn.sys.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bdqn.sys.entity.AsAccount;
import cn.bdqn.sys.mapper.AsAccountMapper;
import cn.bdqn.sys.service.IAsAccountService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
@Controller
@RequestMapping("/sys/as-account")
public class AsAccountController {

	@Autowired
	private IAsAccountService accountService;
	
	@RequestMapping("/showAccount")
	public String showAcount(long userId,HttpServletRequest req) {
		
		AsAccount AsAccount= accountService.showUserAccount(userId);
		req.setAttribute("account", AsAccount);
		return "main";
	}
}
