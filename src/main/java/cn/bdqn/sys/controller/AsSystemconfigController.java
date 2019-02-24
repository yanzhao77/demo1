package cn.bdqn.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.bdqn.sys.entity.AsSystemconfig;
import cn.bdqn.sys.service.IAsSystemconfigService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-01
 */
@RestController
@RequestMapping("/sys/as-systemconfig")
public class AsSystemconfigController {
	@Autowired
	private IAsSystemconfigService iAsSystemconfigService;

	@ResponseBody
	@RequestMapping("/modifyconfig.action")
	public String modifyconfig(AsSystemconfig systemConfig) {
		System.out.println("&&&&&&&77");
		boolean flag = iAsSystemconfigService.updateById(systemConfig);
		if (flag) {
			return "success";
		} else {
			return "repeat";
		}
	}

	@ResponseBody
	@RequestMapping("/addconfig.action")
	public String addconfig(AsSystemconfig systemConfig) {
		System.out.println("%%%%%%%%%"+" "+systemConfig.getConfigTypeName()+systemConfig.getConfigTypeValue());
		if(systemConfig.getConfigType()==1) {
			systemConfig.setConfigTypeValue(systemConfig.getConfigType());
		}
		boolean flag = iAsSystemconfigService.addSystemconfig(systemConfig);
		if (flag) {
			return "success";
		} else {
			return "repeat";
		}
	}

	@ResponseBody
	@RequestMapping("/deleteconfig.action")
	public String deleteconfig(Long id) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		boolean flag = iAsSystemconfigService.removeById(id);
		if (flag) {
			return "success";
		} else {
			return "repeat";
		}
	}

	@ResponseBody
	@RequestMapping("/isPeatConfig.action")
	public String isPeatConfig(AsSystemconfig systemConfig) {
		
		AsSystemconfig as = iAsSystemconfigService.getisPeatConfig(systemConfig);
		if (null != as && systemConfig.getId() != as.getId()) {
			return "peat";
		} else {
			return "nopeat";
		}
	}

}
