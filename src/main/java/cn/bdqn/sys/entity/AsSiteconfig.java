package cn.bdqn.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yanzhao
 * @since 2019-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AsSiteconfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("siteName")
    private String siteName;


}
