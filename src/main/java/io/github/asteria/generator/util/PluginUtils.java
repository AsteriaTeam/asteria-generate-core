package io.github.asteria.generator.util;

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
}
