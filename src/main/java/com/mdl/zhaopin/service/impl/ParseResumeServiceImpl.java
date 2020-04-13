package com.mdl.zhaopin.service.impl;

import com.mdl.zhaopin.DTO.ResumeBaseDTO;
import com.mdl.zhaopin.service.ParseResumeService;
import com.mdl.zhaopin.handler.hanlp.factory.ParseFileFactory;
import com.mdl.zhaopin.handler.hanlp.prase.ParseHtml;
import com.mdl.zhaopin.handler.hanlp.prase.ParsePdf;
import com.mdl.zhaopin.handler.hanlp.prase.ParseTxt;
import com.mdl.zhaopin.handler.hanlp.prase.ParseWord;
import com.mdl.zhaopin.utils.CheckFileType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseResumeServiceImpl implements ParseResumeService {

    @Override
    public ResumeBaseDTO getResumeInfo(String filePath) throws IOException {

        ParseFileFactory file = null;

        String fileType = CheckFileType.getFileType(filePath);

        ResumeBaseDTO resumeInfo = null;

        if (fileType != null) {
            if (fileType.endsWith("htm") || fileType.endsWith("html")) {
                file = new ParseHtml(filePath);
            } else if (fileType.endsWith("wps") || fileType.endsWith("doc") || filePath.endsWith("docx")) {
                file = new ParseWord(filePath);
            } else if (fileType.endsWith("pdf")) {
                file = new ParsePdf(filePath);
            } else if (fileType.endsWith("txt")) {
                file = new ParseTxt(filePath);
            }

            if (file != null) {
                resumeInfo = getResumeInfo(file);
            }
        }

        if (filePath.endsWith(".txt")) {
            file = new ParseTxt(filePath);
            resumeInfo = getResumeInfo(file);
        }

        return resumeInfo;

    }

    private ResumeBaseDTO getResumeInfo(ParseFileFactory doc) throws IOException {
        doc.readFile();
        doc.separateWords();
        int age = doc.getAge();
        String name = doc.getName();
        String email = doc.getEmail();
        String sex = doc.getSex();
        String phone = doc.getPhone();
        String university = doc.getUniversity();
//		String address = doc.getAddress();
        String address = "";
        String profession = doc.getProfession();
        String specialized = doc.getSpecialized();
        String degree = doc.getDegree();

        String workLength = doc.getWorkLength();

//		System.out.println("workLength--->" + workLength);

//		List<String> keyword = doc.getKeyword();
//		List<String> projectList = doc.getProjectList();// 项目经验

        ResumeBaseDTO resume = new ResumeBaseDTO();

        if (age != 0) {
            resume.setAge(age + "");
        }
        resume.setName(name);
        resume.setSex(sex);
        resume.setAge(age + "");
        resume.setWorkLength(workLength);
        resume.setPhone(phone);
        resume.setEmail(email);
        resume.setDegree(degree);
        resume.setUniversity(university);
        resume.setExpectPlace(address);
        resume.setMajor(specialized);

//		String resumeStr = gson.toJson(resume);

//		System.out.println(resumeStr + "/n");

        return resume;
    }

}
