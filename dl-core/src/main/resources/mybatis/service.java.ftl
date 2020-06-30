package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * 功能描述:
 *
 * @Author ${author} && 紫色年华
 * @Date ${date}
 * @Version: 1.0.0
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
