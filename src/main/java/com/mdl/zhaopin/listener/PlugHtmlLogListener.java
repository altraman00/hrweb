package com.mdl.zhaopin.listener;

import com.mdl.zhaopin.DTO.BiResumeParseLogDTO;
import com.mdl.zhaopin.entity.BiResumeEntity;
import com.mdl.zhaopin.entity.BiResumeParseLogEntity;
import com.mdl.zhaopin.listener.event.PlugHtmlCatchEvent;
import com.mdl.zhaopin.repository.ResumeParseLogRepository;
import com.mdl.zhaopin.repository.ResumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.listener
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月24日 17:30
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Component
public class PlugHtmlLogListener {

    private static final Logger logger = LoggerFactory.getLogger(PlugHtmlLogListener.class);

    @Autowired
    private ResumeParseLogRepository resumeParseLogRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @EventListener
    public void savePlugHtmlLog(PlugHtmlCatchEvent event) {
        //记录操作
//        logger.debug("【PlugHtmlCatchEvent】,event:{}",event);
        BiResumeParseLogDTO source = (BiResumeParseLogDTO)event.getSource();

        String status = source.getStatus();
        String traceId = source.getTraceId();

        //初始化
        try {
            if(BiResumeParseLogDTO.STATUS_INIT.equals(status)){
                BiResumeParseLogEntity entity = new BiResumeParseLogEntity();
                entity.setPlatform(source.getPlatform());
                entity.setPlugHtml(source.getPlugHtml());
                entity.setTraceId(traceId);
                entity.setPlatformResumeUrl(source.getPlatformResumeUrl());
                resumeParseLogRepository.saveAndFlush(entity);
            }
        } catch (Exception e) {
            logger.error("【savePlugHtmlLog】init 异常:",e);
        }

        //成功
        try {
            if(BiResumeParseLogDTO.STATUS_SUCCESS.equals(status)){
                //查找简历
                BiResumeEntity resumeEntity = resumeRepository.findFirstByTraceId(traceId);
                String resumeId = resumeEntity.getId();

                BiResumeParseLogEntity plugLogEntity = resumeParseLogRepository.findFirstByTraceId(traceId);
                plugLogEntity.setResumeId(resumeId);
                plugLogEntity.setStatus(BiResumeParseLogDTO.STATUS_SUCCESS);
                plugLogEntity.setResumeDetail(source.getResumeDetail());
                resumeParseLogRepository.saveAndFlush(plugLogEntity);
            }
        } catch (Exception e) {
            logger.error("【savePlugHtmlLog】init 更新log为succes异常:",e);
        }

        //失败
        try {
            if(BiResumeParseLogDTO.STATUS_FAILED.equals(status)){
                BiResumeParseLogEntity plugLogEntity = resumeParseLogRepository.findFirstByTraceId(traceId);
                plugLogEntity.setStatus(BiResumeParseLogDTO.STATUS_FAILED);
                resumeParseLogRepository.saveAndFlush(plugLogEntity);
            }
        } catch (Exception e) {
            logger.error("【savePlugHtmlLog】init 更新log为failed异常:",e);
        }


    }

}
