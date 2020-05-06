package com.mdl.zhaopin.handler.platform.parse.plug;

import com.mdl.zhaopin.enums.PlatformEnums;
import com.mdl.zhaopin.handler.platform.parse.IParseFactory;
import com.mdl.zhaopin.handler.platform.parse.IResumeParser;
import com.mdl.zhaopin.utils.UrlUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse.plug
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 11:46
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class FactoryResumeParserPlug implements IParseFactory {

    private static final Logger logger = LoggerFactory.getLogger(FactoryResumeParserPlug.class);

    /**job51搜索（付费和未付费）的简历的基础url**/
    private static String RESUME_BASE_URL_SEARCH_JOB51 = "https://ehire.51job.com/Candidate/ResumeView.aspx";

    /**job51收件箱的简历的基础url**/
    private static String RESUME_BASE_URL_INBOX_JOB51 = "https://ehire.51job.com/Candidate/ResumeViewFolderV2.aspx";

    /**
     * 智联搜索（付费和未付费）的简历的基础url
     * 1:搜索模块（付费和未付费）
     * 6:人才推荐模块
     * 7:首页 推荐人才
     */
    private static String RESUME_SEARCH_ZHILIAN_TYPE = "1,6,7";

    /**
     * 智联收件箱的简历的基础url
     * 2:已下载模块
     * 5:收件箱模块
     * highpin:精英人才
     */
    private static String RESUME_INBOX_ZHILIAN_TYPE = "2,5,highpin";


    /**
     * 智联招聘搜素的简历和主动投递的简历html结构一样，不用区分；
     * 51job的搜索的简历和主动投递的简历html结构不一样，需要区分
     * @param platform
     * @param resumeUrl
     * @return
     */
    @Override
    public IResumeParser parseResume(String platform,String resumeUrl) {
        logger.debug("【FactoryResumeParserPlug】platform:{},resumeUrl:{}",platform,resumeUrl);
        IResumeParser iResumeParser = null;

        if (PlatformEnums.ZHI_LIAN.getName().equals(platform)) {
            iResumeParser = analysisResumeUrlZhilian(resumeUrl);
        } else if (PlatformEnums.JOBS.getName().equals(platform)) {
            iResumeParser= analysisResumeUrlJob51(resumeUrl);
        }
        return iResumeParser;
    }

    /**
     * 分析智联的简历是搜索的简历还是收件箱的简历
     * @param resumeUrl
     * @return
     */
    private IResumeParser analysisResumeUrlZhilian(String resumeUrl){
        logger.debug("【FactoryResumeParserPlug】【analysisResumeUrlZhilian】resumeUrl:{}",resumeUrl);
        UrlUtil.UrlEntity parse = UrlUtil.parse(resumeUrl);
        String openFrom = parse.params.get("openFrom") == null ? "" : parse.params.get("openFrom");
        String utmSource = parse.params.get("utm_source") == null ? "" : parse.params.get("utm_source");
        String zhilianType = StringUtils.isEmpty(openFrom) ? utmSource : openFrom;

        if(StringUtils.isEmpty(zhilianType)){
            return new ResumeParserPlugHtmlZhilianInbox();
        }

        if(RESUME_SEARCH_ZHILIAN_TYPE.contains(zhilianType)){
            return new ResumeParserPlugHtmlZhilianSearch();
        }else{
            return new ResumeParserPlugHtmlZhilianInbox();
        }
    }


    /**
     * 分析51job的简历是搜索的简历还是收件箱的简历
     * @param resumeUrl
     * @return
     */
    private IResumeParser analysisResumeUrlJob51(String resumeUrl){
        logger.debug("【FactoryResumeParserPlug】【analysisResumeUrlJob51】resumeUrl:{}",resumeUrl);
        UrlUtil.UrlEntity parse = UrlUtil.parse(resumeUrl);
        String baseUrl = parse.baseUrl;
        logger.debug("【analysisResumeUrlJob51】baseUrl:{}",baseUrl);
        //51job搜索的简历url
        if(RESUME_BASE_URL_SEARCH_JOB51.equals(baseUrl)){
            return new ResumeParserPlugHtmlJob51Search();
        }{
            return new ResumeParserPlugHtmlJob51Inbox();
        }
    }


}
