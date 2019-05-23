package cn.bdqn.sys.service.impl;

import cn.bdqn.sys.entity.AsAccount;
import cn.bdqn.sys.entity.AsAccountdetail;
import cn.bdqn.sys.entity.AsKeywords;
import cn.bdqn.sys.entity.AsSystemconfig;
import cn.bdqn.sys.entity.Procteds;
import cn.bdqn.sys.mapper.AsAccountMapper;
import cn.bdqn.sys.mapper.AsAccountdetailMapper;
import cn.bdqn.sys.mapper.AsKeywordsMapper;
import cn.bdqn.sys.mapper.AsSystemconfigMapper;
import cn.bdqn.sys.mapper.ProctedsMapper;
import cn.bdqn.sys.service.IAsKeywordsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-01
 */
@Service
public class AsKeywordsServiceImpl extends ServiceImpl<AsKeywordsMapper, AsKeywords> implements IAsKeywordsService {
	@Autowired
	private AsAccountdetailMapper adeMapper;
	@Autowired
	private AsAccountMapper accMapper;
	@Autowired
	private AsKeywordsMapper keywordsMapper;
	@Autowired
	private AsSystemconfigMapper asSystemconfigMapper;
	@Autowired
	private ProctedsMapper pproctedsMapper;

	@Override
	public IPage<AsKeywords> FuzzySearchAsKeywordsList(String keywords, Page<AsKeywords> IPage1) {

		QueryWrapper<AsKeywords> mapper = new QueryWrapper<AsKeywords>();
		if (keywords != null && keywords != "") {
			mapper.like("keywords", keywords);
		}
		return keywordsMapper.selectPage(IPage1, mapper);

	}

	@Override
	public boolean updatekeywordOnCheckStatus(AsKeywords keywords) {
		// TODO Auto-generated method stub
		QueryWrapper<AsKeywords> mapper = new QueryWrapper<AsKeywords>();
		int i = keywordsMapper.updateById(keywords);
		if (i == 1) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean KeywordsRenew(long userId, Integer total, long Kid, int serviceyear, int serviceConfigValue) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		// 取出configType的属性和名字
		try {
			QueryWrapper<AsSystemconfig> asSystemMapper = new QueryWrapper<AsSystemconfig>();
			asSystemMapper.eq("configValue", serviceConfigValue);
			AsSystemconfig asSystem = asSystemconfigMapper.selectOne(asSystemMapper);
			// 扣除当前账户的的费用
			QueryWrapper<AsAccount> asAccMapper = new QueryWrapper<AsAccount>();
			asAccMapper.eq("userId", userId);
			AsAccount us = accMapper.selectOne(asAccMapper);
			us.setMoney(us.getMoney() - total);
			int i = accMapper.updateById(us);
			// 生成订单信息
			QueryWrapper<AsAccountdetail> acdetailMapper = new QueryWrapper<AsAccountdetail>();
			AsAccountdetail accountdetail = new AsAccountdetail();
			accountdetail.setUserId(us.getUserId());
			accountdetail.setAccountMoney(us.getMoney());
			accountdetail.setDetailType((long) 9996);
			accountdetail.setDetailTypeName("扣除关键词续费资金");
			accountdetail.setMoney((double) total);
			accountdetail.setAccountMoney(us.getMoney());
			accountdetail.setMemo("Id号："+accountdetail.getDetailType()+"，名称："+accountdetail.getDetailTypeName()+ "，充值金额：" + total + "，userId：" + userId + "，AsKeywordsID：" + Kid);
			// 插入一条账单
			LocalDateTime detailDateTime = instant.atZone(zoneId).toLocalDateTime();
			accountdetail.setDetailDateTime(detailDateTime);
			int j = adeMapper.insert(accountdetail);

			QueryWrapper<AsKeywords> asKeywordMapper = new QueryWrapper<AsKeywords>();
			AsKeywords asKeyword = keywordsMapper.selectById(Kid);
			asKeyword.setAgentId(us.getUserId());
			asKeyword.setPrice((double)total);
			asKeyword.setServiceYears(asKeyword.getServiceYears()+serviceyear);// 申请使用时间
			asKeyword.setIsPass(0);
			asKeyword.getRegPassDatetime();// 到期时间
			// 在原来的时间上延长2年时间
			System.out.println(asKeyword.getRegPassDatetime());
					asKeyword.setRegPassDatetime(asKeyword.getRegPassDatetime().plusYears(serviceyear));
					System.out.println("@@@@@@@@@@@@@@@@@@"+asKeyword.getRegPassDatetime());
			int r = keywordsMapper.updateById(asKeyword);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Procteds> priductsList() {
		// TODO Auto-generated method stub
		QueryWrapper<Procteds> wrapper=new QueryWrapper<Procteds>();
		return pproctedsMapper.selectList(wrapper);
	}

}
