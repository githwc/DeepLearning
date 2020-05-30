package com.yc.practice.selfOffice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yc.core.selfOffice.entity.WorkBench;
import com.yc.practice.selfOffice.service.WorkBenchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 *
 * 功能描述：工作台 控制层
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-04-15
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/workBench")
@Slf4j
public class WorkBenchController {

    @Autowired
    public WorkBenchService iWorkBenchService;

    /**
     * 我的工作台
     * @return list
     */
    @GetMapping
    public List<WorkBench> list(){
        return iWorkBenchService.list(new LambdaQueryWrapper<WorkBench>()
            .orderByAsc(WorkBench::getSort)
        );
    }

    /**
     * 我的工作台-添加
     */
    @PostMapping
    public void add(@RequestBody WorkBench workBench){
        Random random=new Random();
        if(random.nextInt(2) > 0){
            workBench.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png");
        }else {
           workBench.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/ComBAopevLwENQdKWiIn.png");
        }
        iWorkBenchService.save(workBench);
    }


    /**
     * 我的工作台-修改
     */
    @PutMapping
    public void update(@RequestBody WorkBench workBench){
        Random random=new Random();
        if(random.nextInt(2) > 0){
            workBench.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png");
        }else {
            workBench.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/ComBAopevLwENQdKWiIn.png");
        }
        iWorkBenchService.updateById(workBench);
    }

    /**
     * 我的工作台-删除
     * @param workBenchId 主键
     */
    @DeleteMapping
    public void delete (@Param("workBenchId")String workBenchId){
        iWorkBenchService.removeById(workBenchId);
    }


}
