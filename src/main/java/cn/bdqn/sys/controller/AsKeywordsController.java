package cn.bdqn.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.bdqn.sys.entity.AsAccount;
import cn.bdqn.sys.entity.AsKeywords;
import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.service.IAsAccountService;
import cn.bdqn.sys.service.IAsKeywordsService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-01
 */
@Controller
@RequestMapping("/sys/as-keywords")
public class AsKeywordsController {
	@Autowired
	private IAsAccountService accountService;
	@Autowired
	private IAsKeywordsService asKeywordsService;

	@RequestMapping("/checkkeywords")
	public String checkkeywordByName(String keywords, HttpServletRequest req, Page<AsKeywords> page1) {
		IPage<AsKeywords> page = asKeywordsService.FuzzySearchAsKeywordsList(keywords, page1);
		req.setAttribute("page", page);
		req.setAttribute("pageCount", page.getPages());
		req.setAttribute("oldkeywords", keywords);
		return "checkkeyword";
	}

	@RequestMapping("/updatekeyword")
	@ResponseBody
	public String updatekeywordOnCheckStatus(AsKeywords keywords) {

		boolean flag = asKeywordsService.updatekeywordOnCheckStatus(keywords);
		if (flag) {
			return "success";
		} else {
			return "error";
		}
	}

	@RequestMapping("/jisuan")
	@ResponseBody
	public String AutoCalculatedPrice(String p) {
		try {
			int sertype = Integer.parseInt(p.split("-")[0]);
			int year = Integer.parseInt(p.split("-")[1]);
			Integer total = sertype * year;
			return String.valueOf(total);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}

	@RequestMapping("/keywordsxufei")
	@ResponseBody
	public String keywordsxufeiByKeywordsId(String p, long id, HttpSession session) {
		int sertype = Integer.parseInt(p.split("-")[0]);
		int year = Integer.parseInt(p.split("-")[1]);
		Integer total = sertype * year;

		AsUser user = (AsUser) session.getAttribute("user");
		AsAccount userAccount = accountService.showUserAccount(user.getId());

		if (userAccount.getMoney() < total) {
			return "nomoney";
		}
		AsKeywords keyword = asKeywordsService.getById(id);

		boolean flag = asKeywordsService.KeywordsRenew(user.getId(), total, id, year, sertype);
		if (flag) {
			AsAccount userAcc= accountService.showUserAccount(user.getId());
			return String.valueOf(userAcc.getMoney());
		}
		return "exception";
	}

}
