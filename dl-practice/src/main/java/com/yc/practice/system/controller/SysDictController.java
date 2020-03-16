package com.yc.practice.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.constant.CommonConstant;
import com.yc.practice.common.log.WriteLog;
import com.yc.core.system.entity.SysDict;
import com.yc.core.system.model.query.DictQuery;
import com.yc.core.tree.TreeNode;
import com.yc.practice.system.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 功能描述：字典前端控制器
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysDict")
@Slf4j
@Api(tags = "系统字典")
public class SysDictController {

    private final SysDictService service;

    @Autowired
    public SysDictController(SysDictService service) {
        this.service = service;
    }

    @GetMapping(value = "/dictTree")
    @ApiOperation(value = "加载字典树",notes = "加载所有字典")
    @WriteLog(opPosition = "加载字典树" ,optype = CommonConstant.OPTYPE_READ)
    public List<TreeNode> dictTree(@RequestParam(value = "name",required = false)String name){
        try {
            return service.dictTree(name);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping("/childrenDict")
    @ApiOperation(value = "查询子级字典",notes = "查询子级字典")
    @WriteLog(opPosition = "查询子级字典" ,optype = CommonConstant.OPTYPE_READ)
    public Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery){
        return service.childrenDict(page,dictQuery);
    }

    @ApiOperation(value = "字典添加",notes = "字典添加")
    @PostMapping(value = "/add")
    @WriteLog(opPosition = "字典添加" ,optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody SysDict sysDict) {
        // try {
            service.create(sysDict);
        // } catch (Exception e) {
        //     throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        // }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "字典修改",notes = "字典修改")
    @WriteLog(opPosition = "字典修改" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody SysDict sysDict) {
        try{
            service.editByDictId(sysDict);
        }catch (Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "字典删除",notes = "字典删除")
    @WriteLog(opPosition = "字典删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam("sysDictId") String sysDictId) {
        try{
            service.deleteAlone(sysDictId);
        }catch(Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "字典批量删除",notes = "字典批量删除")
    @WriteLog(opPosition = "字典批量删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(@RequestParam("ids") String ids) {
        try{
            service.deleteBatch(ids);
        }catch(Exception e){
            throw new RunningException("".equals(e.getMessage()) ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    /**
     * 查询指定字典
     * @return list
     */
    @GetMapping("/getDict")
    public List<SysDict> getDict(@RequestParam("skey")String skeys,@RequestParam("mode")String mode){
        return service.getDict(skeys);
    }
}
