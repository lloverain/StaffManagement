package modular.service.Impl;
//接口类型：互亿无线触发短信接口，支持发送验证码短信、订单通知短信等。
// 账户注册：请通过该地址开通账户http://sms.ihuyi.com/register.html
// 注意事项：
//（1）调试期间，请用默认的模板进行测试，默认模板详见接口文档；
//（2）请使用APIID（查看APIID请登录用户中心->验证码短信->产品总览->APIID）及 APIkey来调用接口；
//（3）该代码仅供接入互亿无线短信接口参考使用，客户可根据实际需要自行编写；

import java.io.IOException;

import modular.service.MessageSendApiService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

/**
 * 发送验证码的实现方法
 * @author 杨佳颖
 */
@Service
public class MessageSendApiServiceImpl implements MessageSendApiService {

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";


    /**
     * 发送验证码
     *
     * @param telephone   手机号
     * @param mobile_code 验证码
     * @return
     */
    @Override
    public boolean sendMessage(String telephone, int mobile_code) throws IOException, DocumentException {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        NameValuePair[] data = {//提交短信
                //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("account", "C55119092"),
                //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                new NameValuePair("password", "39c86343634c71b9a41b41e4d80fc9a7"),
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", telephone),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        client.executeMethod(method);
        String SubmitResult = method.getResponseBodyAsString();
//        System.out.println(SubmitResult);
        Document doc = DocumentHelper.parseText(SubmitResult);
        Element root = doc.getRootElement();
        String code = root.elementText("code");
//        String msg = root.elementText("msg");
//            String smsid = root.elementText("smsid");
        System.out.println(code);
        if ("2".equals(code)) {
            System.out.println("短信提交成功");
            return true;
        } else {
            return false;
        }
    }
}