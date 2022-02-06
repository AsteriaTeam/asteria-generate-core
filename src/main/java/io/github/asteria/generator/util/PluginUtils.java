package io.github.asteria.generator.util;

import io.github.asteria.generator.mybatis.consts.Const;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.List;


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

	public static FullyQualifiedJavaType getDomainType(IntrospectedTable introspectedTable) {
		return new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
	}

	public static String getBasePackage(Context context) {
		return (String) context.getProperties().get(Const.BASE_PACKAGE_PROP);
	}

	public static String getEntityName(IntrospectedTable introspectedTable) {
		return getDomainName(introspectedTable) + introspectedTable.getAttribute(Const.ENTITY_SUFFIX_ATTR);
	}

	public static FullyQualifiedJavaType getEntityType(IntrospectedTable introspectedTable, Context context) {
		String entityPackage = (String) introspectedTable.getAttribute(Const.ENTITY_PACKAGE_ATTR);
		String entitySuffix = (String) introspectedTable.getAttribute(Const.ENTITY_SUFFIX_ATTR);
		return new FullyQualifiedJavaType(StringUtils.join(new String[]{context.getProperties().getProperty(Const.BASE_PACKAGE_PROP), entityPackage, getEntityName(introspectedTable)}, "."));
	}


	public static FullyQualifiedJavaType getMapperType(IntrospectedTable introspectedTable) {
		return new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
	}

	public static String getServiceName(IntrospectedTable introspectedTable) {
		return introspectedTable.getAttribute(Const.SERVICE_PREFIX_ATTR) + getDomainName(introspectedTable) + introspectedTable.getAttribute(Const.SERVICE_SUFFIX_ATTR);
	}

	public static FullyQualifiedJavaType getServiceType(IntrospectedTable introspectedTable, Context context) {
		return new FullyQualifiedJavaType(StringUtils.join(new String[]{getBasePackage(context), (String) introspectedTable.getAttribute(Const.SERVICE_PACKAGE_ATTR), getServiceName(introspectedTable)}, "."));
	}

	public static String getServiceImplName(IntrospectedTable introspectedTable, Context context) {
		return introspectedTable.getAttribute(Const.SERVICE_IMPL_PREFIX_ATTR) + getDomainName(introspectedTable) + introspectedTable.getAttribute(Const.SERVICE_IMPL_SUFFIX_ATTR);
	}

	public static FullyQualifiedJavaType getServiceImplType(IntrospectedTable introspectedTable, Context context) {
		return new FullyQualifiedJavaType(StringUtils.join(new String[]{getBasePackage(context), (String) introspectedTable.getAttribute(Const.SERVICE_IMPL_PACKAGE_ATTR), getServiceImplName(introspectedTable, context)}, "."));
	}

	public static FullyQualifiedJavaType getBeanMapperType(Context context) {
		if (Const.ORIKA_MAPPER.equals(context.getProperty("beanMapperType"))) {
			return new FullyQualifiedJavaType("ma.glasnost.orika.MapperFacade");
		}
		return null;
	}

	public static Method createMethod(String name, FullyQualifiedJavaType javaType, boolean isAbstract, JavaVisibility javaVisibility) {
		return createMethod(name, javaType, null, isAbstract, javaVisibility);
	}

	public static Method createMethod(String name, FullyQualifiedJavaType javaType, FullyQualifiedJavaType returnJavaType, boolean isAbstract, JavaVisibility javaVisibility) {
		Method method = new Method(name);
		method.setVisibility(javaVisibility);
		method.setAbstract(isAbstract);
		method.addParameter(new Parameter(javaType, JavaBeansUtil.getValidPropertyName(javaType.getShortName())));
		if (returnJavaType != null) {
			method.setReturnType(returnJavaType);
		}
		return method;
	}

	public static String getBeanMapperWarp(Context context, FullyQualifiedJavaType javaType, String content) {
		if (Const.ORIKA_MAPPER.equals(context.getProperty("beanMapperType"))) {
			if (javaType.getFullyQualifiedNameWithoutTypeParameters().equals("java.util.List")) {
				List<FullyQualifiedJavaType> typeArguments = javaType.getTypeArguments();
				return "mapperFacade.mapAsList(" + content + "," + typeArguments.get(0).getShortName() + ".class)";
			} else {
				return "mapperFacade.map(" + content + "," + javaType.getShortName() + ".class)";
			}
		}
		return content;
	}
}
