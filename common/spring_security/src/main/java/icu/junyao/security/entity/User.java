package icu.junyao.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author wu
 * @since 2021-08-02
 */
@Data
@ApiModel(description = "用户实体类")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	@TableId
	private String id;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "用户签名")
	private String token;

}



