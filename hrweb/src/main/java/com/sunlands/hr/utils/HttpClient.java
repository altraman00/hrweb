package com.sunlands.hr.utils;

import okhttp3.*;

import java.io.IOException;

/**
 * Http客户端
 */
public class HttpClient {

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    /**
     * GET接口
     *
     * @param url
     * @return 返回ResponseBody
     * @throws IOException
     */
    public static String httpGet(String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * POST接口
     *
     * @param url
     * @param json
     * @return 返回ResponseBody
     * @throws IOException
     */
    public static String httpPost(String url, String json) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }
}
