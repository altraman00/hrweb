/*
 * XiaoMi PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mdl.zhaopin.common.exception;

import com.mdl.zhaopin.common.response.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.common.exception
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 10:39
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class IServiceException extends Exception {

    private static final long serialVersionUID = 4597428703946175941L;

    private int code = -1;

    protected String msg;

    protected Object[] args;

    public IServiceException(ResultCode resultCode) {
        super(resultCode.get().getMsg());
        this.code = resultCode.get().getCode();
        this.msg = resultCode.get().getMsg();
    }

    public IServiceException(ResultCode resultCode, Object[] args) {
        super(resultCode.get().getMsg());
        this.code = resultCode.get().getCode();
        this.msg = resultCode.get().getMsg();
        this.args = args;
    }

    public IServiceException(Throwable cause) {
        super(cause);
    }

    public IServiceException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public IServiceException(String message) {
        super(message);
    }

    public IServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public IServiceException(String msg, Object[] args, int code) {
        this.code = code;
        this.msg = msg;
        this.args = args;
    }

    public IServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getErrDescription(MessageSource messageSource, Locale locale) {
        String description = null;
        if (StringUtils.isNotBlank(msg)) {
            description = messageSource.getMessage(msg, args,
                    getLocalizedMessage(), locale);
        } else {
            description = getLocalizedMessage();
        }

        return description;
    }

}
