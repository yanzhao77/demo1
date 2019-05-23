package cn.bdqn.sys.service.impl;

import cn.bdqn.sys.entity.AsSystemconfig;
import cn.bdqn.sys.mapper.AsSystemconfigMapper;
import cn.bdqn.sys.service.IAsSystemconfigService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-01
 */
@Service
public class AsSystemconfigServiceImpl extends ServiceImpl<AsSystemconfigMapper, AsSystemconfig>
		implements IAsSystemconfigService {
	@Autowired
	private AsSystemconfigMapper asSystemconfigMapper;

	@Override
	public List<AsSystemconfig> getallasSystemconfig(Integer id) {
		QueryWrapper<AsSystemconfig> wrapper = new QueryWrapper<AsSystemconfig>();
		wrapper.eq("configType", id);
		return asSystemconfigMapper.selectList(wrapper);
	}

	@Override
	public AsSystemconfig getisPeatConfig(AsSystemconfig asSystemconfig) {
		QueryWrapper<AsSystemconfig> wrapper = new QueryWrapper<AsSystemconfig>();
		wrapper.eq("configType", asSystemconfig.getConfigType());
		wrapper.eq("configTypeName", asSystemconfig.getConfigTypeName());
		return asSystemconfigMapper.selectOne(wrapper);
	}

	@Override
	public List<AsSystemconfig> getSystemconfig() {
		QueryWrapper<AsSystemconfig> wrapper = new QueryWrapper<AsSystemconfig>();
		wrapper.eq("configType", 6);
		return asSystemconfigMapper.selectList(wrapper);
	}

	@Override
	public List<AsSystemconfig> AsCustomsList() {
		QueryWrapper<AsSystemconfig> wrapper = new QueryWrapper<AsSystemconfig>();
		wrapper.eq("configType", 5);
		return asSystemconfigMapper.selectList(wrapper);
	}

	@Override
	public boolean addSystemconfig(AsSystemconfig systemconfig) {
		// TODO Auto-generated method stub
		QueryWrapper<AsSystemconfig> wrapper = new QueryWrapper<AsSystemconfig>();

		if (systemconfig.getConfigType() == 1 || systemconfig.getConfigType() == 2 || systemconfig.getConfigType() == 5 || systemconfig.getConfigType() == 6) {
			wrapper.eq("configType", systemconfig.getConfigType());
			List<AsSystemconfig> systemList = asSystemconfigMapper.selectList(wrapper);
			int num = 0;
			for (int i = 0; i < systemList.size(); i++) {
				if (systemList.get(i).getConfigTypeValue() >= 0) {
					num = systemList.get(i).getConfigTypeValue();
					System.out.println(num);
				}
			}
			systemconfig.setConfigTypeValue(num + 1);
		}

		int flag = asSystemconfigMapper.insert(systemconfig);
		if (flag == 1) {
			return true;
		}
		return false;
	}

}
