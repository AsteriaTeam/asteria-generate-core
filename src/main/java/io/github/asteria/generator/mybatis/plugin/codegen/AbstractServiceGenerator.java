package io.github.asteria.generator.mybatis.plugin.codegen;

import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.util.PluginUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.List;

public abstract class AbstractServiceGenerator {

	protected final AsteriaContext context;

	protected final IntrospectedTable introspectedTable;

	protected final TopLevelClass topLevelClass;

	protected final FullyQualifiedJavaType mapperType;
	protected final FullyQualifiedJavaType domainType;
	protected final FullyQualifiedJavaType entityType;

	public AbstractServiceGenerator(AsteriaContext context,IntrospectedTable introspectedTable,TopLevelClass topLevelClass) {
		this.context = context;
		this.introspectedTable = introspectedTable;
		this.topLevelClass = topLevelClass;
		this.mapperType = PluginUtils.getMapperType(introspectedTable);
		this.domainType = PluginUtils.getDomainType(introspectedTable);
		this.entityType = PluginUtils.getEntityType(introspectedTable,context);

	}

	public void generatedSaveMethod(){
		String entityVal = JavaBeansUtil.getValidPropertyName(entityType.getShortName());
		String mapperVar = JavaBeansUtil.getValidPropertyName(mapperType.getShortName());

		List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
		// 主键策略
		if (CollectionUtils.isNotEmpty(primaryKeyColumns)) {
			Method savaMethod = new Method("save");
			savaMethod.addParameter(new Parameter(entityType, entityVal));
			savaMethod.setVisibility(JavaVisibility.PUBLIC);
			savaMethod.addAnnotation("@Override");

			savaMethod.addBodyLine(domainType.getShortName() + " domain = mapperFacade.map(" + entityVal + "," + domainType.getShortName() + ".class);");
			String getMethodName = JavaBeansUtil.getGetterMethodName(primaryKeyColumns.get(0).getJavaProperty(), primaryKeyColumns.get(0).getFullyQualifiedJavaType());
			savaMethod.addBodyLine("if(domain." + getMethodName + "()!=null){");
			savaMethod.addBodyLine(mapperVar + "." + introspectedTable.getUpdateByPrimaryKeySelectiveStatementId() + "(domain);");
			savaMethod.addBodyLine("}else{");
			savaMethod.addBodyLine(mapperVar + "." + introspectedTable.getInsertSelectiveStatementId() + "(domain);");
			savaMethod.addBodyLine("}");
			topLevelClass.addMethod(savaMethod);
		}
	}

	public abstract void generatedGetMethod();

	public abstract void generatedGetListMethod();

}
