package com.example.lyy.util.system;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

public class VisitorOpreator {
    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip!= null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip!= null && !"".equals(ip)  && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 获取来访者的浏览器版本
     * @param request
     * @return
     */
    public static String getRequestBrowserInfo(HttpServletRequest request){
        String browserVersion = null;
        String header = request.getHeader("user-agent");
        if(header == null || header.equals("")){
            return "";
        }
        if(header.indexOf("MSIE")>0){
            browserVersion = "IE";
        }else if(header.indexOf("Firefox")>0){
            browserVersion = "Firefox";
        }else if(header.indexOf("Chrome")>0){
            browserVersion = "Chrome";
        }else if(header.indexOf("Safari")>0){
            browserVersion = "Safari";
        }else if(header.indexOf("Camino")>0){
            browserVersion = "Camino";
        }else if(header.indexOf("Konqueror")>0){
            browserVersion = "Konqueror";
        }
        return browserVersion;
    }

    /**
     * 获取系统版本信息
     * @param request
     * @return
     */
    public static String getRequestSystemInfo(HttpServletRequest request){
        String systenInfo = null;
        Map<String, String> map = System.getenv();
        /*String header = request.getHeader("user-agent");
        if(header == null || header.equals("")){
            return "";
        }
        //得到用户的操作系统
        if (header.indexOf("NT 6.0") > 0){
            systenInfo = "Windows Vista/Server 2008";
        } else if (header.indexOf("NT 5.2") > 0){
            systenInfo = "Windows Server 2003";
        } else if (header.indexOf("NT 5.1") > 0){
            systenInfo = "Windows XP";
        } else if (header.indexOf("NT 6.0") > 0){
            systenInfo = "Windows 7";
        } else if (header.indexOf("NT 6.2") > 0){
            systenInfo = "Windows Slate";
        } else if (header.indexOf("NT 6.3") > 0){
            systenInfo = "Windows 9";
        } else if (header.indexOf("Mac") > 0){
            systenInfo = "Mac";
        } else if (header.indexOf("Unix") > 0){
            systenInfo = "UNIX";
        } else if (header.indexOf("Linux") > 0){
            systenInfo = "Linux";
        } else if (header.indexOf("SunOS") > 0){
            systenInfo = "SunOS";
        } else if (header.indexOf("NT 6.4") > 0){
            systenInfo = "Windows 10";
        }*/
        /*System.out.println(map.get("USERNAME"));//获取username
        System.out.println(map.get("COMPUTERNAME"));//获取计算机名
        System.out.println(map.get("USERDOMAIN"));//获取计算机域名*/
        Properties props = System.getProperties();
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("操作系统的版本号：" + props.getProperty("os.version"));
        systenInfo = props.getProperty("os.name") + " " + props.getProperty("os.version");
        return systenInfo;
    }

    /**
     * 获取来访者的主机名称
     * @param ip
     * @return
     */
    public static String getHostName(String ip){
        InetAddress inet;
        try {
            inet = InetAddress.getByName(ip);
            return inet.getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
