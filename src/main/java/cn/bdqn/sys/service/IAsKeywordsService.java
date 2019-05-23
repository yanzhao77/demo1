package cn.bdqn.sys.service;

import cn.bdqn.sys.entity.AsKeywords;
import cn.bdqn.sys.entity.AsUser;
import cn.bdqn.sys.entity.Procteds;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-01
 */
public interface IAsKeywordsService extends IService<AsKeywords> {
	
	public List<Procteds> priductsList();
	public IPage<AsKeywords> FuzzySearchAsKeywordsList(String keywords,Page<AsKeywords> IPage1);
	public boolean updatekeywordOnCheckStatus(AsKeywords keywords);
	public boolean KeywordsRenew(long userId,Integer total,long Kid,int serviceyear,int serviceConfigValue);
}
