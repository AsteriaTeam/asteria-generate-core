package io.github.asteria.generator.mybatis.plugin;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.plugin.comment.AsteriaCommentGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.DomainObjectRenamingRule;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.asteria.generator.util.PropertiesUtils.getPropertyAsBoolean;
import static io.github.asteria.generator.util.PropertiesUtils.getPropertyAsString;

public class AsteriaMyBatisPlugin extends PluginAdapter {

	private boolean lombok = false;

	private boolean lombokBuilder = false;

	private boolean enableSwagger = false;

	private boolean enableValidation = false;

	private boolean comment = false;

	private boolean createService = false;

	private boolean createDto = false;

	private boolean createConvert = false;

	private String targetProject;

	private String baseJavaPackage;

	private String servicePackage;

	private String serviceImplPackage;

	private String sqlProviderRootClass;

	private String dtoPackage;

	private String convertPackage;


	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Optional"));
		return super.clientGenerated(interfaze, introspectedTable);
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return !lombok;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return !lombok;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		getDomainName(introspectedTable);
		addAnnotation(topLevelClass, introspectedTable, ClassType.MODEL);
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		addFieldAnnotation(field, introspectedColumn, topLevelClass, ClassType.MODEL);
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> javaFiles = super.contextGenerateAdditionalJavaFiles(introspectedTable);
		String domainObjectName = getDomainName(introspectedTable);
		if (createService) {
			if (CollectionUtils.isEmpty(javaFiles)) {
				javaFiles = Lists.newArrayList();
			}

			Interface serviceClass = new Interface(baseJavaPackage + ".service." + domainObjectName + "Service");
			serviceClass.setVisibility(JavaVisibility.PUBLIC);
			GeneratedJavaFile serviceFile = new GeneratedJavaFile(serviceClass, targetProject, context.getJavaFormatter());
			javaFiles.add(serviceFile);

			TopLevelClass serviceImplClass = new TopLevelClass(baseJavaPackage + ".service.impl." + domainObjectName + "ServiceImpl");
			serviceImplClass.setVisibility(JavaVisibility.PUBLIC);
			serviceImplClass.addImportedType(serviceClass.getType());
			serviceImplClass.addSuperInterface(serviceClass.getType());
			GeneratedJavaFile serviceImplFile = new GeneratedJavaFile(serviceImplClass, targetProject, context.getJavaFormatter());
			javaFiles.add(serviceImplFile);
		}
		javaFiles.addAll(generatedDto(introspectedTable));
		return javaFiles;
	}

	private List<GeneratedJavaFile> generatedDto(IntrospectedTable introspectedTable) {
		if (!createDto) {
			return Collections.emptyList();
		}
		ClassType type = ClassType.DTO;
		String domainObjectName = getDomainName(introspectedTable);
		TopLevelClass dtoReqClass = new TopLevelClass(baseJavaPackage + "." + dtoPackage + "." + domainObjectName + "ReqDTO");
		addAnnotation(dtoReqClass, introspectedTable, type);
		dtoReqClass.addImportedType("java.io.Serializable");
		dtoReqClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
		dtoReqClass.setVisibility(JavaVisibility.PUBLIC);
		List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
		if (CollectionUtils.isNotEmpty(allColumns)) {
			allColumns.stream().forEach(r -> {
				dtoReqClass.addImportedType(r.getFullyQualifiedJavaType());
				Field field = new Field(JavaBeansUtil.getJavaBeansField(r, this.context, introspectedTable));
				addFieldAnnotation(field, r, dtoReqClass, type);
				dtoReqClass.addField(field);
			});
		}
		GeneratedJavaFile dtoReqFile = new GeneratedJavaFile(dtoReqClass, targetProject, context.getJavaFormatter());

		TopLevelClass dtoResultClass = new TopLevelClass(baseJavaPackage + "." + dtoPackage + "." + domainObjectName + "ResultDTO");
		if (enableSwagger) {
			dtoResultClass.addImportedType("io.swagger.annotations.ApiModel");
			dtoResultClass.addImportedType("io.swagger.annotations.ApiModelProperty");
		}
		addAnnotation(dtoResultClass, introspectedTable, type);
		dtoResultClass.addImportedType("java.io.Serializable");
		dtoResultClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
		dtoResultClass.setVisibility(JavaVisibility.PUBLIC);
		if (CollectionUtils.isNotEmpty(allColumns)) {
			allColumns.stream().forEach(r -> {
				dtoResultClass.addImportedType(r.getFullyQualifiedJavaType());
				Field field = new Field(JavaBeansUtil.getJavaBeansField(r, this.context, introspectedTable));
				addFieldAnnotation(field, r, dtoResultClass, type);
				dtoResultClass.addField(field);
			});
		}
		GeneratedJavaFile dtoResultFile = new GeneratedJavaFile(dtoResultClass, targetProject, context.getJavaFormatter());
		ArrayList<GeneratedJavaFile> generatedJavaFiles = Lists.newArrayList(dtoReqFile, dtoResultFile);
		//generatedJavaFiles.addAll(generatedConvert(introspectedTable));
		return generatedJavaFiles;
	}

	@Override
	public void setContext(Context context) {
		super.setContext(context);
//		comment = Boolean.parseBoolean(context.getProperty("useMapperCommentGenerator"));
//		if (comment) {
//			CommentGeneratorConfiguration commentCfg = new CommentGeneratorConfiguration();
//			commentCfg.setConfigurationType(AsteriaCommentGenerator.class.getCanonicalName());
//			context.setCommentGeneratorConfiguration(commentCfg);
//		}
		targetProject = getPropertyAsString(super.context.getProperties(), "targetProject");
		baseJavaPackage = getPropertyAsString(super.context.getProperties(), "baseJavaPackage");
		servicePackage = getPropertyAsString(super.context.getProperties(), "servicePackage", "service");
		serviceImplPackage = getPropertyAsString(super.context.getProperties(), "serviceImplPackage", "service.impl");
		dtoPackage = getPropertyAsString(super.context.getProperties(), "dtoPackage", "dto");
//		convertPackage = getPropertyAsString(super.context.getProperties(), "convertPackage", "convert");
//		sqlProviderRootClass = getPropertyAsString(super.context.getProperties(), "sqlProviderRootClass", null);
		//支持oracle获取注释#114
		context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
		//支持mysql获取注释
		context.getJdbcConnectionConfiguration().addProperty("useInformationSchema", "true");

		context.getCommentGeneratorConfiguration().addProperty("dateFormat", "yyyy-MM-dd");
	}

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		lombok = getPropertyAsBoolean(properties, "lombok", false);
		lombokBuilder = getPropertyAsBoolean(properties, "lombokBuilder", false);
		enableSwagger = getPropertyAsBoolean(properties, "enableSwagger", false);
		enableValidation = getPropertyAsBoolean(properties, "enableValidation", false);
		createService = getPropertyAsBoolean(properties, "createService", false);
		createDto = getPropertyAsBoolean(properties, "createDto", false);
		createConvert = getPropertyAsBoolean(properties, "createConvert", false);
		super.context.getCommentGeneratorConfiguration().addProperty("lombok", getPropertyAsString(properties, "lombok", "false"));
		super.context.getCommentGeneratorConfiguration().addProperty("enableSwagger", getPropertyAsString(properties, "enableSwagger", "false"));
	}

	/**
	 * 获取标准的模型名称
	 *
	 * @param introspectedTable
	 * @return
	 */
	private String getDomainName(IntrospectedTable introspectedTable) {
		return introspectedTable.getFullyQualifiedTable().getDomainObjectName();
	}

	private void addAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, ClassType type) {
		if (lombok) {
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
			}
		}
		if (lombokBuilder) {
			topLevelClass.addImportedType("lombok.Builder");
			topLevelClass.addAnnotation("@Builder");
		}
		boolean flag = (ClassType.MODEL.equals(type) && !createDto) || (ClassType.DTO.equals(type));
		if (flag && enableSwagger) {
			//导包
			topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
			//增加注解(去除注释中的转换符)
			String remarks = introspectedTable.getRemarks();
			if (remarks == null) {
				remarks = "";
			}
			remarks = remarks.replaceAll("\r", "").replaceAll("\n", "");
			if (StringUtils.isNoneBlank(remarks)) {
				topLevelClass.addAnnotation("@ApiModel(\"" + remarks + "\")");
			} else {
				topLevelClass.addAnnotation("@ApiModel(\"" + topLevelClass.getType().getShortName() + "\")");
			}
		}

	}

	private void addFieldAnnotation(Field field, IntrospectedColumn introspectedColumn, TopLevelClass topLevelClass, ClassType type) {
		boolean flag = (ClassType.MODEL.equals(type) && !createDto) || (ClassType.DTO.equals(type));
		if (flag && enableSwagger) {
			topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
			String remarks = introspectedColumn.getRemarks();
			remarks = remarks.replaceAll("\r", "").replaceAll("\n", "");
			if (StringUtils.isNoneBlank(remarks)) {
				StringBuffer buffer = new StringBuffer("@ApiModelProperty(");
				buffer.append("value=\"" + remarks + "\"");
				buffer.append(")");
				field.addAnnotation(buffer.toString());
			}
		}
		if (flag && enableValidation) {
			topLevelClass.addImportedType(new FullyQualifiedJavaType("org.hibernate.validator.constraints.Length"));
			int length = introspectedColumn.getLength();
			String fullyQualifiedName = introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName();
			String stringClassName = "java.lang.String";
			if (stringClassName.equals(fullyQualifiedName)) {
				field.addAnnotation("@Length(max = " + length + ")");
			}
		}
	}

	public enum ClassType {
		/**
		 * model
		 */
		MODEL,
		/**
		 * dto
		 */
		DTO;
	}
}
