package cn.bdqn.sys.service;

import cn.bdqn.sys.entity.AsSystemconfig;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-01
 */
public interface IAsSystemconfigService extends IService<AsSystemconfig> {
	
	List<AsSystemconfig> getallasSystemconfig(Integer id);

	public AsSystemconfig getisPeatConfig(AsSystemconfig asSystemconfig);

	public List<AsSystemconfig> getSystemconfig();

	public List<AsSystemconfig> AsCustomsList();
	
	public boolean addSystemconfig(AsSystemconfig systemconfig);
}
