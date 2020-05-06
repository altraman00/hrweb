package com.mdl.zhaopin.service.impl;

import com.mdl.zhaopin.DTO.*;
import com.mdl.zhaopin.common.exception.ResumeParseException;
import com.mdl.zhaopin.common.response.ResultCode;
import com.mdl.zhaopin.entity.BiResumeEducationEntity;
import com.mdl.zhaopin.entity.BiResumeEntity;
import com.mdl.zhaopin.entity.BiResumeSkillEntity;
import com.mdl.zhaopin.entity.BiResumeWorkExpEntity;
import com.mdl.zhaopin.handler.platform.parse.IParseFactory;
import com.mdl.zhaopin.handler.platform.parse.IResumeParser;
import com.mdl.zhaopin.handler.platform.parse.ParserFactoryProducer;
import com.mdl.zhaopin.handler.platform.resume.Resume;
import com.mdl.zhaopin.listener.event.PlugHtmlCatchEvent;
import com.mdl.zhaopin.repository.ResumeEduRepository;
import com.mdl.zhaopin.repository.ResumeRepository;
import com.mdl.zhaopin.repository.ResumeSkillRepository;
import com.mdl.zhaopin.repository.ResumeWorkExpRepository;
import com.mdl.zhaopin.service.ResumeService;
import com.mdl.zhaopin.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.service.impl
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 15:27
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Transactional
@Service
public class ResumeServiceImpl implements ResumeService {

