package com.yc.practice.common.dao;

import com.yc.common.config.exception.RunException.RunningException;
import com.yc.core.system.model.vo.CurrUserVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述：implements - DAO data operation layer
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-31 14:15
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DaoFactory implements DaoApi {

    @Override
    public CurrUserVO getCurrUser() {
        try {
            return (CurrUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e) {
            throw new RunningException("系统错误");
        }
    }


    @Override
    public String getCurrUserId() {
        try {
            return ((CurrUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSysUserId();
        }catch (Exception e) {
            throw new RunningException("系统错误");
        }
    }
}
