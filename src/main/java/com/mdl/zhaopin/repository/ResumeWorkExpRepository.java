package com.mdl.zhaopin.repository;

import com.mdl.zhaopin.entity.BiResumeWorkExpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.repository
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月22日 09:55
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface ResumeWorkExpRepository extends JpaRepository<BiResumeWorkExpEntity,String> {

    List<BiResumeWorkExpEntity> findByResumeId(String resumeId);

}
