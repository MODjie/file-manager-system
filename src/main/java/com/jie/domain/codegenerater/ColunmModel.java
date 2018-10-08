package com.jie.domain.codegenerater;

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

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getColumName() {
		return columName;
	}

	public void setColumName(String columName) {
		this.columName = columName;
	}

	public String getPrefixUppercasePropertyName() {
		return prefixUppercasePropertyName;
	}

	public void setPrefixUppercasePropertyName(String prefixUppercasePropertyName) {
		this.prefixUppercasePropertyName = prefixUppercasePropertyName;
	}
}
