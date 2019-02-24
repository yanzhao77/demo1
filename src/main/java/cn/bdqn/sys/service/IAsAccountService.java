package cn.bdqn.sys.service;

import cn.bdqn.sys.entity.AsAccount;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
public interface IAsAccountService extends IService<AsAccount> {

	public AsAccount showUserAccount(Long userId);
	
}
