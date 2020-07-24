package modular.dao;

import modular.entity.User;

/**
 * 用户mapper
 * @author 杨佳颖
 */
public interface UserMapper {

    /**
     * 根据电话查询用户详情
     * @param phone
     * @return
     */
    User oneUser(String phone);
}
