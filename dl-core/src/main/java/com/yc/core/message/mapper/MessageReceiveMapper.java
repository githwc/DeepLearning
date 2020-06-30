package com.yc.core.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.core.message.entity.MessageReceive;
import com.yc.core.message.model.query.MessageReceiveQuery;
import com.yc.core.message.model.vo.NoticeVO;
import org.springframework.stereotype.Repository;

/**
 *
 * 功能描述:
 *
 * @Author:  xieyc && 紫色年华
 * @Date 2019-10-08
 * @Version: 1.0.0
 */
@Repository
public interface MessageReceiveMapper extends BaseMapper<MessageReceive> {

    /**
     * 我的消息分页信息
     * @param page 分页信息
     * @param query 入参
     * @return page
     */
    Page<NoticeVO> pageNotice(Page<NoticeVO> page, MessageReceiveQuery query);

}
