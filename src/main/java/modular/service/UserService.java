package modular.service;

import modular.entity.User;

/**
 * @author 杨佳颖
 */
public interface UserService {
    /**
     * 根据电话查询用户详情
     * @param phone
     * @return
     */
    User oneUser(String phone);
}
