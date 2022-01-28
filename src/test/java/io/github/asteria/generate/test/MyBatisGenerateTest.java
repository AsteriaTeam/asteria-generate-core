package io.github.asteria.generate.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class MyBatisGenerateTest {

	@Test
	public void test() throws Exception {
		List<String> warnings = new ArrayList<String>();
		ClassLoader classLoader = MyBatisGenerateTest.class.getClassLoader();
		URL url = classLoader.getResource("generatorConfig.xml");
		File configFile = new File(url.getFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);

	}
}
