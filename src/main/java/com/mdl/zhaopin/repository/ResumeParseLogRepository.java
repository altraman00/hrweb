package com.mdl.zhaopin.repository;

import com.mdl.zhaopin.entity.BiResumeParseLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.repository
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月24日 17:24
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Repository
public interface ResumeParseLogRepository extends JpaRepository<BiResumeParseLogEntity,String> {

    BiResumeParseLogEntity findFirstByTraceId(String traceId);

}
