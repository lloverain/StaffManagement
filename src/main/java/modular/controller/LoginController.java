package modular.controller;

import modular.service.login.Impl.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 登录
 * @author 杨佳颖
 */
@Controller
public class LoginController {

    private TokenUtil tokenUtil = new TokenUtil();

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(){
        return "/login.html";
    }

    @RequestMapping(value = "/homepage",method = RequestMethod.GET)
    public String toIndex(){
        return "/index.html";
    }

    @RequestMapping(value = "/login/{phone}/{code}",method = RequestMethod.POST)
    public String login(@PathVariable String phone, HttpSession session, @PathVariable String code){
        //获取服务器产生的验证码
        String co = (String) session.getAttribute("code");
        //todo 通过电话去找用户信息
        if(co.equals(code)){
            System.out.println("登录成功！");
            //todo 使用id作为加密，得到token返回
//            String token = tokenUtil.getToken(users.get(0));
        }else {
            System.out.println("登录失败！");
        }
        return null;
    }
}
