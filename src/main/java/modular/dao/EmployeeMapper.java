package modular.dao;
import modular.entity.Employee;
import java.util.List;

/**
 * @author 杨佳颖
 */
public interface EmployeeMapper {
    /**
     * 查询全部信息
     * @return
     */
    List<Employee> listAll();
}
