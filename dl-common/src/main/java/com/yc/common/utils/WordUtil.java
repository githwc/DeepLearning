package com.yc.common.utils;

import com.yc.common.constant.CommonConstant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 功能描述:word 操作工具类
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2019-12-19 17:24
 * @Version: 1.0.0
 */
public class WordUtil {

    private static Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding(CommonConstant.CHARSET_UTF_8);
        try {
            configuration.setClassForTemplateLoading(WordUtil.class, "/ftl/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WordUtil() {
        throw new AssertionError();
    }

    /**
     * 导出Word
     * @param response 响应
     * @param map 数据
     * @param fileName 导出文件名称
     * @param path 模板名称
     * @throws IOException
     */
    public static void exportWord(HttpServletResponse response, Map<String,Object> map, String fileName, String path) throws IOException {
        Template freemarkerTemplate = configuration.getTemplate(path);
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            file = createDoc(map,freemarkerTemplate,fileName);
            fin = new FileInputStream(file);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // // 设置浏览器以下载的方式处理该文件名
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(StringUtils.join(fileName, ".doc"), CommonConstant.CHARSET_UTF_8));

            out = response.getOutputStream();
            byte[] buffer = new byte[512];
            int bytesToRead ;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if(fin != null){
                fin.close();
            }
            if(out != null){
                out.close();
            }
            if(file != null){
                file.delete();
            }
        }
    }

    private static File createDoc(Map<?, ?> dataMap, Template template,String fileName) {
        File file = new File(fileName);
        try {
            Writer w = new OutputStreamWriter(new FileOutputStream(file), CommonConstant.CHARSET_UTF_8);
            template.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return file;
    }
}
