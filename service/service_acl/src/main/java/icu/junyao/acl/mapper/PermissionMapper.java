package icu.junyao.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.junyao.acl.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author wu
 * @since 2021-08-02
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}
