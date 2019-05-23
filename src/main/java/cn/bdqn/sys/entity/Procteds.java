package cn.bdqn.sys.entity;

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
public class Procteds implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("configTypeName")
    private String configTypeName;

    private Long number;

    private Double price;


}
