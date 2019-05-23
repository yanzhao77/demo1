package cn.bdqn.sys.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bdqn.sys.entity.AsFunction;
import cn.bdqn.sys.service.IAsFunctionService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
@Controller
@RequestMapping("/sys/as-function")
public class AsFunctionController {

	@Autowired
	private IAsFunctionService funService;

	@RequestMapping("/funtionList")
	public String getFuntion(HttpSession session) {
		List<AsFunction> functionName = funService.getAllFuntion();
		int i = 0;
		for (AsFunction asFunction : functionName) {
			i++;
			List<AsFunction> subFunctions = funService.getFuntionByParentId(asFunction.getId());
			for (AsFunction asFunction2 : subFunctions) {

				session.setAttribute("subFunctions" + i, subFunctions);
			}
		}
		session.setAttribute("functionName", functionName);
		return "main";
	}

	public List<AsFunction> functionList(){
		List<AsFunction> functionList = funService.list();
		return functionList;
	}
}
