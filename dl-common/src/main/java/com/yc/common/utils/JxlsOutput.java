package com.yc.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jxls.common.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 功能描述:导出Excel工具类
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-04-17
 * @Version: 1.0.0
 */
@Slf4j
public class JxlsOutput {

    /**
     * 输出到浏览器
     *
     * @param response     响应
     * @param fileName     文件名，含扩展名
     * @param templateName 模板文件名，此文件路径只取resources/reports下的文件，含扩展名
     * @param context      excel使用到的参数
     */
    public static void out(HttpServletResponse response, String fileName, String templateName, Context context) {
        log.info("Running {} report output WebBrowser start", fileName);
        try (InputStream is = JxlsOutput.class.getResourceAsStream("/reports/" + templateName)) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String defaultname = "export.xlsx";
            if (StringUtils.isNotEmpty(fileName)) {
                defaultname = fileName;
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(defaultname, "UTF-8"));

            try (OutputStream os = response.getOutputStream()) {
                CusJxlsHelper.getInstance().setDeleteTemplateSheet(true).processTemplate(is, os, context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
