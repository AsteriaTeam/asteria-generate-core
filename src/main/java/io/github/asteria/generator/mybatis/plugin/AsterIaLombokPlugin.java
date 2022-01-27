package io.github.asteria.generator.mybatis.plugin;

import io.github.asteria.generator.util.PluginUtils;
import io.github.asteria.generator.util.PropertiesUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.List;
import java.util.Properties;

public class AsterIaLombokPlugin extends PluginAdapter {

	private boolean lombokBuilder = false;

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
		PluginUtils.addLombokAn(topLevelClass, lombokBuilder);
		return true;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
		Boolean builder = PropertiesUtils.getPropertyAsBoolean(context.getProperties(), "lombokBuilder", false);
		if (builder) {
			this.lombokBuilder = true;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		Boolean builder = PropertiesUtils.getPropertyAsBoolean(properties, "lombokBuilder", false);
		if (builder) {
			this.lombokBuilder = true;
		}
	}
}
