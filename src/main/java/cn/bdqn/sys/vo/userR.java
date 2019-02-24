package cn.bdqn.sys.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
@Data
public class userR  implements Serializable{
     @TableField("roleName")
	private String roleName;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户CODE
     */
    @TableField("userCode")
    private String userCode;

    /**
     * 用户名称
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户登录密码
     */
    @TableField("userPassword")
    private String userPassword;

    /**
     * 注册时间
     */
    @TableField("creationTime")
    private LocalDateTime creationTime;

    /**
     * 最后登录时间
     */
    @TableField("lastLoginTime")
    private LocalDateTime lastLoginTime;

    /**
     * 创建者
     */
    @TableField("createdBy")
    private String createdBy;

    /**
     * 最后修改时间
     */
    @TableField("lastUpdateTime")
    private LocalDateTime lastUpdateTime;

    /**
     * 是否启动1为启用0为不启用
     */
    @TableField("isStart")
    private Integer isStart;

    /**
     * 所属角色
     */
    @TableField("roleId")
    private Long roleId;
	
	
}
