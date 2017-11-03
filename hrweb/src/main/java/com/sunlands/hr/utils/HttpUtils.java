package com.sunlands.hr.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
//import org.opentsdb.client.response.SimpleHttpResponse;

public class HttpUtils {
	
	public static String deleteResultContent(String url) throws ClientProtocolException, IOException{
		HttpEntity entity = httpDelete(url).getEntity();
		return entity != null ? EntityUtils.toString(entity,"UTF-8") : null;
	}
	
	public static int deleteResultStatus(String url) throws ClientProtocolException, IOException{
		return httpDelete(url).getStatusLine().getStatusCode();
	}
	
    
	/**
	 * post 请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String post(String url) throws ClientProtocolException, IOException {
		return httpPost(url);
	}
	
	/**
	 * post请求
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String post(String url, String data) throws ClientProtocolException, IOException{
		return httpPost(url, data,"application/x-www-form-urlencoded");
	}
	
	/**
     * post请求
     * @param url
     * @param data
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public static String post(String url, Map<String,String> data) throws ClientProtocolException, IOException{
        return httpPost(url, convenDataByMap(data,"post"),"application/x-www-form-urlencoded");
    }
	
	public static String postJson(String url, String data) throws ClientProtocolException, IOException{
		return httpPost(url, data,"application/json");
	}
	
	/**
	 * 发送http post请求
	 * @param url       url
	 * @param instream  post数据的字节流
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String post(String url, InputStream instream) throws ClientProtocolException, IOException{
	 
			HttpEntity entity = Request.Post(url)
					.bodyStream(instream,ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
	}
	
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String get(String url) throws ClientProtocolException, IOException{
		return httpGet(url);
	}
	
	/**
	 * get请求
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String get(String url,Map<String,String>paramMap) throws ClientProtocolException, IOException{
	    StringBuffer sb = new StringBuffer(url);
	    sb.append(convenDataByMap(paramMap,"Get"));
        return get(sb.toString());
    }
	
	
	private static String convenDataByMap(Map<String,String>paramMap,String method){
	    StringBuffer sb = new StringBuffer();
	    if(paramMap!=null && paramMap.size()!=0){
            int count = 0;
            for(Entry<String, String>entry:paramMap.entrySet()){
                if(entry.getValue()==null || entry.getKey()==null){
                    continue;
                }
                if(count++ == 0){
                    if("GET".equals(method.toUpperCase())){
                        sb.append("?");
                    }
                } else {
                    sb.append("&");
                }
                sb.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
	    return sb.toString();
	}

	
//	public static SimpleHttpResponse postToOpentsdb(String url, String data) throws ClientProtocolException, IOException{
//		HttpResponse httpResponse= Request
//				.Post(url).bodyString(data,ContentType.create("application/json", Consts.UTF_8))
//				.execute().returnResponse();
//		
//		SimpleHttpResponse simpleHttpResponse = new SimpleHttpResponse();
//		simpleHttpResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
//		simpleHttpResponse.setContent(EntityUtils.toString(httpResponse.getEntity()));
//		return simpleHttpResponse;
//	}
	
	/**
	 * post 请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private static String httpPost(String url, String data, String bodyType) throws ClientProtocolException, IOException {
			HttpEntity entity = Request.Post(url)
					.bodyString(data,ContentType.create(bodyType, Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
	}
	
	
	/**
	 * 发送post请求
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private static String httpPost(String url) throws ClientProtocolException, IOException{
		HttpEntity entity = Request.Post(url)
				.bodyString("",ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8))
			   .execute().returnResponse().getEntity();
		return entity != null ? EntityUtils.toString(entity,"UTF-8") : null;
	}
	
	/**
	 * 上传文件
	 * @param url    URL
	 * @param file   需要上传的文件
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String postFile(String url,File file) throws ClientProtocolException, IOException{
		return postFile(url, null, file);
	}
	
	/**
	 * 上传文件
	 * @param url    URL
	 * @param name   文件的post参数名称
	 * @param file   上传的文件
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String postFile(String url,String name,File file) throws ClientProtocolException, IOException{
			HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody(name, file).build();
			Request request = Request.Post(url);
			request.body(reqEntity);
			HttpEntity resEntity = request.execute().returnResponse().getEntity();
			return resEntity != null ? EntityUtils.toString(resEntity) : null;
 
	}
	
	
	/**
	 * 下载文件
	 * @param url   URL
	 * @return      文件的二进制流，客户端使用outputStream输出为文件
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static byte[] getFile(String url) throws ClientProtocolException, IOException{
 
			Request request = Request.Get(url);
			HttpEntity resEntity = request.execute().returnResponse().getEntity();
			return EntityUtils.toByteArray(resEntity);
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private static String httpGet(String url) throws ClientProtocolException, IOException {
		return httpGet(url,null);
	}
	
	
	
	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String httpGet(String url,Map<String,String>headMap) throws ClientProtocolException, IOException {
		Request request = Request.Get(url);
		
		if(null != headMap){
			for(Entry<String, String> entry:headMap.entrySet()){
				request.addHeader(entry.getKey(),entry.getValue());
			}
		}
		
		HttpEntity entity = request.execute().returnResponse().getEntity();
		
		return entity != null ? EntityUtils.toString(entity,"UTF-8") : null;
	}
	
	
	/**
	 * 发送delete请求
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private static HttpResponse httpDelete(String url) throws ClientProtocolException, IOException{
		return Request.Delete(url)
				.addHeader("content-type", "application/x-www-form-urlencoded")
			   .execute().returnResponse();
	}
	
}
