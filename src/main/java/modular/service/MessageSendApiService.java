package modular.service;

import org.dom4j.DocumentException;

import java.io.IOException;

/**
 * 发送消息的接口
 * @author 杨佳颖
 */
public interface MessageSendApiService {
    /**
     * 发送验证法
     * @param telephone 电话
     * @param mobile_code   验证码
     * @return
     */
    boolean sendMessage(String telephone, int mobile_code) throws IOException, DocumentException;
}
