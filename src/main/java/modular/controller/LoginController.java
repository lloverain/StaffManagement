package modular.controller;

import modular.api.Response;
import modular.api.ResponseResult;
import modular.entity.User;
import modular.service.UserService;
import modular.service.login.Impl.TokenUtil;
import modular.service.login.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 登录
 *
 * @author 杨佳颖
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private TokenUtil tokenUtil = new TokenUtil();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login.jsp";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String toIndex() {
        return "homepage.jsp";
    }

    @RequestMapping(value = "/getpass", method = RequestMethod.GET)
    public String toPassword() {
        return "getpassword.jsp";
    }

    /**
     * 登录
     * @param phone 电话
     * @param session  获得服务器验证码
     * @param code  获得用户上传验证码
     * @return
     */
    @RequestMapping(value = "/login/{phone}/{code}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@PathVariable String phone, HttpSession session, @PathVariable String code) {
        //获取服务器产生的验证码
        System.out.println("得到是验证码：" + code);
        String co = String.valueOf(session.getAttribute("code"));
        System.out.println("验证码1:" + co);
        if (co.equals(code)) {
            User user = userService.oneUser(phone);
            System.out.println(user.toString());
            String token = tokenUtil.getToken(user);
            return Response.makeOKRsp(token);
        } else {
            return Response.makeErrRsp("登录失败！");
        }
    }

    /**
     * 密码找回
     * @param phone 电话
     * @param session   服务器验证码
     * @param code  用户上传验证码
     * @return
     */
    @RequestMapping(value = "/password/{phone}/{code}", method = RequestMethod.POST)
    @ResponseBody
//    @UserLoginToken
    public ResponseResult getPassword(@PathVariable String phone, HttpSession session, @PathVariable String code) {
        //获取服务器产生的验证码
        String co = String.valueOf(session.getAttribute("code"));
        System.out.println("得到"+code);
        System.out.println("本地"+co);
        if (co.equals(code)) {
            User user = userService.oneUser(phone);
            System.out.println("查询:"+user.toString());
            return Response.makeOKRsp(user.getPassword());
        } else {
            return Response.makeErrRsp("验证失败！");
        }
    }

}
