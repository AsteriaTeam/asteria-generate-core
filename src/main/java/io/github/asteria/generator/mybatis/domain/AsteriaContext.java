package io.github.asteria.generator.mybatis.domain;

import io.github.asteria.generator.util.PropertiesUtils;
import lombok.Data;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;

import java.util.Properties;


@Data
public class AsteriaContext {

	private String targetProject;

	private String basePackage;

	private String entityPackage;

	private String entitySuffix;

	private String servicePackage;

	private String servicePrefix;

	private String serviceSuffix;

	private String serviceImplPackage;

	private String serviceImplPrefix;

	private String serviceImplSuffix;

	private String beanMapper;


	public void setContext(Context context){
		targetProject = PropertiesUtils.getPropertyAsString(context.getProperties(), "targetProject", "src/main/java");
		basePackage = PropertiesUtils.getPropertyAsString(context.getProperties(), "basePackage", "io.github.asteria");
		entityPackage = PropertiesUtils.getPropertyAsString(context.getProperties(), "entityPackage", "entity");
		entitySuffix = PropertiesUtils.getPropertyAsString(context.getProperties(), "entitySuffix", "DTO");

		servicePackage = PropertiesUtils.getPropertyAsString(context.getProperties(), "servicePackage", "service");
		servicePrefix = PropertiesUtils.getPropertyAsString(context.getProperties(), "servicePrefix", "");
		serviceSuffix = PropertiesUtils.getPropertyAsString(context.getProperties(), "serviceSuffix", "Service");

		serviceImplPackage = PropertiesUtils.getPropertyAsString(context.getProperties(), "serviceImplPackage", "service.impl");
		serviceImplPrefix = PropertiesUtils.getPropertyAsString(context.getProperties(), "serviceImplPrefix", "");
		serviceImplSuffix = PropertiesUtils.getPropertyAsString(context.getProperties(), "serviceImplSuffix", "ServiceImpl");

		beanMapper = PropertiesUtils.getPropertyAsString(context.getProperties(),"beanMapper","orika");

	}

	public void setProperties(Properties properties){
		targetProject = PropertiesUtils.getPropertyAsString(properties, "targetProject", targetProject);
		basePackage = PropertiesUtils.getPropertyAsString(properties, "basePackage", basePackage);
		entityPackage = PropertiesUtils.getPropertyAsString(properties, "entityPackage", entityPackage);
		entitySuffix = PropertiesUtils.getPropertyAsString(properties, "entitySuffix", entitySuffix);

		servicePackage = PropertiesUtils.getPropertyAsString(properties, "servicePackage", servicePackage);
		servicePrefix = PropertiesUtils.getPropertyAsString(properties, "servicePrefix", servicePrefix);
		serviceSuffix = PropertiesUtils.getPropertyAsString(properties, "serviceSuffix", serviceSuffix);

		serviceImplPackage = PropertiesUtils.getPropertyAsString(properties, "serviceImplPackage", serviceImplPackage);
		serviceImplPrefix = PropertiesUtils.getPropertyAsString(properties, "serviceImplPrefix", serviceImplPrefix);
		serviceImplSuffix = PropertiesUtils.getPropertyAsString(properties, "serviceImplSuffix", serviceImplSuffix);

		beanMapper = PropertiesUtils.getPropertyAsString(properties,"beanMapper",beanMapper);
	}
}
