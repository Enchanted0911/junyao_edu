package icu.junyao.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.acl.entity.Role;
import icu.junyao.acl.entity.User;
import icu.junyao.acl.mapper.UserMapper;
import icu.junyao.acl.service.UserService;
import icu.junyao.serviceBase.exceptionHandler.JunYaoException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wu
 * @since 2021-08-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public void saveUnique(User user) {
        if (super.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, user.getUsername())) != null) {
            throw new JunYaoException(20001, "用户已存在!");
        }
        super.save(user);
    }

    @Override
    public void updateUnique(User user) {
        if (super.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, user.getUsername())) != null) {
            throw new JunYaoException(20001, "用户已存在!");
        }
        super.updateById(user);
    }
}
