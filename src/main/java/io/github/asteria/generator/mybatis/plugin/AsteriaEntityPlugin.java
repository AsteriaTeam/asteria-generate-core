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
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;

public class AsteriaEntityPlugin extends AsteriaAbstractPlugin {

	private String entityPackage;

	private String entitySuffix;

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		introspectedTable.setAttribute(Const.ENTITY_PACKAGE_ATTR, entityPackage);
		introspectedTable.setAttribute(Const.ENTITY_SUFFIX_ATTR, entitySuffix);
		List<GeneratedJavaFile> javaFileList = Lists.newArrayList();
		String domainName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
		String packageVal = StringUtils.join(new String[]{PropertiesUtils.getPropertyAsString(this.context.getProperties(), Const.BASE_PACKAGE_PROP), entityPackage}, ".");
		List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
		TopLevelClass entityClass = new TopLevelClass(packageVal + "." + domainName + entitySuffix);
		entityClass.setVisibility(JavaVisibility.PUBLIC);
		boolean lombok = (boolean) introspectedTable.getAttribute(Const.LOMBOK_ENABLED_ATTR);
		if (lombok) {
			PluginUtils.addLombokAn(entityClass, (boolean) introspectedTable.getAttribute(Const.LOMBOK_BUILDER_ENABLED_ATTR));
		}
		if (CollectionUtils.isNotEmpty(columnList)) {
			List<Method> methodList = Lists.newArrayList();
			columnList.forEach(r -> {
				entityClass.addImportedType(r.getFullyQualifiedJavaType());
				Field field = new Field(JavaBeansUtil.getJavaBeansField(r, this.context, introspectedTable));
				entityClass.addField(field);
				if (!lombok) {
					// get set method
					Method getMethod = JavaBeansUtil.getJavaBeansGetter(r, context, introspectedTable);
					Method setMethod = JavaBeansUtil.getJavaBeansGetter(r, context, introspectedTable);
					methodList.add(getMethod);
					methodList.add(setMethod);
				}
			});
			methodList.forEach(entityClass::addMethod);
		}
		javaFileList.add(new GeneratedJavaFile(entityClass, targetProject, javaFileEncoding, context.getJavaFormatter()));
		return javaFileList;
	}
	

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		entityPackage = properties.getProperty("entityPackage", "entity");
		entitySuffix = properties.getProperty("entitySuffix", "DTO");
	}
}
