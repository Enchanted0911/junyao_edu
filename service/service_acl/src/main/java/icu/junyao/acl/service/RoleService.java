package icu.junyao.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.acl.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wu
 * @since 2021-08-02
 */
public interface RoleService extends IService<Role> {


    /**
     * 根据用户获取角色数据
     *
     * @param userId
     * @return
     */
    Map<String, Object> findRoleByUserId(String userId);


    /**
     * 根据用户分配角色
     *
     * @param userId
     * @param roleId
     */
    void saveUserRoleRelationShip(String userId, String[] roleId);

    /**
     * 通过用户id获得该用户角色
     *
     * @param id
     * @return
     */
    List<Role> selectRoleByUserId(String id);

    /**
     * 新增一个角色 注意新增的角色名不能和已有的角色名重复
     *
     * @param role
     */
    void saveUnique(Role role);

    /**
     * 修改一个角色 注意新增的角色名不能和已有的角色名重复
     *
     * @param role
     */
    void updateUnique(Role role);
}
