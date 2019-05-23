package cn.bdqn.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.bdqn.sys.vo.userR;

public interface AsUserRMapper extends BaseMapper<userR>{
	
	@Select("SELECT * FROM as_user JOIN as_role ON as_user.`roleId`=as_role.`id`")
	public List<userR> showUserListAndRoleName(Page p,userR u);
	

}
