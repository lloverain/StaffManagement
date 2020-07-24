package modular.controller;

import modular.api.Response;
import modular.api.ResponseResult;
import modular.service.Impl.MessageSendApiServiceImpl;
import modular.service.MessageSendApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 短信发送管理
 *
 * @author 杨佳颖
 */
@Controller
public class MessageController {

    private MessageSendApiService messageSendApiService = new MessageSendApiServiceImpl();

    /**
     * 发送验证码接口
     * @param phone 电话
     * @param session   存储服务器产生的验证码，用作验证
     * @return
     */
    @RequestMapping("/sendyzm/{phone}")
    @ResponseBody
    public ResponseResult sendVerificationCode(@PathVariable String phone, HttpSession session) {
        if (11 == phone.length()) {
            int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
            System.out.println("验证码：" + mobile_code);
            session.setAttribute("code", mobile_code);
            try {
                if (messageSendApiService.sendMessage(phone, mobile_code)) {
                    session.setAttribute("code", mobile_code);
                    return Response.makeOKRsp("手机号为:" + phone + ",已发送！");
                } else {
                    return Response.makeErrRsp("手机号为:" + phone + ",发送失败！");
                }
            } catch (Exception e) {
                //如果检查到异常，则是发送验证码失败
                return Response.makeErrRsp("验证法发送失败！");
            }
        } else {
            return Response.makeErrRsp("手机号应是11位！");
        }

    }
}
