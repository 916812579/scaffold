package org.example.model;

import com.baomidou.mybatisplus.annotation.TableName;
import org.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author AutoGenerator
 * @since 2020-03-29
 */
@Data
  @EqualsAndHashCode(callSuper = true)
  @Accessors(chain = true)
@TableName("t_test")
@ApiModel(value="Test对象", description="")
public class Test extends BaseModel {

    private static final long serialVersionUID=1L;


}
