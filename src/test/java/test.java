import modular.service.Impl.MessageSendApiServiceImpl;
import org.dom4j.DocumentException;

import java.io.IOException;

public class test {
    public static void main(String[] args) {
        MessageSendApiServiceImpl messageSendApi = new MessageSendApiServiceImpl();
        try {
            messageSendApi.sendMessage("13684303475",123123);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
