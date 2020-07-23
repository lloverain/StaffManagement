package modular.service.Impl;

import modular.dao.EmployeeMapper;
import modular.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import modular.service.EmployeeService;
import java.util.List;

/**
 * EmployeeService的实现类
 * @author 杨佳颖
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> listAll(){
        System.out.println("11");
        return employeeMapper.listAll();
    }
}
