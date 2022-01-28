package io.github.asteria.generator.util;

import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class PluginUtils {

	private PluginUtils() {

	}

	public static void addLombokAn(TopLevelClass topLevelClass, boolean lombokBuilder) {
		topLevelClass.addImportedType("lombok.Data");
		topLevelClass.addAnnotation("@Data");
		if (topLevelClass.getSuperClass().isPresent()) {
			topLevelClass.addImportedType("lombok.EqualsAndHashCode");
			topLevelClass.addImportedType("lombok.ToString");
			topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");
			topLevelClass.addAnnotation("@ToString(callSuper = true)");
		}
		if (lombokBuilder) {
			topLevelClass.addImportedType("lombok.NoArgsConstructor");
			topLevelClass.addAnnotation("@NoArgsConstructor");
			topLevelClass.addImportedType("lombok.AllArgsConstructor");
			topLevelClass.addAnnotation("@AllArgsConstructor");
			topLevelClass.addImportedType("lombok.Builder");
			topLevelClass.addAnnotation("@Builder");
		}
	}

	public static String getDomainName(IntrospectedTable introspectedTable) {
		return introspectedTable.getFullyQualifiedTable().getDomainObjectName();
	}

	public static FullyQualifiedJavaType getDomainType(IntrospectedTable introspectedTable){
		return new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
	}

	public static String getEntityName(IntrospectedTable introspectedTable,AsteriaContext context){
		return getDomainName(introspectedTable)+ context.getEntitySuffix();
	}

	public static FullyQualifiedJavaType getEntityType(IntrospectedTable introspectedTable,AsteriaContext context){
		return new FullyQualifiedJavaType(
			StringUtils.join(new String[]{context.getBasePackage(), context.getEntityPackage(),getEntityName(introspectedTable,context)}, "."));
	}


	public static FullyQualifiedJavaType getMapperType(IntrospectedTable introspectedTable){
		return new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
	}

	public static String getServiceName(IntrospectedTable introspectedTable,AsteriaContext context){
		return context.getServicePrefix() + getDomainName(introspectedTable)+ context.getServiceSuffix();
	}

	public static FullyQualifiedJavaType getServiceType(IntrospectedTable introspectedTable,AsteriaContext context){
		return new FullyQualifiedJavaType(
			StringUtils.join(new String[]{context.getBasePackage(), context.getServicePackage(),getServiceName(introspectedTable,context)}, "."));
	}

	public static String getServiceImplName(IntrospectedTable introspectedTable,AsteriaContext context){
		return context.getServiceImplPrefix() + getDomainName(introspectedTable)+ context.getServiceImplSuffix();
	}

	public static FullyQualifiedJavaType getServiceImplType(IntrospectedTable introspectedTable,AsteriaContext context){
		return new FullyQualifiedJavaType(
			StringUtils.join(new String[]{context.getBasePackage(), context.getServiceImplPackage(),getServiceImplName(introspectedTable,context)}, "."));
	}
}
