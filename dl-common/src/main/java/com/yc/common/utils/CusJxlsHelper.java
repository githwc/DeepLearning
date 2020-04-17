package com.yc.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.formula.FastFormulaProcessor;
import org.jxls.formula.FormulaProcessor;
import org.jxls.formula.StandardFormulaProcessor;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-17
 * @Version: 1.0.0
 */
@Slf4j
public class CusJxlsHelper extends JxlsHelper {

    // 子类去实例化父类
    public static JxlsHelper getInstance() {
        return new CusJxlsHelper();
    }

    @Override
    public void processTemplate(Context context, Transformer transformer) throws IOException {
        AreaBuilder areaBuilder = this.getAreaBuilder();
        boolean processFormulas = this.isProcessFormulas();

        areaBuilder.setTransformer(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Iterator var4 = xlsAreaList.iterator();

        Area xlsArea;
        while (var4.hasNext()) {
            xlsArea = (Area) var4.next();
            xlsArea.applyAt(new CellRef(xlsArea.getStartCellRef().getCellName()), context);
        }

        if (processFormulas) {
            var4 = xlsAreaList.iterator();

            while (var4.hasNext()) {
                xlsArea = (Area) var4.next();
                this.setFormulaProcessor(xlsArea);
                xlsArea.processFormulas();
            }
        }
        //增加了删除template sheet的业务控制
        if (this.isDeleteTemplateSheet()) {
            transformer.deleteSheet("TEMPLATE");
        }

        transformer.write();
    }

    private Area setFormulaProcessor(Area xlsArea) {
        FormulaProcessor fp = this.getFormulaProcessor();
        if (fp == null) {
            if (this.isUseFastFormulaProcessor()) {
                fp = new FastFormulaProcessor();
            } else {
                fp = new StandardFormulaProcessor();
            }
        }

        xlsArea.setFormulaProcessor((FormulaProcessor) fp);
        return xlsArea;
    }
}
