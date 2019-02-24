package cn.bdqn.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bdqn.sys.entity.AsAccount;
import cn.bdqn.sys.entity.AsAccountAll;
import cn.bdqn.sys.entity.AsAccountdetail;
import cn.bdqn.sys.entity.AsAccountdetailAll;
import cn.bdqn.sys.entity.AsKeywords;
import cn.bdqn.sys.entity.AsRole;
import cn.bdqn.sys.entity.AsSystemconfig;
import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.entity.Procteds;
import cn.bdqn.sys.service.IAsAccountAllService;
import cn.bdqn.sys.service.IAsAccountService;
import cn.bdqn.sys.service.IAsAccountdetailService;
import cn.bdqn.sys.service.IAsKeywordsService;
import cn.bdqn.sys.service.IAsRoleService;
import cn.bdqn.sys.service.IAsSystemconfigService;
import cn.bdqn.sys.service.IAsUserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhou
 * @since 2018-12-29
 */
@Controller

public class AsAdminController {
	
	@Autowired
	private IAsUserService userService;
	@Autowired
	private IAsAccountService accountService;
	@Autowired
	private IAsAccountAllService accountAllService;
	@Autowired
	private IAsAccountdetailService adetailService;
	@Autowired
	private IAsKeywordsService asKeywordsService;
	@Autowired
	private AsRoleController asRoleController;
	@Autowired
	private IAsRoleService roleService;
	@Autowired
	private IAsSystemconfigService iAsSystemconfigService;
	@Autowired
	private AsUserController asUserController;
	

	@RequestMapping("/systemconfig")
	public String getsystemconfig(Integer id, HttpServletRequest req) {
		List<AsSystemconfig> systemConfigList = iAsSystemconfigService.getallasSystemconfig(id);
		req.setAttribute("systemConfigList", systemConfigList);
		req.setAttribute("configType", id);
		return "systemconfig";
	}

	@RequestMapping("/caiwutype.action")
	public String caiwutype() {

		return "redirect:/systemconfig?id=1";
	}

	@RequestMapping("/servicetype.action")
	public String servicetype() {

		return "redirect:/systemconfig?id=2";
	}

	@RequestMapping("/serviceyears.action")
	public String serviceyears() {

		return "redirect:/systemconfig?id=3";
	}

	@RequestMapping("/appurl.action")
	public String appurl() {

		return "redirect:/systemconfig?id=4";
	}

	@RequestMapping("/customtype.action")
	public String customtype() {

		return "redirect:/systemconfig?id=5";
	}

	@RequestMapping("/cardtype.action")
	public String cardtype() {

		return "redirect:/systemconfig?id=6";
	}

	@RequestMapping("/youhuitype.action")
	public String youhuitype() {

		return "redirect:/systemconfig?id=7";
	}

	@RequestMapping("/keyword.action")
	public String keyword() {
		return "keyword";
	}

	@RequestMapping("/customlist.action")
	public String customlist() {
		return "customlist";
	}

	@RequestMapping("/addcustom.action")
	public String addcustom() {
		return "addcustom";
	}

	@RequestMapping("/yfk.action")
	public String yfk() {
		return "yfk";
	}

	@RequestMapping("/keywordmanage.action")
	public String keywordmanage() {
		return "keywordmanage";
	}

	@RequestMapping("/mylogs.action")
	public String loglist() {
		return "loglist";
	}

	@RequestMapping("/myportalmanage.action")
	public String modifyportal() {
		return "modifyportal";
	}

	@RequestMapping("/report.action")
	public String report() {
		
		return "report";
	}
	
	@RequestMapping("/reportcheck.action")
	public String reportcheck(HttpServletRequest req) {
		String pid=req.getParameter("reportType");
		String startTime=req.getParameter("startTime");
		String endTime=req.getParameter("endTime");
		int reportType=Integer.valueOf(pid);
		if(reportType==1) {
			List<AsAccountAll> accountList=accountAllService.list();
			req.setAttribute("accountList", accountList);
			}else if(reportType==2||reportType==3){
			List<AsAccountdetailAll> accountDetailList=adetailService.getasAccountdetailList( startTime, endTime );
		    req.setAttribute("accountDetailList", accountDetailList);
		
		}else if(reportType==4) {
			List<Procteds>reportProductList=asKeywordsService.priductsList();
			System.out.println("&&&"+reportProductList);
			req.setAttribute("reportProductList", reportProductList);
		}
		req.setAttribute("reportType", reportType);
		return "/report";
		}

	@RequestMapping("/caiwu.action")
	public String caiwu(HttpServletRequest req) {
		List<AsSystemconfig> sys = iAsSystemconfigService.list();
		List<AsSystemconfig> list = new ArrayList<AsSystemconfig>();
		for (int i = 0; i < sys.size(); i++) {
			if (sys.get(i).getConfigType() == 1) {
				list.add(sys.get(i));
			}
		}
		req.setAttribute("configTypeName", list);

		return "caiwu";
	}

	@RequestMapping("/rolelist.action")
	public String rolelist() {
		return "rolelist";
	}

	@RequestMapping("/premission.action")
	public String premission(Model model) {

		List<AsRole> roleList1 = asRoleController.showList();
		System.out.println(roleList1);
		model.addAttribute("roleList", roleList1);
		return "premission";
	}

	@RequestMapping("/userlist.action")
	public String userList(HttpServletRequest req) {
		List<AsRole> roleList = roleService.list();
		req.setAttribute("roleList", roleList);
		System.out.println(roleList);
		return "userlist";
	}

	@RequestMapping("/checkkeyword.action")
	public String checkkeyword() {
		return "redirect:/sys/as-keywords/checkkeywords";
	}

	@RequestMapping("/xufei.action")
	public String xufei(long id, HttpServletRequest req, HttpSession session) {

		AsUser user = (AsUser) session.getAttribute("user");
		AsAccount userAccount = accountService.showUserAccount(user.getId());
		req.setAttribute("account", userAccount);
		
		AsKeywords keyword = asKeywordsService.getById(id);
		req.setAttribute("keyword", keyword);

		List<AsSystemconfig> sys = iAsSystemconfigService.list();
		List<AsSystemconfig> list = new ArrayList<AsSystemconfig>();
		for (int i = 0; i < sys.size(); i++) {
			if (sys.get(i).getConfigType() == 2) {
				list.add(sys.get(i));
			}
		}
		req.setAttribute("configTypeName", list);	
		return "xufei";
	}
}
