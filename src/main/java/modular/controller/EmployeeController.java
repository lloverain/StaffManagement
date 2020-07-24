package modular.controller;

import modular.api.Response;
import modular.api.ResponseResult;
import modular.entity.Employee;
import modular.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨佳颖
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 访问index页面
     *
     * @return
     */
    @RequestMapping(value = "/aaa", method = RequestMethod.GET)
    public String toIndex() {
        return "index.jsp";
    }

    /**
     * 查询所用员工
     *
     * @return
     */
    @RequestMapping(value = "/see", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<Employee>> show() {
        List<Employee> employeeList = employeeService.listAll();
        try {
            return Response.makeOKRsp(employeeList);
        } catch (Exception e) {
            return Response.makeOKRsp("查询异常");
        }
    }

    /**
     * 增添员工
     *
     * @param name    姓名
     * @param age     年龄
     * @param sex     性别
     * @param address 地址
     * @return
     */
    @RequestMapping(value = "/add/{name}/{age}/{sex}/{address}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(@PathVariable String name, @PathVariable int age, @PathVariable String sex, @PathVariable String address) {
        if (age >= 0 && age <= 200) {
            if ("男".equals(sex) || "女".equals(sex)) {
                Employee employee = new Employee();
                employee.setName(name);
                employee.setAge(age);
                employee.setSex(sex);
                employee.setAddress(address);
                if (employeeService.addUser(employee)) {
                    return Response.makeOKRsp(name + "添加成功！");
                } else {
                    return Response.makeErrRsp(name + "添加失败！");
                }
            } else {
                return Response.makeErrRsp("性别为男或者女！");
            }
        } else {
            return Response.makeErrRsp("年龄异常！age=" + age);
        }

    }

    /**
     * 根据用户id修改数据
     *
     * @param id
     * @param name
     * @param age
     * @param sex
     * @param address
     * @return
     */
    @RequestMapping(value = "/update/{id}/{name}/{age}/{sex}/{address}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult updateUser(@PathVariable int id, @PathVariable String name,
                                     @PathVariable int age, @PathVariable String sex, @PathVariable String address) {
        if (age >= 0 && age <= 200) {
            if ("男".equals(sex) || "女".equals(sex)) {
                Employee employee = new Employee();
                employee.setId(id);
                employee.setName(name);
                employee.setAge(age);
                employee.setSex(sex);
                employee.setAddress(address);
                if (employeeService.updateUser(employee)) {
                    return Response.makeOKRsp(name + "更新成功！");
                } else {
                    return Response.makeErrRsp(name + "更新失败！");
                }
            } else {
                return Response.makeErrRsp("性别为男或者女！");
            }
        } else {
            return Response.makeErrRsp("年龄异常！age=" + age);
        }
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteUser(@PathVariable int id) {
        if (employeeService.deleteUser(id)) {
            return Response.makeOKRsp("用户" + id + "删除成功！");
        } else {
            return Response.makeErrRsp("用户" + id + "删除失败！请检查id是否存在或者错误。");
        }
    }

    /**
     * 当前类的异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ModelAndView error(Exception exception) {
        ModelAndView mv = new ModelAndView();
        if (exception instanceof MethodArgumentTypeMismatchException) {
            mv.addObject("error", "参数不匹配:" + exception.getMessage());
            mv.setViewName("error");
        } else {
            mv.addObject("error", exception.getMessage());
            mv.setViewName("error");
        }
        return mv;
    }
}
