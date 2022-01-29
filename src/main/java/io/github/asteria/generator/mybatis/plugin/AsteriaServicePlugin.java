package io.github.asteria.generator.mybatis.plugin;

import java.util.List;
import java.util.Properties;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.util.PluginUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;

/**
 * service plugin
 */
public class AsteriaServicePlugin extends PluginAdapter {

	private final AsteriaContext asteriaContext = new AsteriaContext();

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> javaFileList = Lists.newArrayList();
		String domainName = PluginUtils.getDomainName(introspectedTable);
		String packageVal = StringUtils.join(new String[]{asteriaContext.getBasePackage(), asteriaContext.getServicePackage()}, ".");
		String serviceName = asteriaContext.getServicePrefix() + domainName + asteriaContext.getServiceSuffix();

		FullyQualifiedJavaType entityType = PluginUtils.getEntityType(introspectedTable, asteriaContext);
		FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();

		// interface
		Interface interfaceClass = new Interface(StringUtils.join(new String[]{packageVal, serviceName}, "."));
		interfaceClass.setVisibility(JavaVisibility.PUBLIC);

		interfaceClass.addImportedType(entityType);
		interfaceClass.addImportedType(listType);

		List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();

		if (CollectionUtils.isNotEmpty(primaryKeyColumns)) {
			Method saveMethod = new Method("save");
			saveMethod.setAbstract(true);

			Parameter entityParameter = new Parameter(entityType, JavaBeansUtil.getValidPropertyName(entityType.getShortName()));
			saveMethod.addParameter(entityParameter);
			interfaceClass.addMethod(saveMethod);
		}

		listType.addTypeArgument(entityType);

		Method getMethod = PluginUtils.createMethod("get", entityType, entityType, true, JavaVisibility.PUBLIC);
		interfaceClass.addMethod(getMethod);

		Method getListMethod = PluginUtils.createMethod("getList", entityType, listType, true, JavaVisibility.PUBLIC);
		interfaceClass.addMethod(getListMethod);

		javaFileList.add(new GeneratedJavaFile(interfaceClass, asteriaContext.getTargetProject(), context.getJavaFormatter()));


		return javaFileList;
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
