package io.github.asteria.generator.mybatis.plugin.codegen.dynamic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.consts.Const;
import io.github.asteria.generator.mybatis.plugin.codegen.AbstractServiceGenerator;
import io.github.asteria.generator.util.PluginUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.internal.util.JavaBeansUtil;

/**
 * service plugin
 */
public class DynamicServiceGenerator extends AbstractServiceGenerator {


	public DynamicServiceGenerator(Context context, IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
		super(context, introspectedTable, topLevelClass);
	}

	@Override
	public void generatedGetMethod() {
		boolean optionalEnabled = (boolean) introspectedTable.getAttribute("optional.enabled");
		FullyQualifiedJavaType sqlSupportType = new FullyQualifiedJavaType(introspectedTable.getMyBatisDynamicSqlSupportType());


		topLevelClass.addImportedType(sqlSupportType);
		topLevelClass.addStaticImport(Const.SQL_BUILDER_PACKAGE + "." + Const.SQL_BUILDER_IS_EQUAL_TO_WHEN_PRESENT);

		String mapperVar = JavaBeansUtil.getValidPropertyName(mapperType.getShortName());
		String entityVal = JavaBeansUtil.getValidPropertyName(entityType.getShortName());


		Method method = PluginUtils.createMethod("get", entityType, entityType, false, JavaVisibility.PUBLIC);
		method.addAnnotation("@Override");

		StringBuilder builder = new StringBuilder();

		builder.append(mapperVar).append(".selectOne(dsl->dsl.where()\n");
		for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
			builder.append(".and(").append(sqlSupportType.getShortName()).append(".").append(column.getJavaProperty())
				.append(" ,").append(Const.SQL_BUILDER_IS_EQUAL_TO_WHEN_PRESENT).append("(").append(entityVal).append("::").append(JavaBeansUtil.getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType())).append("))\n");
		}
		builder.append(")");
		if (optionalEnabled) {
			builder.append(".map(r->mapperFacade.map(r,").append(entityType.getShortName()).append(".class)).orElse(null);");
			method.addBodyLines(Arrays.stream(("return "+builder.toString()).split("\n")).collect(Collectors.toList()));
		} else {
			List<String> bodyList = Arrays.stream(("return " + PluginUtils.getBeanMapperWarp(context, entityType, builder.toString()) + ";").split("\n")).collect(Collectors.toList());
			method.addBodyLines(bodyList);
		}

		topLevelClass.addMethod(method);
	}

	@Override
	public void generatedGetListMethod() {
		FullyQualifiedJavaType sqlSupportType = new FullyQualifiedJavaType(introspectedTable.getMyBatisDynamicSqlSupportType());
		FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();


		topLevelClass.addImportedType(sqlSupportType);
		topLevelClass.addImportedType(listType);
		topLevelClass.addStaticImport(Const.SQL_BUILDER_PACKAGE + "." + Const.SQL_BUILDER_IS_EQUAL_TO_WHEN_PRESENT);

		String mapperVar = JavaBeansUtil.getValidPropertyName(mapperType.getShortName());
		String entityVal = JavaBeansUtil.getValidPropertyName(entityType.getShortName());

		listType.addTypeArgument(entityType);

		Method method = PluginUtils.createMethod("getList", entityType, listType, false, JavaVisibility.PUBLIC);
		method.addAnnotation("@Override");

		StringBuilder builder = new StringBuilder();

		builder.append(mapperVar).append(".select(dsl->dsl.where()\n");
		for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
			builder.append(".and(").append(sqlSupportType.getShortName()).append(".").append(column.getJavaProperty())
				.append(" ,").append(Const.SQL_BUILDER_IS_EQUAL_TO_WHEN_PRESENT).append("(").append(entityVal).append("::").append(JavaBeansUtil.getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType())).append("))\n");
		}
		builder.append(")");
		List<String> bodyList = Arrays.stream(("return " + PluginUtils.getBeanMapperWarp(context, listType, builder.toString()) + ";").split("\n")).collect(Collectors.toList());
		method.addBodyLines(bodyList);

		topLevelClass.addMethod(method);
	}
}
