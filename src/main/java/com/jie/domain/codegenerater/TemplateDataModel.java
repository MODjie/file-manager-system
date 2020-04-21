package com.jie.domain.codegenerater;

import lombok.Data;

import java.util.List;

@Data
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
}
