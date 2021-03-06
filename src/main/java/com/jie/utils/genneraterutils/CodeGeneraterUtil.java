package com.jie.utils.genneraterutils;

import com.jie.domain.codegenerater.ColunmModel;
import com.jie.domain.codegenerater.TemplateDataModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGeneraterUtil {

	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
	/**
	 * 生成目录路径
	 */
	private  String outputFolderPathname;
	/**
	 * freemark配置初始化
	 * @throws IOException
	 */
	private void freeMarkerInit() throws IOException{
		cfg.setDirectoryForTemplateLoading(new File("D:/idealWorkspace/file-manager-system/src/main/resources/template"));
		cfg.setDefaultEncoding("UTF-8");
//		cfg.setDirectoryForTemplateLoading(pathname);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	/**
	 * 获取模板
	 * @param templateName
	 * @return
	 * @throws IOException
	 */
	private Template getTemplate (String templateName) throws IOException{
		Template temp = cfg.getTemplate(templateName);
		return temp;
	}

	/**
	 * 生成模板
	 * @param templateDataModelMap
	 * @param pathname
	 * @param templateNameMap
	 */
	public  void generateTemplate(Map<String,TemplateDataModel> templateDataModelMap ,String pathname,Map<String,String> templateNameMap){
		setOutputFolderPathname(pathname);
		CodeGeneraterUtil codeGeneraterUtil = new CodeGeneraterUtil();
		try {
			codeGeneraterUtil.freeMarkerInit();
			for (Map.Entry<String,String> entry:templateNameMap.entrySet()) {
				Template template = codeGeneraterUtil.getTemplate(entry.getValue());
				Path model = chooseModel(entry.getKey().toString());
				BufferedWriter bufferedWriter = Files.newBufferedWriter(model);
				TemplateDataModel dataModel = templateDataModelMap.get(entry.getKey().toString());
				template.process(dataModel, bufferedWriter);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成输出目录
	 */
	private  Map<String,Path> generateOutputFolder()throws IOException{
		Map<String,Path> folderMap = new HashMap<>();
		String pathname = getOutputFolderPathname();
		Path outputRootFolder = Paths.get(pathname);
		Path srcFolder = Paths.get(pathname,"src");
		Path mainFolder = Paths.get(srcFolder.toString(),"main");
		Path javaFolder = Paths.get(mainFolder.toString(),"java");
		Path resourceFolder = Paths.get(mainFolder.toString(),"resource");
		Path jsFolder = Paths.get(mainFolder.toString(),"js");
		Path domainFolder = Paths.get(javaFolder.toString(),"domain");
		Path conditionFolder = Paths.get(javaFolder.toString(),"condition");
		Path dtoFolder = Paths.get(javaFolder.toString(),"dto");
		Path controllerFolder = Paths.get(javaFolder.toString(),"controller");
		Path bizFolder = Paths.get(javaFolder.toString(),"biz");
		Path implFolder = Paths.get(bizFolder.toString(),"impl");
		Path javaMapperFolder = Paths.get(javaFolder.toString(),"mapper");
		Path resourceMapperFolder = Paths.get(resourceFolder.toString(),"mapper");

		Path modelName = getModelName();
		folderMap.put(modelName.toString(),outputRootFolder);
		folderMap.put("src",srcFolder);
		folderMap.put("main",mainFolder);
		folderMap.put("java",javaFolder);
		folderMap.put("resource",resourceFolder);
		folderMap.put("js",jsFolder);
		folderMap.put("domain",domainFolder);
		folderMap.put("condition",conditionFolder);
		folderMap.put("dto",dtoFolder);
		folderMap.put("controller",controllerFolder);
		folderMap.put("biz",bizFolder);
		folderMap.put("impl",implFolder);
		folderMap.put("javaMapper",javaMapperFolder);
		folderMap.put("resourceMapper",resourceMapperFolder);

		for (Map.Entry<String, Path> entry : folderMap.entrySet()) {
			Files.createDirectories(entry.getValue());
		}
		return folderMap;
	}

	/**
	 * 根据要生成目录的路径获取modelname
	 * @return
	 */
	private  Path getModelName(){
		Path outputRootFolder = Paths.get(getOutputFolderPathname());
		//获取pathname的最后一个文件夹名称
		int nameCount = outputRootFolder.getNameCount();
		Path modelName = outputRootFolder.getName(nameCount-1);
		return modelName;
	}

	/**
	 * 获取数据模板
	 * @param columName
	 * @return
	 */
	public TemplateDataModel getTemplateDataModel(String columName,String prefixPackagePath, String suffixPackagePath){

		TemplateDataModel templateDataModel = new TemplateDataModel();
		if (suffixPackagePath.contains("apper")){
			suffixPackagePath = "mapper";
		}
		templateDataModel.setPackagePath(prefixPackagePath+suffixPackagePath);
		String className = getClassName(columName);
		templateDataModel.setClassName(className);
		String prefixLowercaseClassname = getPrefixLowercaseClassName(columName);
		templateDataModel.setPrefixLowercaseClassName(prefixLowercaseClassname);
		String uppercaseClassName = getUppercaseClassName(columName);
		templateDataModel.setUppercaseClassName(uppercaseClassName);
		templateDataModel.setTableName(columName);
		templateDataModel.setNamespace(prefixPackagePath+suffixPackagePath+"."+className+"Mapper");
		templateDataModel.setDomainPath(prefixPackagePath+"domain."+className);
		templateDataModel.setConditionPath(prefixPackagePath+"condition."+className+"Condition");

		List<String> importClasspaths = getImportClassPaths(columName,prefixPackagePath,suffixPackagePath);
		templateDataModel.setImportClassPaths(importClasspaths);

		List<ColunmModel> colunmModels = DatabaseUtil.getPropertyModels(columName);
		for (ColunmModel colunmModel : colunmModels) {
			DatabaseUtil.convertDbTypeToJava(colunmModel);
			colunmModel.setColumName(colunmModel.getPropertyName());
			String newName = DatabaseUtil.lineToHump(colunmModel.getPropertyName());
			colunmModel.setPropertyName(newName);
			String prefixUppcasePropertyName = getPrefixUppercasePropertyName(newName);
			colunmModel.setPrefixUppercasePropertyName(prefixUppcasePropertyName);
		}
		templateDataModel.setColunmModels(colunmModels);
		return templateDataModel;
	}

	/**
	 * 获取首字母大写的属性名
	 * @param propertyName
	 * @return
	 */
	public String getPrefixUppercasePropertyName(String propertyName){
		String suffixStr = propertyName.substring(1);
		char prefixChar = propertyName.charAt(0);
		String prefixStr = String.valueOf(prefixChar);
		String newPropertyName =prefixStr.toUpperCase()+suffixStr;
		return newPropertyName;
	}

	/**
	 * 获取导入类的路径集合
	 * @param columName
	 * @param prefixPackagePath
	 * @param suffixPackagePath
	 * @return
	 */
	private List<String> getImportClassPaths(String columName,String prefixPackagePath,String suffixPackagePath){
		String className = getClassName(columName);
		String prefixLowercaseClassname = getPrefixLowercaseClassName(columName);
		List<String> importClasspaths = new ArrayList<>();

		switch (suffixPackagePath){
			case "biz":
				importClasspaths.add(prefixPackagePath+"domain."+className+";");
				importClasspaths.add(prefixPackagePath+"condition."+className+"Condition;");
				break;
			case "impl":
				importClasspaths.add(prefixPackagePath+"biz."+className+"Biz;");
				importClasspaths.add(prefixPackagePath+"domain."+className+";");
				importClasspaths.add(prefixPackagePath+"condition."+className+"Condition;");
				importClasspaths.add(prefixPackagePath+"mapper."+className+"Mapper;");
				break;
			case "mapper":
				importClasspaths.add(prefixPackagePath+"domain."+className+";");
				importClasspaths.add(prefixPackagePath+"condition."+className+"Condition;");
				break;
			default:
				break;
		}
		return importClasspaths;
	}

	/**
	 * 获取要生成的文件类名
	 * @param columName
	 * @return
	 */
	public String getClassName(String columName){
		String className = DatabaseUtil.lineToHump(columName);
		String suffixStr = className.substring(1);
		char prefixChar = className.charAt(0);
		String prefixStr = String.valueOf(prefixChar);
		String newClassName =prefixStr.toUpperCase()+suffixStr;
		return newClassName;
	}

	/**
	 * 获取首字母小写的类名
	 * @param columName
	 * @return
	 */
	private String getPrefixLowercaseClassName(String columName){
		String className = DatabaseUtil.lineToHump(columName);
		return className;
	}

	/**
	 * 获取字母全部大写类名
	 * @param columName
	 * @return
	 */
	private String getUppercaseClassName(String columName){
		String className = DatabaseUtil.lineToHump(columName);
		return className.toUpperCase();
	}

	/**
	 * 根据key值获取要生成的文件名字
	 * @param key
	 * @return
	 * @throws IOException
	 */
	private  Path chooseModel(String key) throws IOException{
		Map<String,Path> folderMap = generateOutputFolder();
		Path model = null;
		String modelName = getModelName().toString();
		switch (key){
			case "domain":
				model = Paths.get(folderMap.get(key).toString(),modelName+".java");
				break;
			case "condition":
				model = Paths.get(folderMap.get(key).toString(),modelName+"Condition.java");
				break;
			case "dto":
				model = Paths.get(folderMap.get(key).toString(),modelName+"Dto.java");
				break;
			case "controller":
				model = Paths.get(folderMap.get(key).toString(),modelName+"Controller.java");
				break;
			case "biz":
				model = Paths.get(folderMap.get(key).toString(),modelName+"Biz.java");
				break;
			case "impl":
				model = Paths.get(folderMap.get(key).toString(),modelName+"BizImpl.java");
				break;
			case "javaMapper":
				model = Paths.get(folderMap.get(key).toString(),modelName+"Mapper.java");
				break;
			case "resourceMapper":
				model = Paths.get(folderMap.get(key).toString(),modelName+"Mapper.xml");
				break;
			default:
				break;
		}
		return model;
	}

	public  String getOutputFolderPathname() {
		return outputFolderPathname;
	}

	public  void setOutputFolderPathname(String outputFolderPathname) {
		this.outputFolderPathname = outputFolderPathname;
	}
}
