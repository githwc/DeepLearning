package com.yc.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述:读取本机信息工具包
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2019-05-09
 */
public class LocalHostUtil {

    /**
     * @Description:获取客户端真实IP地址
     * @Date: 16:07 2019/5/9
     * @Param: request  :HttpServletRequest
     * @Return: IP Address
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)  || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip)  || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip)  || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * @Description:获取客户端真实IP地址
     * @Date: 16:34 2019/5/9
     * @Param:
     * @Return: IP Address
     */
    public static String getIpAddress() {
        String hostName = "";
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            hostName = addr.getHostAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    /**
     * @Description:根据IP地址获取客户端的MAC
     * @Date: 16:38 2019/5/9
     * @Param: inetAddress
     * @Return: MAC Address
     */
    public static String getMACAddress(InetAddress inetAddress) throws IOException {
        String strMac = "";
        // Linux System
        if (System.getProperties().getProperty("os.name").toLowerCase().indexOf("linux") != -1) {
            String mac = "";
            try {
                Process p = new ProcessBuilder("ifconfig").start();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    Matcher mat = Pattern.compile("\\b\\w+:\\w+:\\w+:\\w+:\\w+:\\w+\\b").matcher(line);
                    if (mat.find()){
                        mac = mat.group(0);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            strMac = mac;
        }
        // Windows System
        else {
            // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            // 下面代码是把mac地址拼装成String
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                // mac[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            strMac = sb.toString();
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        return strMac.toUpperCase();
    }

    /**
     * @Description:读取本机MAC地址
     * @Date: 16:40 2019/5/9
     * @Param:
     * @Return: MAC Address
     */
    public static String getMACAddress() {
        String strMacAddr = "";
        try {
            strMacAddr = getMACAddress(InetAddress.getLocalHost());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strMacAddr;
    }

    /**
     * @Description:读取本机机器名称
     * @Date: 16:41 2019/5/9
     * @Param:
     * @Return:  HostName
     */
    public static String getHostName() {
        String ip = "";
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            ip = addr.getHostName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
