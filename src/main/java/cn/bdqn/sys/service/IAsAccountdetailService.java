package cn.bdqn.sys.service;

import cn.bdqn.sys.entity.AsAccount;
import cn.bdqn.sys.entity.AsAccountdetail;
import cn.bdqn.sys.entity.AsAccountdetailAll;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanzhao
 * @since 2018-12-31
 */
public interface IAsAccountdetailService extends IService<AsAccountdetail> {

	public IPage getasAccountdetail(IPage arg0,long userId);
	public List<AsAccountdetailAll> getasAccountdetailList(String startTime,String endTime );
	public boolean addAccountdetail(AsAccountdetail accountdetail);
	
}
