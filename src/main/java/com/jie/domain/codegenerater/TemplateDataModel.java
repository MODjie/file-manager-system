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

	private List<ColunmModel> colunmModels;

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
}
