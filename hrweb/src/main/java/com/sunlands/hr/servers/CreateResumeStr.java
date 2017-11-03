package com.sunlands.hr.servers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sunlands.hr.hanlp.AnalysisDoc;
import com.sunlands.hr.hanlp.bean.Resume;
import com.sunlands.hr.hanlp.factory.AnalysisFileFactory;
import com.sunlands.hr.servers.impl.CreateIndex;

@Component
public class CreateResumeStr implements CreateResumeInterface{
	
	@Autowired
	private CreateIndex CreateIndexService;
	
	static Gson gson = new Gson();
	
	@Override
	public void getFileContent(String file) throws IOException {
		AnalysisFileFactory doc = new AnalysisDoc(file);
		doc.readFile();
		doc.separateWords();
		int age = doc.getAge();
		String name = doc.getName();
		String email = doc.getEmail();
		String sex = doc.getSex();
		String phone = doc.getPhone();
		String university = doc.getUniversity();
		String address = doc.getAddress();
		String profession = doc.getProfession();
		String specialized = doc.getSpecialized();
		String degree = doc.getDegree();
		
		String workLength = doc.getWorkLength();
		
		System.out.println("workLength--->" + workLength);
		
//		List<String> keyword = doc.getKeyword();
//		List<String> projectList = doc.getProjectList();// 项目经验

		Resume resume = new Resume();
		resume.setAddress("");
		if (age != 0) {
			resume.setAge(age + "");
		}
		resume.setName(name);
		resume.setPhone(phone);
		resume.setSex(sex);
		resume.setEmail(email);
		resume.setUniversity(university);
		resume.setAddress(address);
//		resume.setKeyword(keyword.toString());
		resume.setProfession(profession);
		resume.setSpecialized(specialized);
		resume.setDegree(degree);
		
		String resumeStr = gson.toJson(resume);
		
		System.out.println(resumeStr + "/n");
		
		CreateIndexService.createResumeIndex(resumeStr);//往es里面插
	}

}
