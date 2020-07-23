package modular.service;

import modular.entity.Employee;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Employee服务
 * @author 杨佳颖
 */
public interface EmployeeService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<Employee> listAll();

    /**
     * 添加用户
     * @param employee
     * @return
     */
    boolean addUser(Employee employee);

    /**
     * 更新用户
     * @param employee
     * @return
     */
    boolean updateUser(Employee employee);

    /**
     * 删除用户
     */
    boolean deleteUser(int id);
}
