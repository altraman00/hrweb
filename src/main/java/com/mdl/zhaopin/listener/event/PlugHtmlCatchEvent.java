package com.mdl.zhaopin.listener.event;

import com.mdl.zhaopin.DTO.BiResumeParseLogDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.listener.event
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月24日 17:33
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Getter
public class PlugHtmlCatchEvent extends ApplicationEvent {
    private static final long serialVersionUID = -8153252033265907034L;

    public PlugHtmlCatchEvent(BiResumeParseLogDTO plugDTO) {
        super(plugDTO);
    }
}
