package io.github.asteria.generator.mybatis.plugin;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.util.PluginUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.List;
import java.util.Properties;

public class AsteriaOptionalPlugin extends PluginAdapter {

	private final AsteriaContext asteriaContext = new AsteriaContext();


	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType domainType = PluginUtils.getDomainType(introspectedTable);
		FullyQualifiedJavaType optionalType = new FullyQualifiedJavaType("java.util.Optional");
		optionalType.addTypeArgument(domainType);
		method.setReturnType(optionalType);

		interfaze.addImportedType(optionalType);

		introspectedTable.setAttribute("optional.enabled", true);
		return true;
	}

	@Override
	public boolean clientSelectOneMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
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
		asteriaContext.setContext(context);
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		asteriaContext.setProperties(properties);
	}
}
