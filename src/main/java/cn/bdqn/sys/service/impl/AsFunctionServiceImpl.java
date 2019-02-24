package cn.bdqn.sys.service.impl;

import cn.bdqn.sys.entity.AsFunction;
import cn.bdqn.sys.mapper.AsFunctionMapper;
import cn.bdqn.sys.service.IAsFunctionService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

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
public class AsFunctionServiceImpl extends ServiceImpl<AsFunctionMapper, AsFunction> implements IAsFunctionService {

	@Autowired
	private AsFunctionMapper funMapper;
	
	@Override
	public List<AsFunction> getAllFuntion() {
		QueryWrapper<AsFunction> mapper=new QueryWrapper<AsFunction>();
		mapper.eq("parentId", 0);
		return funMapper.selectList(mapper);
		
	}

	@Override
	public List<AsFunction> getFuntionByParentId(long id) {
		// TODO Auto-generated method stub
		QueryWrapper<AsFunction> mapper=new QueryWrapper<AsFunction>();
		mapper.eq("parentId", id);
		return funMapper.selectList(mapper);
	}

}
