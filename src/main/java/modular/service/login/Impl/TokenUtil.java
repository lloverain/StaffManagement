package modular.service.login.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import modular.entity.User;
import modular.tool.SystemConfig;

import java.util.Date;

/**
 * 生成token
 * @author 杨佳颖
 */
public class TokenUtil {
    public String getToken(User user) {
        Algorithm key = Algorithm.HMAC256(user.getPassword());
        Long now = System.currentTimeMillis();
        Long nexttime = now + SystemConfig.PRESCRIPTIONTIME;
        Date nextDate = new Date(nexttime);
        System.out.println("时间到期为:"+nextDate);
        String token = "";
        //根据电话加密
        token = JWT.create()
                .withAudience(user.getPhone())
                .withExpiresAt(nextDate)
                .sign(key);
        return token;
    }
}
