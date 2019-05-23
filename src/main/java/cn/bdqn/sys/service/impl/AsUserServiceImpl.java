package cn.bdqn.sys.service.impl;

import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.entity.Userr;
import cn.bdqn.sys.mapper.AsUserMapper;
import cn.bdqn.sys.mapper.AsUserRMapper;
import cn.bdqn.sys.mapper.UserrMapper;
import cn.bdqn.sys.service.IAsUserService;
import cn.bdqn.sys.vo.userR;
import cn.bdqn.utils.MD5;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
@Service
public class AsUserServiceImpl extends ServiceImpl<AsUserMapper, AsUser> implements IAsUserService {

	@Autowired
	private AsUserRMapper urMapper;
	@Autowired
	private AsUserMapper userMapper;

	@Override
	public AsUser login(AsUser user) {
		// TODO Auto-generated method stub
		QueryWrapper<AsUser> mapper = new QueryWrapper<AsUser>();
		mapper.eq("userCode", user.getUserCode());
		mapper.eq("userPassword", MD5.MD5Encode(user.getUserPassword()));
		AsUser use = userMapper.selectOne(mapper);

		return use;
	}

	@Override
	public List<AsUser> FuzzySearchUserByUserCode(String FuzzyUserCode) {
		if (FuzzyUserCode == "" || FuzzyUserCode == null) {
			return null;
		}
		// TODO Auto-generated method stub
		QueryWrapper<AsUser> mapper = new QueryWrapper<AsUser>();
		mapper.like("userCode", FuzzyUserCode);
		List<AsUser> userList = userMapper.selectList(mapper);
		return userList;
	}

	@Override
	public IPage<userR> getAllUserforPage(userR u, Integer page) {
		// TODO Auto-generated method stub
		Page<userR> page1 = new Page<>();
		page1.setRecords(urMapper.showUserListAndRoleName(page1, u));
		return page1;

	}

	@Override
	public int updateUser(HttpSession session, AsUser user) {
		// TODO Auto-generated method stub

		AsUser use = (AsUser) session.getAttribute("user");
		user.setCreatedBy(use.getUserCode());
		user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
		Date date = new Date();
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime lastUpdateTime = instant.atZone(zoneId).toLocalDateTime();
		user.setLastUpdateTime(lastUpdateTime);
		return userMapper.updateById(user);
	}

	@Override
	public int addUser(HttpSession session, AsUser user) {
		// TODO Auto-generated method stub

		AsUser use = (AsUser) session.getAttribute("user");
		user.setCreatedBy(use.getUserCode());
		user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
		Date date = new Date();
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		user.setCreationTime(localDateTime);
		return userMapper.insert(user);
	}

	@Override
	public int delUser(Long id) {
		// TODO Auto-generated method stub

		return userMapper.deleteById(id);
	}

	@Override
	public int updateUserLastLoginTime(AsUser user) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime lastLoginTime = instant.atZone(zoneId).toLocalDateTime();
		user.setLastLoginTime(lastLoginTime);
		return userMapper.updateById(user);

	}

}
