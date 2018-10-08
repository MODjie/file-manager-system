package ${packagePath};

import com.sinoservices.minima.mybatis.BaseLogicDeleteMapper;
import com.sinoservices.minima.mybatis.BaseMapper;

<#list importClassPaths as importClassPath>
    <#if importClassPath != "">
import ${importClassPath}
    </#if>
</#list>

public interface ${className}Mapper extends BaseMapper<${className}Condition, ${className}>,BaseLogicDeleteMapper<${className}Condition, ${className}> {

}