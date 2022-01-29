package io.github.asteria.generator.mybatis.plugin;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.mybatis.plugin.codegen.AbstractServiceGenerator;
import io.github.asteria.generator.mybatis.plugin.codegen.DefaultServiceGenerator;
import io.github.asteria.generator.mybatis.plugin.codegen.dynamic.DynamicServiceGenerator;
import io.github.asteria.generator.util.PluginUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.List;
import java.util.Properties;

/**
 * service plugin
 */
public class AsteriaServiceImplPlugin extends PluginAdapter {

	private final AsteriaContext asteriaContext = new AsteriaContext();

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> javaFileList = Lists.newArrayList();
		FullyQualifiedJavaType domainType = PluginUtils.getDomainType(introspectedTable);
		FullyQualifiedJavaType entityType = PluginUtils.getEntityType(introspectedTable, asteriaContext);
		FullyQualifiedJavaType mapperType = PluginUtils.getMapperType(introspectedTable);
		FullyQualifiedJavaType serviceType = PluginUtils.getServiceType(introspectedTable, asteriaContext);
		FullyQualifiedJavaType serviceImplType = PluginUtils.getServiceImplType(introspectedTable, asteriaContext);
		FullyQualifiedJavaType beanMapperType = PluginUtils.getBeanMapperType(asteriaContext);


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


		AbstractServiceGenerator serviceGenerator = new DefaultServiceGenerator(asteriaContext, introspectedTable, topLevelClass);
		if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3_DSQL) {
			serviceGenerator = new DynamicServiceGenerator(asteriaContext, introspectedTable, topLevelClass);
		}
		serviceGenerator.generatedSaveMethod();
		serviceGenerator.generatedGetMethod();
		serviceGenerator.generatedGetListMethod();


		javaFileList.add(new GeneratedJavaFile(topLevelClass, asteriaContext.getTargetProject(), context.getJavaFormatter()));
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
