package io.github.asteria.generator.mybatis.plugin.codegen;

import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.util.PluginUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DefaultServiceGenerator extends AbstractServiceGenerator {


	public DefaultServiceGenerator(AsteriaContext context, IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
		super(context, introspectedTable, topLevelClass);
	}

	@Override
	public void generatedGetMethod() {
		Method getMethod = PluginUtils.createMethod("get", entityType, entityType, false, JavaVisibility.PUBLIC);

		String mapperVar = JavaBeansUtil.getValidPropertyName(mapperType.getShortName());
		String entityVal = JavaBeansUtil.getValidPropertyName(entityType.getShortName());

		FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(introspectedTable.getExampleType());
		String exampleVal = JavaBeansUtil.getValidPropertyName(exampleType.getShortName());

		FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(introspectedTable.getExampleType() + ".Criteria");
		String criteriaVal = JavaBeansUtil.getValidPropertyName(criteriaType.getShortName());

		topLevelClass.addImportedType(exampleType);

		StringBuilder builder = new StringBuilder();

		builder.append(exampleType.getShortName()).append(" ").append(exampleVal)
			.append(" = ").append("new ").append(exampleType.getShortName()).append("();\n");

		builder.append(exampleType.getShortName()).append(".").append(criteriaType.getShortName()).append(" ").append(criteriaVal).append(" = ")
			.append(exampleVal).append(".createCriteria();\n");
		for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
			builder.append(criteriaVal).append(".").append("and").append(JavaBeansUtil.getFirstCharacterUppercase(column.getJavaProperty())).append("EqualTo(")
				.append(entityVal).append(".").append(JavaBeansUtil.getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType())).append("());\n");
		}
		FullyQualifiedJavaType resultListType = FullyQualifiedJavaType.getNewListInstance();
		topLevelClass.addImportedType(resultListType);
		String resultVal = JavaBeansUtil.getValidPropertyName(resultListType.getShortName());
		resultListType.addTypeArgument(domainType);
		builder.append(resultListType.getShortName()).append(" ").append(resultVal).append(" = ")
			.append(mapperVar).append(".").append(introspectedTable.getSelectByExampleStatementId()).append("(").append(exampleVal).append(");\n");
		builder.append("if(!").append(resultVal).append(".isEmpty()){\n")
			.append("return ").append(PluginUtils.getBeanMapperWarp(context, entityType, resultVal + ".get(0)")).append(";\n")
			.append("}else{\n")
			.append("return null;\n").append("}\n");
		getMethod.addBodyLines(Arrays.stream(builder.toString().split("\n")).collect(Collectors.toList()));
		topLevelClass.addMethod(getMethod);
	}

	@Override
	public void generatedGetListMethod() {
		FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(introspectedTable.getExampleType());
		FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(introspectedTable.getExampleType() + ".Criteria");

		FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
		listType.addTypeArgument(entityType);

		Method getMethod = PluginUtils.createMethod("getList", entityType, listType, false, JavaVisibility.PUBLIC);
		String mapperVar = JavaBeansUtil.getValidPropertyName(mapperType.getShortName());
		String entityVal = JavaBeansUtil.getValidPropertyName(entityType.getShortName());
		String exampleVal = JavaBeansUtil.getValidPropertyName(exampleType.getShortName());
		String criteriaVal = JavaBeansUtil.getValidPropertyName(criteriaType.getShortName());

		topLevelClass.addImportedType(exampleType);
		topLevelClass.addImportedType(listType);

		StringBuilder builder = new StringBuilder();

		builder.append(exampleType.getShortName()).append(" ").append(exampleVal)
			.append(" = ").append("new ").append(exampleType.getShortName()).append("();\n");

		builder.append(exampleType.getShortName()).append(".").append(criteriaType.getShortName()).append(" ").append(criteriaVal).append(" = ")
			.append(exampleVal).append(".createCriteria();\n");
		for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
			builder.append(criteriaVal).append(".").append("and").append(JavaBeansUtil.getFirstCharacterUppercase(column.getJavaProperty())).append("EqualTo(")
				.append(entityVal).append(".").append(JavaBeansUtil.getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType())).append("());\n");
		}
		FullyQualifiedJavaType resultListType = FullyQualifiedJavaType.getNewListInstance();
		topLevelClass.addImportedType(resultListType);
		String resultVal = JavaBeansUtil.getValidPropertyName(resultListType.getShortName());
		resultListType.addTypeArgument(domainType);
		builder.append(resultListType.getShortName()).append(" ").append(resultVal).append(" = ")
			.append(mapperVar).append(".").append(introspectedTable.getSelectByExampleStatementId()).append("(").append(exampleVal).append(");\n");

		builder.append("return ").append(PluginUtils.getBeanMapperWarp(context, listType, resultVal)).append(";");

		getMethod.addBodyLines(Arrays.stream(builder.toString().split("\n")).collect(Collectors.toList()));
		topLevelClass.addMethod(getMethod);
	}
}
