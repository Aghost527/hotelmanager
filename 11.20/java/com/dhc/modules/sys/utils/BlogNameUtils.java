package com.dhc.modules.sys.utils;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tomxie on 2016/4/10 15:42.
 * 
 * 用于根据id搜索名字
 */
public class BlogNameUtils {
    private static String getHTML(String url) throws URISyntaxException, IOException {
        //采用用户自定义cookie策略,不显示cookie rejected的报错
        CookieSpecProvider cookieSpecProvider = new CookieSpecProvider() {
            public CookieSpec create(HttpContext context) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {

                    }
                };
            }
        };
        Registry<CookieSpecProvider> r = RegistryBuilder
                .<CookieSpecProvider>create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory())
                .register("cookie", cookieSpecProvider)
                .build();
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec("cookie")
                .setSocketTimeout(5000) //设置socket超时时间
                .setConnectTimeout(5000)  //设置connect超时时间
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieSpecRegistry(r)
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        String html = "html获取失败"; //用于验证是否取到正常的html
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            html = EntityUtils.toString(response.getEntity());
            //System.out.println(html); //打印返回的html
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }

    public static String decodeUnicode(String dataStr) {

        final StringBuilder buffer = new StringBuilder();
        Pattern p = Pattern.compile("\\\\u([\\S]{4})([^\\\\]*)");
        Matcher match = p.matcher(dataStr);
        int firstCharacter = 0;
        if (match.find()) {
            firstCharacter = match.start();
            if (firstCharacter != 0) {
                buffer.append(dataStr.substring(0, firstCharacter));
            }
        } else {
            buffer.append(dataStr);
            return buffer.toString();
        }
        match.reset(dataStr);
        while (match.find()) {
            char letter = (char) Integer.parseInt(match.group(1), 16);
            buffer.append(Character.toString(letter));
            buffer.append(match.group(2));
        }

        return buffer.toString();
    }

    public static String getNickName(String userID) {
        String html = null;
        try {
            html = getHTML("http://m.weibo.cn/u/" + userID + "?wvr=5&is_all=1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(html);
        //System.out.println("_\u5289\u5e258023");
        if (html != null) {
            Pattern pattern = Pattern.compile(",\"name\":\"([^\"]+)\",\"avatar_hd\":\"");
            Matcher matcher = pattern.matcher(html);
            if (matcher.find()) {
                return decodeUnicode(matcher.group(1));
            }
        }
        return null;
    }

    
    public static String FromBlogId2Name(String userID) throws IOException, URISyntaxException
    {
    	
        return (getNickName(userID));
    	
    }
}
