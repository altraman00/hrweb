package com.mdl.zhaopin.DTO;

/**
 * @Project : zhaopin
 * @Package Name : com.sunlands.zhaopin.dtos
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2019年12月10日 17:25
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface DTOConvert<S,T> {

    T convert(S s);

}
