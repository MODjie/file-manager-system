package com.jie.test;

import com.jie.domain.codegenerater.ColunmModel;
import com.jie.domain.codegenerater.PropertyModel;
import com.jie.utils.genneraterutils.CodeGeneraterUtil;
import com.jie.utils.genneraterutils.DatabaseUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
		PropertyModel propertyModel = new PropertyModel();
		propertyModel.setPackagePath("com.jie.domain");
		propertyModel.setClassName("MdmFeeCategory");
		List<ColunmModel> colunmModels = DatabaseUtil.getPropertyModels("mdm_fee_category");
		for (ColunmModel colunmModel : colunmModels) {
			DatabaseUtil.convertDbTypeToJava(colunmModel);
			String newName = DatabaseUtil.lineToHump(colunmModel.getPropertyName());
			colunmModel.setPropertyName(newName);
		}
		propertyModel.setColunmModels(colunmModels);
		CodeGeneraterUtil.generateTemplate(propertyModel);
	}
}
