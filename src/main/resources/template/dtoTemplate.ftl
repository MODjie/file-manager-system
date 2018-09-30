package ${packagePath};

public class ${className} {
    <#list colunmModels as column>
    <#if column.notes != "">
    /**
	 * ${column.notes}
	 */
    </#if>
    private ${column.propertyType} ${column.propertyName};
    </#list>

    <#list colunmModels as column>
    public ${column.propertyType} get${column.propertyName}(){
        return ${column.propertyName};
    }

    public void set${column.propertyName}(${column.propertyType} ${column.propertyName}){
        this.${column.propertyName} = ${column.propertyName};
    }
    </#list>
}
