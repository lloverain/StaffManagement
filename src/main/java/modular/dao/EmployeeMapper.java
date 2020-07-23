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

    /**
     * 添加用户
     * @param employee
     * @return
     */
    int addUser(Employee employee);

    /**
     * 更新用户
     * @param employee
     * @return
     */
    int updateUser(Employee employee);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);

}
