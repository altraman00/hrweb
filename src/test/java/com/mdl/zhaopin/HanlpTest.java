package com.mdl.zhaopin;

import com.mdl.zhaopin.DTO.ResumeBaseDTO;
import com.mdl.zhaopin.service.ParseResumeService;
import com.mdl.zhaopin.service.impl.ParseResumeServiceImpl;
import com.mdl.zhaopin.utils.JsonTools;
import com.mdl.zhaopin.utils.TxtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年01月17日 14:47
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class HanlpTest {

    public static void main(String[] args) throws IOException {

        String path = System.getProperty("user.dir");
//        String resumeStr = TxtUtils.read(path + "/src/main/resumefiles/1-夏小为.txt");
        String resumePath = path + "/src/main/resumefiles/1-夏小为.txt";
        ParseResumeService service = new ParseResumeServiceImpl();
        ResumeBaseDTO resumeInfo = service.getResumeInfo(resumePath);

        System.out.println(JsonTools.obj2String(resumeInfo));

    }


}
