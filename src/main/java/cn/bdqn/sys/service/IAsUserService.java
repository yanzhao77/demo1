package cn.bdqn.sys.service;

import cn.bdqn.sys.entity.AsRole;
import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.entity.Userr;
import cn.bdqn.sys.vo.userR;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
public interface IAsUserService extends IService<AsUser> {

	public AsUser login(AsUser user);

	public IPage<userR> getAllUserforPage(userR u,Integer page);
	

	public List<AsUser> FuzzySearchUserByUserCode(String FuzzyUserCode);

	public int updateUser(HttpSession session, AsUser user);

	public int addUser(HttpSession session, AsUser user);

	public int delUser(Long id);
	
	public int updateUserLastLoginTime(AsUser user);
}
