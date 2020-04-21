package com.jie.domain.codegenerater;

import lombok.Data;

@Data
public class ColunmModel {
	/**
	 * 列名
	 */
	private String columName;
	/**
	 * 属性名
	 */
	private String propertyName;
	/**
	 * 属性名（首字母大写）
	 */
	private String prefixUppercasePropertyName;
	/**
	 * 属性类型
	 */
	private String propertyType;
	/**
	 * 注释
	 */
	private String notes;

}
