package com.mdl.zhaopin.repository;

import com.mdl.zhaopin.entity.BiResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.repository
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 15:31
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Repository
public interface ResumeRepository extends JpaRepository<BiResumeEntity,String> {

    /**
     * 根据id查找简历信息
     * @param id
     * @return
     */
    BiResumeEntity findFirstById(String id);

    /**
     * 根据跟踪id查询简历
     * @param traceId
     * @return
     */
    BiResumeEntity findFirstByTraceId(String traceId);

}
