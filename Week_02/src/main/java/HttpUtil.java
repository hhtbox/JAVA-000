package cn.rongcloud.common.http;


import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author huhangtao
 */
public class HttpUtil {

    private static final String SENDURL = "http://127.0.0.1:8088/api/hello";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(SENDURL);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(500)
                .setConnectTimeout(500)
                .build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.format("resp: %s", EntityUtils.toString(entity));
    }


    private HttpUtil() {
    }

}