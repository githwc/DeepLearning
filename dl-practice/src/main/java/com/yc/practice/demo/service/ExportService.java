package com.yc.practice.demo.service;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-02-16
 * @Version: 1.0.0
 */
public interface ExportService {

    /**
     * 导出word
     * @param response 响应
     */
    void export(HttpServletResponse response);
}
