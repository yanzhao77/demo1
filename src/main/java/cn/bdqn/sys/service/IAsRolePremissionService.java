package cn.bdqn.sys.service;

import cn.bdqn.sys.entity.AsRolePremission;
import cn.bdqn.sys.entity.AsUser;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-02
 */
public interface IAsRolePremissionService extends IService<AsRolePremission> {

	public List<AsRolePremission> showRolePremissionById(long roleId);
	public boolean AddOrDelete(String[] list,long roleId,AsUser user);
}
