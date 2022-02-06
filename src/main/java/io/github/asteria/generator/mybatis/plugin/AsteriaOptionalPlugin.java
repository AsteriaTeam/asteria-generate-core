package io.github.asteria.generator.mybatis.plugin;

import java.util.List;
import java.util.Properties;

import io.github.asteria.generator.mybatis.consts.Const;
import io.github.asteria.generator.util.PluginUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.config.Context;

public class AsteriaOptionalPlugin extends PluginAdapter {


	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		introspectedTable.setAttribute(Const.OPTIONAL_ENABLED_ATTR, true);
		FullyQualifiedJavaType domainType = PluginUtils.getDomainType(introspectedTable);
		FullyQualifiedJavaType optionalType = new FullyQualifiedJavaType("java.util.Optional");
		optionalType.addTypeArgument(domainType);
		method.setReturnType(optionalType);
		interfaze.addImportedType(optionalType);
		return true;
	}

	@Override
	public boolean clientSelectOneMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		introspectedTable.setAttribute(Const.OPTIONAL_ENABLED_ATTR, true);
		FullyQualifiedJavaType domainType = PluginUtils.getDomainType(introspectedTable);
		FullyQualifiedJavaType optionalType = new FullyQualifiedJavaType("java.util.Optional");
		optionalType.addTypeArgument(domainType);
		method.setReturnType(optionalType);
		interfaze.addImportedType(optionalType);
		return true;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
