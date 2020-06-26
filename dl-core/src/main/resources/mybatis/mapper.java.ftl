package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.springframework.stereotype.Repository;
/**
 *
 * 功能描述：
 *
 * @Company: 紫色年华
 * @Author ${author}
 * @Date ${date}
 * @Version: 1.0.0
 *
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@Repository
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
