package io.github.asteria.generator.mybatis.plugin;

import java.util.List;
import java.util.Properties;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.consts.Const;
import io.github.asteria.generator.util.PluginUtils;
import io.github.asteria.generator.util.PropertiesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.internal.util.JavaBeansUtil;

/**
 * service plugin
 */
public class AsteriaServicePlugin extends AsteriaAbstractPlugin {

	private String servicePackage;

	private String servicePrefix;

	private String serviceSuffix;

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

		introspectedTable.setAttribute(Const.SERVICE_PACKAGE_ATTR, servicePackage);
		introspectedTable.setAttribute(Const.SERVICE_PREFIX_ATTR, servicePrefix);
		introspectedTable.setAttribute(Const.SERVICE_SUFFIX_ATTR, serviceSuffix);

		List<GeneratedJavaFile> javaFileList = Lists.newArrayList();
		String domainName = PluginUtils.getDomainName(introspectedTable);
		String packageVal = StringUtils.join(new String[]{basePackage, this.servicePackage}, ".");
		String serviceName = this.servicePrefix + domainName + this.serviceSuffix;

		FullyQualifiedJavaType entityType = PluginUtils.getEntityType(introspectedTable, this.context);
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

		javaFileList.add(new GeneratedJavaFile(interfaceClass, targetProject, javaFileEncoding, context.getJavaFormatter()));


		return javaFileList;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		this.servicePackage = PropertiesUtils.getPropertyAsString(properties, "servicePackage", "service");
		this.servicePrefix = PropertiesUtils.getPropertyAsString(properties, "servicePrefix", "");
		this.serviceSuffix = PropertiesUtils.getPropertyAsString(properties, "serviceSuffix", "");
	}
}
