package modular.service.Impl;

import modular.dao.UserMapper;
import modular.entity.User;
import modular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User oneUser(String phone) {
        return userMapper.oneUser(phone);
    }
}
