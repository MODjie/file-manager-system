package com.jie.utils.genneraterutils;

import com.jie.domain.codegenerater.ColunmModel;
import com.jie.domain.codegenerater.PropertyModel;
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

public class CodeGeneraterUtil {

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

	/**
	 * 生成模板
	 * @param dataModel
	 */
	public static void generateTemplate(Object dataModel){
		CodeGeneraterUtil test = new CodeGeneraterUtil();
		Template template = null;
		try {
			test.freeMarkerInit();
			template = test.getTemplate("propertyTemplate.ftl");
			Writer out = new OutputStreamWriter(System.out);
			template.process(dataModel, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
