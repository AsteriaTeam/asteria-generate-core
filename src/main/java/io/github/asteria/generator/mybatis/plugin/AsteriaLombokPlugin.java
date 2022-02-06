package io.github.asteria.generator.mybatis.plugin;

import java.util.List;
import java.util.Properties;

import io.github.asteria.generator.mybatis.consts.Const;
import io.github.asteria.generator.util.PluginUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

public class AsteriaLombokPlugin extends PluginAdapter {

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
		introspectedTable.setAttribute(Const.LOMBOK_ENABLED_ATTR, true);
		introspectedTable.setAttribute(Const.LOMBOK_BUILDER_ENABLED_ATTR, lombokBuilder);
		PluginUtils.addLombokAn(topLevelClass, lombokBuilder);
		return true;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		lombokBuilder = Boolean.parseBoolean(properties.getProperty("lombokBuilder", "false"));
	}
}
