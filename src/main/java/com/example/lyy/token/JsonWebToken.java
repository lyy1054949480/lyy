package com.example.lyy.token;

import com.alibaba.fastjson.JSON;
import com.example.lyy.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JsonWebToken {

    final static String SIGN_KEY = "secret key byte array cannot be null or empty";
    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("bankgl");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷

    /**
     *
     * @param claims
     * @param cycle 设置的过期时间  毫秒
     * @return
     */
    public static String createJavaWebToken(Map<String, Object> claims,Long cycle) {
        Long future = System.currentTimeMillis() + cycle;
//        Key keyInstance = getKeyInstance();
//        String format = keyInstance.getFormat();
//        System.out.println(format);
//        String s = StringUtil.bytes2Str(keyInstance.getEncoded());
//        System.out.println(s);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(new Date(future)).signWith(SignatureAlgorithm.HS256,SIGN_KEY).compact();
    }

    //解析Token，同时也能验证Token，当验证失败返回null
    public static Claims parserJavaWebToken(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            log.error("json web token verify failed");
            return null;
        }
    }

    public static Claims parserJavaWebTokenBySigningKey(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SIGN_KEY).parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            log.error("json web token verify failed");
            return null;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        Map<String,Object> map = new HashMap<>();
        map.put("jtpf.userId","19UC1732892602427261721");

        String javaWebToken = createJavaWebToken(map, 100000L);
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhIjoiODk1NkY4ODZDNzk0QTUyMzdFNjA5MEY0M0UyNkM5QjMiLCJiIjoiMTlVQzE2NTE2MDkyNjM5OTI0NzE2ODMiLCJjIjoiSlRfVVNFUl8wMDAwIiwiZCI6InN5c190b3VyX3BjIiwiZSI6InRvdXIiLCJmIjoiMSIsImV4cCI6MTU5MDQ4MzQwM30.M3DIkAEyTQJdDZOe0Bcw-yxak0DJxQnZljg9-zhs4vc";
        System.out.println(javaWebToken);
        Claims claims = parserJavaWebTokenBySigningKey(javaWebToken);
        System.out.println(JSON.toJSON(claims).toString());
        Claims claims2 = parserJavaWebTokenBySigningKey(token);
        System.out.println(JSON.toJSON(claims2).toString());

//        Claims claims = parserJavaWebTokenBySigningKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJseXkiLCJzY29wZSI6WyJyZWFkX3VzZXJpbmZvIl0sInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FDVFVBVE9SIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImV4cCI6MTY4NjQwNTAxNiwidXNlck5hbWUiOiJseXkiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FDVFVBVE9SIl0sImp0aSI6ImI2NmJmNDdkLWYzODMtNDhhZS1iNTU1LTQ3ODM0NmM0ZWQ0ZSIsImNsaWVudF9pZCI6ImNsaWVudGFwcCJ9.U-t8K666Z9yqgIkuOZxn5Qm3nLbKVNfyfpCtw-frfGI","123");
//        System.out.println(JSON.toJSON(claims).toString());
//        String s = "bearer e";
//        System.out.println(s.substring(7));
    }
}