package com.yc.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.system.entity.SysLog;
import com.yc.core.system.model.query.LogQuery;
import com.yc.core.system.model.vo.SysLogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-21
 * @Version: 1.0.0
 *
 */
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 查询系统日志
     * @param page 分页信息
     * @param logQuery 查询条件
     * @return
     */
    Page<SysLogVO> logPage(@Param("param") Page<SysLogVO> page, @Param("query") LogQuery logQuery);
}
