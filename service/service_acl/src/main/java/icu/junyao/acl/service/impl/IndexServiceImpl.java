package icu.junyao.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import icu.junyao.acl.entity.Role;
import icu.junyao.acl.entity.User;
import icu.junyao.acl.service.IndexService;
import icu.junyao.acl.service.PermissionService;
import icu.junyao.acl.service.RoleService;
import icu.junyao.acl.service.UserService;
import icu.junyao.serviceBase.exceptionHandler.JunYaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wu
 */
@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final UserService userService;

    private final RoleService roleService;

    private final PermissionService permissionService;

    private final RedisTemplate redisTemplate;

    /**
     * 根据用户名获取用户登录信息
     *
     * @param username
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>(8);
        User user = userService.selectByUsername(username);
        if (null == user) {
            throw new JunYaoException(20001, "用户不存在!");
        }

        //根据用户id获取角色
        List<Role> roleList = roleService.selectRoleByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if (roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名获取动态菜单
     *
     * @param username
     * @return
     */
    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.selectByUsername(username);

        //根据用户id获取用户菜单权限
        return permissionService.selectPermissionByUserId(user.getId());
    }
}
