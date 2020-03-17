package com.yc.practice.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.common.config.exception.ApiException;
import com.yc.common.config.exception.RunException.RunningException;
import com.yc.common.config.exception.parameterException.ParameterException;
import com.yc.common.constant.CacheConstant;
import com.yc.common.constant.CommonConstant;
import com.yc.common.utils.EncoderUtil;
import com.yc.core.system.entity.SysPermission;
import com.yc.core.system.mapper.SysPermissionMapper;
import com.yc.core.system.model.vo.SysPermissionTree;
import com.yc.core.system.model.vo.TreeModel;
import com.yc.practice.common.dao.DaoApi;
import com.yc.practice.config.filter.JwtUtil;
import com.yc.practice.system.service.SysPermissionService;
import com.yc.practice.system.utils.PermissionOPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
* 功能描述：
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
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private DaoApi daoApi;

    @Override
    public Set<String> getUserPermCodes(String loginName) {
        Set<String> permissionSet = new HashSet<String>();
        List<SysPermission> permissionList = this.baseMapper.queryPermissionByUser(loginName);
        for (SysPermission po : permissionList) {
            if (StringUtils.isNotEmpty(po.getPermsCode())) {
                permissionSet.add(po.getPermsCode());
            }
        }
        return permissionSet;
    }

    @Override
    public JSONObject getUserPermissionByToken(String token, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        if (StringUtils.isEmpty(token)) {
            throw new ParameterException("参数错误:TOKEN不允许为空");
        }
        String loginName = JwtUtil.getUsername(token);
        List<SysPermission> metaList = this.baseMapper.queryPermissionByUser(loginName);
        PermissionOPUtil.addIndexPage(metaList);
        JSONArray menujsonArray = new JSONArray();
        this.getMenuJsonArray(menujsonArray, metaList, null);
        JSONArray authjsonArray = new JSONArray();
        this.getAuthJsonArray(authjsonArray, metaList);
        // 查询所有的权限
        List<SysPermission> allAuthList = this.baseMapper.selectList(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getDelFlag,0)
                .eq(SysPermission::getMenuType,2)
                .eq(SysPermission::getStatus,"1")
        );
        JSONArray allauthjsonArray = new JSONArray();
        this.getAllAuthJsonArray(allauthjsonArray, allAuthList);
        json.put("menu", menujsonArray);
        json.put("auth", authjsonArray);
        json.put("allAuth", allauthjsonArray);
        return json;
    }

    /**
     *  @DESC：组装前端菜单JSON数组
     *  1、遍历第一条菜单权限信息，此时parentJson为null
     *  2、如果是一级菜单，执行@1(封装成json对象),执行@2(添加到jsonArray,继续调用getMenuJsonArray方法,并将完整metaList及封装的json对象传入)
     *      然后遍历所有metaList,如果是一级菜单不执行任何操作,如果是二级菜单并且此parentJson的ID等于当前菜单的父ID,
     *      执行@3(将子菜单组装到children中，将按钮组装到meta->permissionList中,如果不是叶子节点,会继续调用getMenuJsonArray方法封装其下级菜单)
     *  3、如果是二级菜单或三级菜单，不执行任何操作(在一级菜单走完后会循环放入二级三级菜单)
     *
     *   menuType: 类型( 0：一级菜单 1：子菜单 2：按钮 )
     * @param jsonArray 组装的json
     * @param metaList 当前人拥有的菜单权限
     * @param parentJson 父级菜单json对象
     */
    private void getMenuJsonArray(JSONArray jsonArray, List<SysPermission> metaList, JSONObject parentJson) {
        for (SysPermission permission : metaList){
            if (permission.getMenuType() == null){
                continue;
            }
            String tempPid = permission.getParentId();
            // @1
            JSONObject json = getMenuJsonObject(permission);
            if(json==null) {
                continue;
            }
            //@2
            if (parentJson == null && StringUtils.isEmpty(tempPid)) {
                jsonArray.add(json);
                if (!permission.getIsLeaf()) {
                    getMenuJsonArray(jsonArray, metaList, json);
                }
                // @3
            } else if (parentJson != null && StringUtils.isNotEmpty(tempPid) && tempPid.equals(parentJson.getString("id"))) {
                if (permission.getMenuType() == 2) {
                    JSONObject metaJson = parentJson.getJSONObject("meta");
                    if (metaJson.containsKey("permissionList")) {
                        metaJson.getJSONArray("permissionList").add(json);
                    } else {
                        JSONArray permissionList = new JSONArray();
                        permissionList.add(json);
                        metaJson.put("permissionList", permissionList);
                    }
                } else if (permission.getMenuType() == 1 || permission.getMenuType() == 0) {
                    if (parentJson.containsKey("children")) {
                        parentJson.getJSONArray("children").add(json);
                    } else {
                        JSONArray children = new JSONArray();
                        children.add(json);
                        parentJson.put("children", children);
                    }

                    if (!permission.getIsLeaf()) {
                        getMenuJsonArray(jsonArray, metaList, json);
                    }
                }
            }
        }
    }

    /**
     * @DESC：单条权限信息拼装JSON对象
     *  类型(0：一级菜单 1：子菜单 2：按钮)
     * @param permission
     * @return
     */
    private JSONObject getMenuJsonObject(SysPermission permission) {
        JSONObject json = new JSONObject();
        if (permission.getMenuType() == 2) {
            return null;
        } else if (permission.getMenuType() == 0 || permission.getMenuType() == 1) {
            json.put("id", permission.getSysPermissionId());
            if (permission.getIsRoute()) {
                json.put("route", "1");// 表示生成路由
            } else {
                json.put("route", "0");// 表示不生成路由
            }
            if (isWWWHttpUrl(permission.getUrl())) {
                json.put("path", EncoderUtil.MD5Encode(permission.getUrl(), "utf-8"));
            } else {
                json.put("path", permission.getUrl());
            }
            // 重要规则：路由name (通过URL生成路由name,路由name供前端开发，页面跳转使用)
            if (StringUtils.isNotEmpty(permission.getComponentName())) {
                json.put("name", permission.getComponentName());
            } else {
                json.put("name", urlToRouteName(permission.getUrl()));
            }
            // 是否隐藏路由，默认都是显示的
            if (permission.getHidden().equals(1)) {
                json.put("hidden", true);
            }
            // 聚合路由
            if (permission.getAlwaysShow() != null && permission.getAlwaysShow()) {
                json.put("alwaysShow", true);
            }
            json.put("component", permission.getComponent());
            JSONObject meta = new JSONObject();
            // 默认所有的菜单都加路由缓存，提高系统性能
            meta.put("keepAlive", "true");
            meta.put("title", permission.getName());
            if (StringUtils.isEmpty(permission.getParentId())) {
                // 一级菜单跳转地址
                json.put("redirect", permission.getRedirect());
                if (StringUtils.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            } else {
                if (StringUtils.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            }
            if (isWWWHttpUrl(permission.getUrl())) {
                meta.put("url", permission.getUrl());
            }
            json.put("meta", meta);
        }
        return json;
    }


    /**
     *  组装前端按钮权限JSON数组——当前人的权限按钮
     *
     * @param jsonArray
     * @param metaList
     */
    private void getAuthJsonArray(JSONArray jsonArray,List<SysPermission> metaList) {
        for (SysPermission permission : metaList) {
            if(permission.getMenuType()==null) {
                continue;
            }
            JSONObject json = null;
            if(permission.getMenuType()==2&&"1".equals(permission.getStatus())) {
                json = new JSONObject();
                json.put("action", permission.getPermsCode());
                json.put("type", permission.getPermsType());
                json.put("describe", permission.getName());
                jsonArray.add(json);
            }
        }
    }

    /**
     *  组装前端按钮权限JSON数组——所有按钮
     * @param jsonArray
     * @param allList
     */
    private void getAllAuthJsonArray(JSONArray jsonArray,List<SysPermission> allList) {
        JSONObject json;
        for (SysPermission permission : allList) {
            json = new JSONObject();
            json.put("action", permission.getPermsCode());
            json.put("status", permission.getStatus());
            json.put("type", permission.getPermsType());
            json.put("describe", permission.getName());
            jsonArray.add(json);
        }
    }

    /**
     *  @DESC：判断是否外网URL
     *  例如： http://localhost:8080/lionherding/swagger-ui.html#/
     *  支持特殊格式： {{ *  window._CONFIG['domianURL'] }}/druid/ {{ JS代码片段 }}，前台解析会自动执行JS代码片段
     *
     * @param url
     * @return
     */
    private boolean isWWWHttpUrl(String url) {
        if (url != null && (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("{{"))) {
            return true;
        }
        return false;
    }

    /**
     * @DESC：通过URL生成路由name
     *  去掉URL前缀斜杠，替换内容中的斜杠‘/’为-）
     *  举例： URL = /isystem/role RouteName = isystem-role
     *
     * @param url
     * @return
     */
    private String urlToRouteName(String url) {
        if (StringUtils.isNotEmpty(url)) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            url = url.replace("/", "-");
            url = url.replace(":", "@");
            return url;
        } else {
            return null;
        }
    }

    @Override
    public List<SysPermissionTree> permissionlist() {
        List<SysPermission> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getDelFlag, CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysPermission::getSort)
        );
        List<SysPermissionTree> treeList = new ArrayList<>();
        this.getTreeList(treeList, list, null);
        return treeList;
    }

    @Override
    public Map<String, Object> queryTreeList() {
        Map<String, Object> resMap = new HashMap<String, Object>();
        // 全部权限id
        List<String> ids = new ArrayList<>();
        List<SysPermission> list = this.baseMapper.selectList(new LambdaQueryWrapper<SysPermission>()
            .eq(SysPermission::getDelFlag,CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysPermission::getSort)
        );
        for (SysPermission curr : list) {
            ids.add(curr.getSysPermissionId());
        }
        //全部树节点
        List<TreeModel> treeList = new ArrayList<>();
        getTreeModelList(treeList, list, null);
        resMap.put("treeList", treeList);
        resMap.put("ids", ids);
        return resMap;
    }


    @Override
    @CacheEvict(value = CacheConstant.SYS_PERMISSIONS_CACHE,allEntries=true)
    public void addPermission(SysPermission sysPermission) throws ApiException {
        //判断是否是一级菜单，是的话清空父菜单
        if(CommonConstant.MENU_TYPE_0.equals(sysPermission.getMenuType())) {
            sysPermission.setParentId(null);
        }
        String pid = sysPermission.getParentId();
        //设置父节点不为叶子节点
        if(StringUtils.isNotEmpty(pid)) {
            this.baseMapper.setMenuLeaf(pid, 0);
        }
        sysPermission.setIsLeaf(true);
        sysPermission.setCreateUserId(daoApi.getCurrentUser().getSysUserId());
        this.save(sysPermission);
    }

    @Override
    @CacheEvict(value = CacheConstant.SYS_PERMISSIONS_CACHE,allEntries=true)
    public void editPermission(SysPermission sysPermission) {
        SysPermission oldPer = this.getById(sysPermission.getSysPermissionId());
        if(oldPer==null) {
            throw new ParameterException("未找到菜单信息");
        }else {
            //----------------------------------------------------------------------
            //Step1.判断是否是一级菜单，是的话清空父菜单ID
            if(CommonConstant.MENU_TYPE_0.equals(sysPermission.getMenuType())) {
                sysPermission.setParentId(null);
            }
            //Step2.判断菜单下级是否有菜单，无则设置为叶子节点
            int count = this.count(new QueryWrapper<SysPermission>().lambda().eq(SysPermission::getParentId, sysPermission.getSysPermissionId()));
            if(count==0) {
                sysPermission.setIsLeaf(true);
            }
            //----------------------------------------------------------------------
            this.updateById(sysPermission);
            //如果当前菜单的父菜单变了，则需要修改新父菜单和老父菜单的，叶子节点状态
            String newPid = sysPermission.getParentId();
            if((StringUtils.isNotEmpty(newPid) && !newPid.equals(oldPer.getParentId())) || StringUtils.isEmpty(newPid)&& StringUtils.isNotEmpty(oldPer.getParentId())) {
                //a.设置新的父菜单不为叶子节点
                this.baseMapper.setMenuLeaf(newPid, 0);
                //b.判断老的菜单下是否还有其他子菜单，没有的话则设置为叶子节点
                int cc = this.count(new QueryWrapper<SysPermission>().lambda().eq(SysPermission::getParentId, oldPer.getParentId()));
                if(cc==0) {
                    if(StringUtils.isNotEmpty(oldPer.getParentId())) {
                        this.baseMapper.setMenuLeaf(oldPer.getParentId(), 1);
                    }
                }

            }
        }
    }

    @Override
    @CacheEvict(value = CacheConstant.SYS_PERMISSIONS_CACHE,allEntries=true)
    public void deletePermission(String id) throws RunningException {
        SysPermission sysPermission = this.getById(id);
        if(sysPermission==null) {
            throw new RunningException("未找到菜单信息");
        }
        String pid = sysPermission.getParentId();
        if(StringUtils.isNotEmpty(pid)) {
            int count = this.count(new QueryWrapper<SysPermission>().lambda().eq(SysPermission::getParentId, pid));
            if(count==1) {
                //若父节点无其他子节点，则该父节点是叶子节点
                this.baseMapper.setMenuLeaf(pid, 1);
            }
        }
        // 该节点可能是子节点但也可能是其它节点的父节点,所以需要级联删除
        this.removeChildrenBy(sysPermission.getSysPermissionId());
        //执行逻辑删除
        sysPermission.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.baseMapper.updateById(sysPermission);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] arr = ids.split(",");
        for (String id : arr) {
            if (StringUtils.isNotEmpty(id)) {
                this.deletePermission(id);
            }
        }
    }

    @Override
    public Map<String,Object> permissionMapTree() {
        List<String> ids = new ArrayList<>();
        List<SysPermission> list = this.list(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getDelFlag, CommonConstant.DEL_FLAG_0)
                .orderByAsc(SysPermission::getSort)
        );
        for(SysPermission sysPer : list) {
            ids.add(sysPer.getSysPermissionId());
        }
        List<TreeModel> treeList = new ArrayList<>();
        this.getTreeModelList(treeList, list, null);
        Map<String,Object> resMap = new HashMap<String,Object>();
        //全部树节点数据
        resMap.put("treeList", treeList);
        //全部树ids
        resMap.put("ids", ids);
        return resMap;
    }

    /**
     *  组装菜单树 ====== permissionlist 子方法 ========
     * @param treeList
     * @param metaList
     * @param temp
     */
    private void getTreeList(List<SysPermissionTree> treeList, List<SysPermission> metaList, SysPermissionTree temp) {
        for (SysPermission permission : metaList) {
            String tempPid = permission.getParentId();
            SysPermissionTree tree = new SysPermissionTree(permission);
            if (temp == null && StringUtils.isEmpty(tempPid)) {
                treeList.add(tree);
                if (!tree.isLeaf()) {
                    getTreeList(treeList, metaList, tree);
                }
            } else if (temp != null && tempPid != null && tempPid.equals(temp.getId())) {
                temp.getChildren().add(tree);
                if (!tree.isLeaf()) {
                    getTreeList(treeList, metaList, tree);
                }
            }
        }
    }

    /**
     * ======= permissionMapTree 子方法 =======
     * @param treeList
     * @param metaList
     * @param temp
     */
    private void getTreeModelList(List<TreeModel> treeList, List<SysPermission> metaList, TreeModel temp) {
        for (SysPermission permission : metaList) {
            String tempPid = permission.getParentId();
            TreeModel tree = new TreeModel(permission);
            if (temp == null && StringUtils.isEmpty(tempPid)) {
                treeList.add(tree);
                if (!tree.isLeaf()) {
                    getTreeModelList(treeList, metaList, tree);
                }
            } else if (temp != null && tempPid != null && tempPid.equals(temp.getKey())) {
                temp.getChildren().add(tree);
                if (!tree.isLeaf()) {
                    getTreeModelList(treeList, metaList, tree);
                }
            }
        }
    }

    /**
     * 根据父id删除其关联的子节点数据 ====== deletePermission 子方法 =========
     *
     * @return
     */
    private void removeChildrenBy(String parentId) {
        // 查出该主键下的所有子级
        List<SysPermission> permissionList = this.list(new LambdaQueryWrapper<SysPermission>()
            .eq(SysPermission::getParentId,parentId)
        );
        if (permissionList != null && permissionList.size() > 0) {
            SysPermission sysPermission = new SysPermission();
            sysPermission.setDelFlag(CommonConstant.DEL_FLAG_1);
            String id ; // id
            int num ; // 查出的子级数量
            // 如果查出的集合不为空, 则先删除所有
            this.update(sysPermission,new LambdaQueryWrapper<SysPermission>()
                    .eq(SysPermission::getParentId,parentId)
            );
            // 再遍历刚才查出的集合, 根据每个对象,查找其是否仍有子级
            for (int i = 0, len = permissionList.size(); i < len; i++) {
                id = permissionList.get(i).getSysPermissionId();
                num = this.count(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getParentId, id));
                if (num > 0) {// 如果有, 则递归
                    this.removeChildrenBy(id);
                }
            }
        }
    }
}
