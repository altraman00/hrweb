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
public class ResumePlatformException extends IServiceException {

    private static final long serialVersionUID = -4681434905235351981L;

    public ResumePlatformException(ResultCode resultCode) {
        super(resultCode);
    }

    public ResumePlatformException(ResultCode resultCode, Object[] args) {
        super(resultCode, args);
    }

    public ResumePlatformException(String key, Object[] args, int errCode) {
        super(key, args, errCode);
    }

    public ResumePlatformException(String message, int errCode) {
        super(message, errCode);
    }
}
