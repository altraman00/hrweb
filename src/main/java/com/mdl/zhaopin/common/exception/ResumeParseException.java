package com.mdl.zhaopin.common.exception;

import com.mdl.zhaopin.common.response.ResultCode;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.common.exception
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 10:48
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class ResumeParseException extends IServiceException {

    private static final long serialVersionUID = 341164000477344856L;

    public ResumeParseException(ResultCode resultCode) {
        super(resultCode);
    }

    public ResumeParseException(ResultCode resultCode, Object[] args) {
        super(resultCode, args);
    }

    public ResumeParseException(String key, Object[] args, int errCode) {
        super(key, args, errCode);
    }

    public ResumeParseException(String message, int errCode) {
        super(message, errCode);
    }
}
