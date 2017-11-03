package com.sunlands.hr.servers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sunlands.hr.repositorys.elasticsearch.ElasticSearchDao;
import com.sunlands.hr.servers.impl.CreateIndex;

import io.searchbox.client.JestResult;

@Component
public class CreateIndexImpl implements CreateIndex{

	@Autowired
	private ElasticSearchDao esDao;

	@Override
	public void createResumeIndex(String inParam) {
		
		JestResult jestResult = esDao.createIndex(inParam, "resume", "sunlands");
		
//		System.out.println(jestResult.toString());
	}

}
