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
    List<Employee> listAll();
}
