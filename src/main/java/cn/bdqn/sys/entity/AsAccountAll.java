package cn.bdqn.sys.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AsAccountAll implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户CODE
     */
    @TableField("userCode")
    private String userCode;

    /**
     * 主键ID
     */
    private Long id;

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

    @TableField("userId")
    private Long userId;

    private Double money;

    @TableField("moneyBak")
    private Double moneyBak;


}
