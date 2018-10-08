package com.jie.domain.codegenerater;

import java.util.List;

public class TemplateDataModel {
	/**
	 * 包路径
	 */
	private String packagePath;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 首字母小写的类名
	 */
	private String prefixLowercaseClassName;
	/**
	 * 全大写的类名
	 */
	private String uppercaseClassName;
	/**
	 * 表模型集合
	 */
	private List<ColunmModel> colunmModels;
	/**
	 * 要导入的类路径
	 */
	private List<String> importClassPaths;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * mapper.xml文件命名空间
	 */
	private String namespace;
	/**
	 * domain类所在相对路径
	 */
	private String domainPath;
	/**
	 * condition类所在相对路径
	 */
	private String conditionPath;
	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<ColunmModel> getColunmModels() {
		return colunmModels;
	}

	public void setColunmModels(List<ColunmModel> colunmModels) {
		this.colunmModels = colunmModels;
	}

	public List<String> getImportClassPaths() {
		return importClassPaths;
	}

	public void setImportClassPaths(List<String> importClassPaths) {
		this.importClassPaths = importClassPaths;
	}

	public String getPrefixLowercaseClassName() {
		return prefixLowercaseClassName;
	}

	public void setPrefixLowercaseClassName(String prefixLowercaseClassName) {
		this.prefixLowercaseClassName = prefixLowercaseClassName;
	}

	public String getUppercaseClassName() {
		return uppercaseClassName;
	}

	public void setUppercaseClassName(String uppercaseClassName) {
		this.uppercaseClassName = uppercaseClassName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getDomainPath() {
		return domainPath;
	}

	public void setDomainPath(String domainPath) {
		this.domainPath = domainPath;
	}

	public String getConditionPath() {
		return conditionPath;
	}

	public void setConditionPath(String conditionPath) {
		this.conditionPath = conditionPath;
	}
}
