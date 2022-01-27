package io.github.asteria.generator.mybatis.plugin;

import com.google.common.collect.Lists;
import io.github.asteria.generator.util.PluginUtils;
import io.github.asteria.generator.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.List;
import java.util.Properties;

public class AsteriaEntityPlugin extends PluginAdapter {

	private String targetProject;

	private String basePackage;

	private String entityPackage;

	private boolean lombok = false;

	private boolean lombokBuilder = false;

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> javaFileList = Lists.newArrayList();
		String domainName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
		String packageVal = StringUtils.join(new String[]{basePackage, entityPackage}, ".");
		List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
		TopLevelClass entityClass = new TopLevelClass(packageVal + "." + domainName);
		if (lombok) {
			PluginUtils.addLombokAn(entityClass, this.lombokBuilder);
		}
		javaFileList.add(new GeneratedJavaFile(entityClass, targetProject, context.getJavaFormatter()));
		return javaFileList;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
		Boolean lombokFlag = PropertiesUtils.getPropertyAsBoolean(context.getProperties(), "lombok", false);
		if (lombokFlag) {
			this.lombok = true;
			Boolean builderFlag = PropertiesUtils.getPropertyAsBoolean(context.getProperties(), "lombokBuilder", false);
			if (builderFlag) {
				this.lombokBuilder = true;
			}
		}
		basePackage = PropertiesUtils.getPropertyAsString(context.getProperties(), "basePackage", basePackage);
		entityPackage = PropertiesUtils.getPropertyAsString(context.getProperties(), "basePackage", entityPackage);
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		Boolean lombokFlag = PropertiesUtils.getPropertyAsBoolean(properties, "lombok", false);
		if (lombokFlag) {
			this.lombok = true;
			Boolean builderFlag = PropertiesUtils.getPropertyAsBoolean(properties, "lombokBuilder", false);
			if (builderFlag) {
				this.lombokBuilder = true;
			}
		}
		basePackage = PropertiesUtils.getPropertyAsString(properties, "basePackage", basePackage);
		entityPackage = PropertiesUtils.getPropertyAsString(properties, "basePackage", entityPackage);
	}
}
