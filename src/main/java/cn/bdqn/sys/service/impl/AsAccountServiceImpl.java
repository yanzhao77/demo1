package cn.bdqn.sys.service.impl;

import cn.bdqn.sys.entity.AsAccount;
import cn.bdqn.sys.mapper.AsAccountMapper;
import cn.bdqn.sys.service.IAsAccountService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
@Service
public class AsAccountServiceImpl extends ServiceImpl<AsAccountMapper, AsAccount> implements IAsAccountService {

	@Autowired
	private AsAccountMapper accountMapper;

	@Override
	public AsAccount showUserAccount(Long userId) {
		// TODO Auto-generated method stub
		QueryWrapper<AsAccount> mapper= new QueryWrapper<AsAccount>();
		mapper.eq("userId", userId);
		return accountMapper.selectOne(mapper);
	}
}
