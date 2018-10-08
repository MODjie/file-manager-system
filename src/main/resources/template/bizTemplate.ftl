package ${packagePath};

import java.util.List;

<#list importClassPaths as importClassPath>
    <#if importClassPath != "">
import ${importClassPath}
    </#if>
</#list>
import com.sinoservices.minima.mybatis.BaseBiz;

public interface ${className}Biz extends BaseBiz<${className}Condition, ${className}> {

    void logicBatchDelete(List<${className}> ${prefixLowercaseClassName}List);

}
