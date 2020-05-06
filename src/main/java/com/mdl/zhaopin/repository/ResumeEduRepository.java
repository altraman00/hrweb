package com.mdl.zhaopin.repository;

import com.mdl.zhaopin.entity.BiResumeEducationEntity;
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
public interface ResumeEduRepository extends JpaRepository<BiResumeEducationEntity,String> {

    List<BiResumeEducationEntity> findByResumeId(String resumeId);

}
