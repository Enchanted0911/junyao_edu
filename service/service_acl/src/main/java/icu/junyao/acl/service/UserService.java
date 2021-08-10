package icu.junyao.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.acl.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wu
 * @since 2021-08-02
 */
public interface UserService extends IService<User> {


    /**
     * 从数据库中取出用户信息
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 新增管理员用户
     *
     * @param user
     */
    void saveUnique(User user);

    /**
     * 修改管理员用户
     *
     * @param user
     */
    void updateUnique(User user);
}
