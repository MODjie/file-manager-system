package com.jie.test;

import com.jie.domain.codegenerater.ColunmModel;
import com.jie.domain.codegenerater.TemplateDataModel;
import com.jie.utils.genneraterutils.CodeGeneraterUtil;
import com.jie.utils.genneraterutils.DatabaseUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {

	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
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

	public static void main(String[] args) {
//		TemplateDataModel templateDataModel = new TemplateDataModel();
//		templateDataModel.setPackagePath("com.jie.domain");
//		templateDataModel.setClassName("MdmConsign");
//		List<ColunmModel> colunmModels = DatabaseUtil.getPropertyModels("mdm_consign");
//		for (ColunmModel colunmModel : colunmModels) {
//			DatabaseUtil.convertDbTypeToJava(colunmModel);
//			String newName = DatabaseUtil.lineToHump(colunmModel.getPropertyName());
//			colunmModel.setPropertyName(newName);
//		}
//		templateDataModel.setColunmModels(colunmModels);

		CodeGeneraterUtil codeGeneraterUtil = new CodeGeneraterUtil();

		Map<String,TemplateDataModel> templateDataModelMap = new HashMap<>();
		TemplateDataModel templateDataModel = new TemplateDataModel();
		templateDataModel = codeGeneraterUtil.getTemplateDataModel("mdm_consign","domain");
		templateDataModelMap.put("domain",templateDataModel);
		templateDataModel = codeGeneraterUtil.getTemplateDataModel("mdm_consign","condition");
		templateDataModelMap.put("condition",templateDataModel);
		templateDataModel = codeGeneraterUtil.getTemplateDataModel("mdm_consign","dto");
		templateDataModelMap.put("dto",templateDataModel);

		String modelName = "MdmConsign";

		Map<String,String> templateMap = new HashMap<>();
		templateMap.put("domain","domainTemplate.ftl");
		templateMap.put("condition","conditionTemplate.ftl");
		templateMap.put("dto","dtoTemplate.ftl");

		codeGeneraterUtil.generateTemplate(templateDataModelMap,"C:/Users/user/Desktop/"+modelName,templateMap);
	}
}
