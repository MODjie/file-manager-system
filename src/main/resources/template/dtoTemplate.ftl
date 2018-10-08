package ${packagePath};

import java.io.Serializable;

public class ${className}Dto implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list colunmModels as column>
    <#if column.notes != "">
    /**
	 * ${column.notes}
	 */
    </#if>
    private ${column.propertyType} ${column.propertyName};
    </#list>

    <#list colunmModels as column>
    public void set${column.prefixUppercasePropertyName}(${column.propertyType} ${column.propertyName}){
        this.${column.propertyName} = ${column.propertyName};
    }

    public ${column.propertyType} get${column.prefixUppercasePropertyName}(){
        return ${column.propertyName};
    }
    </#list>
}
