package com.jie.domain.codegenerater;

public class ColunmModel {
	/**
	 * 属性名
	 */
	private String propertyName;
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
}
