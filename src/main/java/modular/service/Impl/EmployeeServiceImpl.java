package modular.service.Impl;

import modular.dao.EmployeeMapper;
import modular.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import modular.service.EmployeeService;

import java.util.List;

/**
 * EmployeeService的实现类
 * 具有查询所有用户，增添，修改，删除
 *
 * @author 杨佳颖
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> listAll() {
        return employeeMapper.listAll();
    }

    @Override
    public boolean addUser(Employee employee) {
        int i = employeeMapper.addUser(employee);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUser(Employee employee) {
        int i = employeeMapper.updateUser(employee);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        int i = employeeMapper.deleteUser(id);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
}
