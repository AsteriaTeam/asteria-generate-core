package io.github.asteria.generator.mybatis.plugin;

import java.util.List;
import java.util.Properties;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.consts.Const;
import io.github.asteria.generator.mybatis.plugin.codegen.AbstractServiceGenerator;
import io.github.asteria.generator.mybatis.plugin.codegen.DefaultServiceGenerator;
import io.github.asteria.generator.mybatis.plugin.codegen.dynamic.DynamicServiceGenerator;
import io.github.asteria.generator.util.PluginUtils;
import io.github.asteria.generator.util.PropertiesUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.JavaBeansUtil;

/**
 * service plugin
 */
public class AsteriaServiceImplPlugin extends AsteriaAbstractPlugin {

	private String serviceImplPackage;
	private String serviceImplPrefix;
	private String serviceImplSuffix;


	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

		introspectedTable.setAttribute(Const.SERVICE_IMPL_PACKAGE_ATTR, serviceImplPackage);
		introspectedTable.setAttribute(Const.SERVICE_IMPL_PREFIX_ATTR, serviceImplPrefix);
		introspectedTable.setAttribute(Const.SERVICE_IMPL_SUFFIX_ATTR, serviceImplSuffix);

		List<GeneratedJavaFile> javaFileList = Lists.newArrayList();
		FullyQualifiedJavaType domainType = PluginUtils.getDomainType(introspectedTable);
		FullyQualifiedJavaType entityType = PluginUtils.getEntityType(introspectedTable, this.context);
		FullyQualifiedJavaType mapperType = PluginUtils.getMapperType(introspectedTable);
		FullyQualifiedJavaType serviceType = PluginUtils.getServiceType(introspectedTable, this.context);
		FullyQualifiedJavaType serviceImplType = PluginUtils.getServiceImplType(introspectedTable, this.context);
		FullyQualifiedJavaType beanMapperType = PluginUtils.getBeanMapperType(this.context);


		// impl
		TopLevelClass topLevelClass = new TopLevelClass(serviceImplType);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.addSuperInterface(serviceType);

		topLevelClass.addImportedType(domainType);
		topLevelClass.addImportedType(entityType);
		topLevelClass.addImportedType(mapperType);
		topLevelClass.addImportedType(serviceType);
		topLevelClass.addImportedType(beanMapperType);


		String mapperVar = JavaBeansUtil.getValidPropertyName(mapperType.getShortName());

		// constructor
		Method method = new Method(serviceImplType.getShortName());
		method.addParameter(new Parameter(mapperType, mapperVar));
		method.addParameter(new Parameter(beanMapperType, "mapperFacade"));
		method.setConstructor(true);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addBodyLine("this." + mapperVar + " = " + mapperVar + ";");
		method.addBodyLine("this.mapperFacade = mapperFacade;");
		topLevelClass.addMethod(method);


		Field mapperField = new Field(mapperVar, mapperType);
		mapperField.setVisibility(JavaVisibility.PRIVATE);
		mapperField.setFinal(true);


		Field beanMapperField = new Field("mapperFacade", beanMapperType);
		beanMapperField.setVisibility(JavaVisibility.PRIVATE);
		beanMapperField.setFinal(true);

		topLevelClass.addField(mapperField);
		topLevelClass.addField(beanMapperField);


		AbstractServiceGenerator serviceGenerator = new DefaultServiceGenerator(this.context, introspectedTable, topLevelClass);
		if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3_DSQL) {
			serviceGenerator = new DynamicServiceGenerator(this.context, introspectedTable, topLevelClass);
		}
		serviceGenerator.generatedSaveMethod();
		serviceGenerator.generatedGetMethod();
		serviceGenerator.generatedGetListMethod();


		javaFileList.add(new GeneratedJavaFile(topLevelClass, targetProject, javaFileEncoding, context.getJavaFormatter()));
		return javaFileList;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		this.serviceImplPackage = PropertiesUtils.getPropertyAsString(properties, "serviceImplPackage", "service.impl");
		this.serviceImplPrefix = PropertiesUtils.getPropertyAsString(properties, "serviceImplPrefix", "");
		this.serviceImplSuffix = PropertiesUtils.getPropertyAsString(properties, "serviceImplSuffix", "Impl");
	}
}
