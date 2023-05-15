package com.ruoyi.common.utils.http;

/* *
 *@Description:
 *@Author:TYJ
 *@Date: create in  2020/4/22 18:21
 */


import org.apache.commons.io.IOUtils;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HttpSslUtils {
    public String getRequest(String url,int timeOut) throws Exception{
        URL u = new URL(url);
        if("https".equalsIgnoreCase(u.getProtocol())){
            SslUtils.ignoreSsl();
        }
        URLConnection conn = u.openConnection();
        conn.setConnectTimeout(timeOut);
        conn.setReadTimeout(timeOut);
        return IOUtils.toString(conn.getInputStream());
    }
    public String postRequest(String urlAddress, Map<String,Object> params, int timeOut) throws Exception{
        URL url = new URL(urlAddress);
        if("https".equalsIgnoreCase(url.getProtocol())){
            SslUtils.ignoreSsl();
        }
        URLConnection u = url.openConnection();
        u.setDoInput(true);
        u.setDoOutput(true);
        u.setConnectTimeout(timeOut);
        u.setReadTimeout(timeOut);
        OutputStreamWriter osw = new OutputStreamWriter(u.getOutputStream(), "UTF-8");
        if (params != null) {
            StringBuilder param = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (param.length() > 0) {
                    param.append("&");
                }
                param.append(entry.getKey());
                param.append("=");
                param.append(entry.getValue());

            }

            osw.write(param.toString());
        }
//        osw.write(args);
        osw.flush();
        osw.close();
        u.getOutputStream();
        return IOUtils.toString(u.getInputStream());
    }
    public static void main(String[] args) {
        try {
            HttpSslUtils st = new HttpSslUtils();
            String result = st.getRequest("https://api.ztoken.cn/api/v1", 3000);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
