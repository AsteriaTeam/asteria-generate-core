package io.github.asteria.generator.mybatis.plugin;

import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.util.PluginUtils;
import io.github.asteria.generator.util.PropertiesUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;

import java.util.List;
import java.util.Properties;

public class AsteriaLombokPlugin extends PluginAdapter {

	private final AsteriaContext asteriaContext = new AsteriaContext();

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		introspectedTable.setAttribute("lombok.enabled",true);
		PluginUtils.addLombokAn(topLevelClass, asteriaContext.isLombokBuilder());
		return true;
	}
}
