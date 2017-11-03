package com.sunlands.hr.repositorys.elasticsearch;

import com.google.gson.GsonBuilder;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;


/**
 * 初始化es
* @ClassName: InitElasticSearchConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xiekun
* @date 2017年11月2日 上午11:47:48 
*
 */
public class InitElasticSearchConfig {
	
	private JestClient client ;
	
	public JestClient getClient() {
		return client;
	}

	public InitElasticSearchConfig(String esUrl){
		client = getClientConfig(esUrl) ;
	}
	
	public JestClient getClientConfig(String esUrl){
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig
                .Builder(esUrl)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true)
                .readTimeout(10000)
                .build());
		JestClient client = factory.getObject();
		return client ;
	}
	
}
