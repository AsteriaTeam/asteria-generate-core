package io.github.asteria.generator.mybatis.plugin;

import io.github.asteria.generator.util.PropertiesUtils;
import lombok.Getter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.Context;

import java.util.Properties;

public abstract class AsteriaAbstractPlugin extends PluginAdapter {

	protected String targetProject;
	protected String basePackage;
	protected String beanMapperType;
	protected String javaFileEncoding;

	@Override
	public void setContext(Context context) {
		super.setContext(context);
		this.targetProject = context.getProperty("targetProject");
		this.basePackage = context.getProperty("basePackage");
		this.beanMapperType = context.getProperty("beanMapperType");
		this.javaFileEncoding = context.getProperty("javaFileEncoding");
	}

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
	}
}
