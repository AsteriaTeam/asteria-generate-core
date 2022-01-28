package io.github.asteria.generator.mybatis.plugin;

import com.google.common.collect.Lists;
import io.github.asteria.generator.mybatis.domain.AsteriaContext;
import io.github.asteria.generator.util.PluginUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.mybatis.generator.config.Context;

import java.util.List;
import java.util.Properties;

/**
 * service plugin
 */
public class AsteriaServicePlugin extends PluginAdapter {

	private final AsteriaContext asteriaContext = new AsteriaContext();

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> javaFileList = Lists.newArrayList();
		String domainName = PluginUtils.getDomainName(introspectedTable);
		String packageVal = StringUtils.join(new String[]{asteriaContext.getBasePackage(), asteriaContext.getServicePackage()}, ".");
		String serviceName = asteriaContext.getServicePrefix() + domainName + asteriaContext.getServiceSuffix();

		String entityFullVal = StringUtils.join(new String[]{asteriaContext.getBasePackage(), asteriaContext.getEntityPackage(),domainName+asteriaContext.getEntitySuffix()}, ".");

		// interface
		Interface interfaceClass = new Interface(StringUtils.join(new String[]{packageVal, serviceName}, "."));
		interfaceClass.setVisibility(JavaVisibility.PUBLIC);
		Method saveMethod = new Method("save");
		saveMethod.setAbstract(true);

		Parameter entityParameter = new Parameter(new FullyQualifiedJavaType(entityFullVal),"entity");
		saveMethod.addParameter(entityParameter);
		interfaceClass.addMethod(saveMethod);
		interfaceClass.addImportedType(new FullyQualifiedJavaType(entityFullVal));

		javaFileList.add(new GeneratedJavaFile(interfaceClass, asteriaContext.getTargetProject(), context.getJavaFormatter()));
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
