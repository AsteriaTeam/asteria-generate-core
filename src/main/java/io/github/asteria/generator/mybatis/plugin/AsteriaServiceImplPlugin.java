package io.github.asteria.generator.mybatis.plugin;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.util.PluginUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;

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
		String domainName = PluginUtils.getDomainName(introspectedTable);
		FullyQualifiedJavaType domainType = PluginUtils.getDomainType(introspectedTable);
		FullyQualifiedJavaType entityType = PluginUtils.getEntityType(introspectedTable, asteriaContext);
		FullyQualifiedJavaType mapperType = PluginUtils.getMapperType(introspectedTable);
		FullyQualifiedJavaType serviceType = PluginUtils.getServiceType(introspectedTable, asteriaContext);
		FullyQualifiedJavaType serviceImplType = PluginUtils.getServiceImplType(introspectedTable, asteriaContext);

		// impl
		TopLevelClass topLevelClass = new TopLevelClass(serviceImplType);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.addSuperInterface(serviceType);

		topLevelClass.addImportedType(domainType);
		topLevelClass.addImportedType(entityType);
		topLevelClass.addImportedType(mapperType);
		topLevelClass.addImportedType(serviceType);


		Field mapperField = new Field(mapperType.getShortName(), mapperType);
		mapperField.setVisibility(JavaVisibility.PRIVATE);

		topLevelClass.addField(mapperField);

		Method savaMethod = new Method("save");
		savaMethod.addParameter(new Parameter(entityType, "entity"));
		savaMethod.setVisibility(JavaVisibility.PUBLIC);
		savaMethod.addAnnotation("@Override");
		topLevelClass.addMethod(savaMethod);




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