    private static final Logger logger = LoggerFactory.getLogger(ResumeServiceImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeEduRepository resumeEduRepository;

    @Autowired
    private ResumeWorkExpRepository resumeWorkExpRepository;

    @Autowired
    private ResumeSkillRepository resumeSkillRepository;


    /**
     * 解析简历
     * @param parseResumeInfo
     * @return
     * @throws ResumeParseException
     */
    @Override
    public String parseResumeInfo(ResumePlatformPlugDTO parseResumeInfo) throws ResumeParseException {

        String resumeHtml = parseResumeInfo.getResumeHtml();
        String platform = parseResumeInfo.getPlatform();
        String resumeUrl = parseResumeInfo.getResumeUrl();

////        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-任女士-插件.html";
//        String filePath = "/Users/admin/Desktop/简历解析/51job/51job-插件-收件箱-孙超.html";
//        File file = new File(filePath);
//        try {
//            resumeHtml = FileUtils.readFileToString(file, "UTF-8");
//        } catch (IOException e) {
//            logger.error("html简历读取异常");
//        }

        //1、记录初始化
        String traceId = JSONUtils.getUuid();
        BiResumeParseLogDTO plugDTOSave = new BiResumeParseLogDTO(traceId,platform,resumeHtml,resumeUrl, BiResumeParseLogDTO.STATUS_INIT);
        applicationContext.publishEvent(new PlugHtmlCatchEvent(plugDTOSave));

        try {
            //2、解析html
            IParseFactory plugFactory = ParserFactoryProducer.getFactory(ResumePlatformPlugDTO.PARSE_TYPE_PLUG);
            IResumeParser iResumeParser = plugFactory.parseResume(platform,resumeUrl);
            Resume resume = iResumeParser.parse(resumeHtml);

            //3、保存解析结果
            ResumeObjDTO resumeBaseDTO = new ResumeObjDTO();
            ResumeBaseDTO resumeBase = resume.getResumeBase();
            resumeBase.setTraceId(traceId);
            resumeBaseDTO.setResumeBase(resumeBase);
            resumeBaseDTO.setEduList(resume.getEduList());
            resumeBaseDTO.setSkillList(resume.getSkillList());
            resumeBaseDTO.setWorkExpList(resume.getWorkExpList());
            String resumeId = saveResumeInfo(resumeBaseDTO,resumeHtml);
            logger.debug("【ResumeController】platform:{},resume:{}",platform,resume);

            //记录成功
            String resumeDetail = JSONUtils.objectToJson(resumeBaseDTO);
            BiResumeParseLogDTO plugDTOSuccess = BiResumeParseLogDTO.builder()
                    .traceId(traceId)
                    .resumeId(resumeId)
                    .status(BiResumeParseLogDTO.STATUS_SUCCESS)
                    .resumeDetail(resumeDetail)
                    .build();
            applicationContext.publishEvent(new PlugHtmlCatchEvent(plugDTOSuccess));

            return resumeId;
        } catch (Exception e) {
            logger.error("简历解析异常", e);
            //记录解析失败
            BiResumeParseLogDTO plugDTOFailed = new BiResumeParseLogDTO(traceId, BiResumeParseLogDTO.STATUS_FAILED);
            applicationContext.publishEvent(new PlugHtmlCatchEvent(plugDTOFailed));
            throw new ResumeParseException(ResultCode.RESUME_PARSE_FAILED);
        }
    }


    /**
     * 根据id查询简历
     *
     * @param resumeId
     * @return
     */
    @Override
    public ResumeObjDTO findFirstByResumeId(String resumeId) {
        ResumeObjDTO resumeObjDTO = new ResumeObjDTO();

        logger.debug("【findFirstById】resumeId:{}", resumeId);

        /** --------------------查询基本信息-------------------- **/
        BiResumeEntity resumeEntity = resumeRepository.findFirstById(resumeId);
        //entity转dto
        ResumeBaseDTO resumeDTO = new ResumeBaseDTO().convertEntityToDTO(resumeEntity);
        resumeObjDTO.setResumeBase(resumeDTO);


        /** --------------------查询教育经历-------------------- **/
        List<BiResumeEducationEntity> eduEntityList = resumeEduRepository.findByResumeId(resumeId);
        //entity转dto
        List<ResumeParseEducationDTO> eduDTOList = eduEntityList.stream()
                .map(t -> new ResumeParseEducationDTO().convertEntityToDTO(t))
                .collect(Collectors.toList());
        resumeObjDTO.setEduList(eduDTOList);


        /** --------------------查询工作经验-------------------- **/
        List<BiResumeWorkExpEntity> workExpEntityList = resumeWorkExpRepository.findByResumeId(resumeId);
        //entity转dto
        List<ResumeParseWorkExpDTO> workExpDTOList = workExpEntityList.stream()
                .map(t -> new ResumeParseWorkExpDTO().convertEntityToDTO(t))
                .collect(Collectors.toList());
        resumeObjDTO.setWorkExpList(workExpDTOList);


        /** --------------------查询技能-------------------- **/
        List<BiResumeSkillEntity> skillEntityList = resumeSkillRepository.findByResumeId(resumeId);
        //entity转dto
        List<ResumeParseSkillsDTO> skillsDTOList = skillEntityList.stream().map(t -> new ResumeParseSkillsDTO().convertEntityToDTO(t))
                .collect(Collectors.toList());
        resumeObjDTO.setSkillList(skillsDTOList);

        return resumeObjDTO;
    }


    @Override
    public String saveResumeInfo(ResumeObjDTO resumeBaseDTO, String resumeHtml) {

        logger.debug("【saveResumeInfo】resumeBaseDTO:{}", resumeBaseDTO);

        //基本信息
        BiResumeEntity biResumeEntity = resumeBaseDTO.getResumeBase().convertDTOToEntity();
        logger.debug("【saveResumeInfo】biResumeEntity:{}", biResumeEntity);
        biResumeEntity = resumeRepository.saveAndFlush(biResumeEntity);
        String resumeId = biResumeEntity.getId();

        //教育经历
        List<BiResumeEducationEntity> eduList = new ArrayList<>();
        resumeBaseDTO.getEduList().stream().map(ResumeParseEducationDTO::convertDTOToEntity).forEach(convertToResumeEduEntity -> {
            convertToResumeEduEntity.setResumeId(resumeId);
            eduList.add(convertToResumeEduEntity);
        });
        logger.debug("【saveResumeInfo】eduList:{}", eduList);
        resumeEduRepository.saveAll(eduList);

        //工作经验
        List<BiResumeWorkExpEntity> workExpList = new ArrayList<>();
        resumeBaseDTO.getWorkExpList().stream().map(ResumeParseWorkExpDTO::convertDTOToEntity).forEach(convertToResumeWordExpEntity -> {
            convertToResumeWordExpEntity.setResumeId(resumeId);
            workExpList.add(convertToResumeWordExpEntity);
        });
        logger.debug("【saveResumeInfo】workExpList:{}", workExpList);
        resumeWorkExpRepository.saveAll(workExpList);

        //技能
        List<BiResumeSkillEntity> skilList = new ArrayList<>();
        resumeBaseDTO.getSkillList().stream().map(ResumeParseSkillsDTO::convertDTOToEntity).forEach(convertToResumeSkillsEntity -> {
            convertToResumeSkillsEntity.setResumeId(resumeId);
            skilList.add(convertToResumeSkillsEntity);
        });
        logger.debug("【saveResumeInfo】skilList:{}", skilList);
        resumeSkillRepository.saveAll(skilList);

        return resumeId;

    }


}
