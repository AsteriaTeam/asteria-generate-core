# asteria-generate-core

## plugins

| name                     | description        |
|--------------------------|--------------------|
| AsteriaOptionalPlugin    | java 8 Optional    |
| AsterIaLombokPlugin      | lombok(client)     |
| AsteriaEntityPlugin      | entity(DTO lombok) |
| AsteriaServicePlugin     | serviceInterface   |
| AsteriaServiceImplPlugin | serviceImpl        |


## context(properties)

| prop               | description                                         |
|--------------------|-----------------------------------------------------|
| lombok             | enabled lombok , default value(`false`)             |
| lombokBuilder      | enabled lombok builder , default value(`false`)     |
| targetProject      | default value(`src/main/java`)                      |
| basePackage        | base package , default value(`io.github.asteria`)   |
| entityPackage      | entity package , default value(`entity`)            |
| entitySuffix       | entity suffix , default value(`DTO`)                |
| servicePackage     | service package , default value(`service`)          |
| servicePrefix      | service prefix                                      |
| serviceSuffix      | service suffix , default value(`Service`)           |
| serviceImplPackage | serviceImpl package , default value(`service.impl`) |
| serviceImplPrefix  | serviceImpl prefix                                  |
| serviceImplSuffix  | serviceImpl suffix , default value(`Impl`)          |

## use

### MyBatis3DynamicSql

```xml
<!DOCTYPE generatorConfiguration PUBLIC
    "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
    "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <context id="sql" targetRuntime="MyBatis3DynamicSql">

        <property name="javaFileEncoding" value="UTF-8"/>
        <property name="targetProject" value="src/test/java"/>
        <property name="basePackage" value="io.github.asteria"/>
        <property name="entityPackage" value="entity"/>
        <property name="lombok" value="true"/>
        <property name="lombokBuilder" value="true"/>

        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaOptionalPlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaLombokPlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaEntityPlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaServicePlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaServiceImplPlugin"/>


        <commentGenerator type="io.github.asteria.generator.mybatis.plugin.comment.AsteriaCommentGenerator">
            <property name="suppressDate" value="true"/>
            <property name="addRemarkComments" value="true"/>
        </commentGenerator>

        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://{you db url}/ops_admin?user={user}&amp;password={password}&amp;useUnicode=true" />


        <javaModelGenerator targetPackage="io.github.asteria.domain" targetProject="src/test/java"/>

        <javaClientGenerator targetPackage="io.github.asteria.mapper" targetProject="src/test/java" />


        <table tableName="t_dict_type">
            <generatedKey column="id" sqlStatement="JDBC"/>
            <domainObjectRenamingRule searchString="^T" replaceString=""/>
            <columnOverride column="is_valid" javaType="java.lang.Integer"/>
            <columnOverride column="status" javaType="java.lang.Integer"/>
        </table>
    </context>

</generatorConfiguration>

```

### MyBatis3

```xml
<!DOCTYPE generatorConfiguration PUBLIC
    "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
    "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
   <context id="MyBatis3" targetRuntime="MyBatis3">

        <property name="javaFileEncoding" value="UTF-8"/>
        <property name="targetProject" value="src/test/java"/>
        <property name="basePackage" value="io.github.asteria"/>
        <property name="entityPackage" value="entity"/>
        <property name="lombok" value="true"/>
        <property name="lombokBuilder" value="true"/>

        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaLombokPlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaOptionalPlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaEntityPlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaServicePlugin"/>
        <plugin type="io.github.asteria.generator.mybatis.plugin.AsteriaServiceImplPlugin"/>

        <commentGenerator type="io.github.asteria.generator.mybatis.plugin.comment.AsteriaCommentGenerator">
            <property name="suppressDate" value="true"/>
            <property name="addRemarkComments" value="true"/>
        </commentGenerator>

        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://{you db url}/ops_admin?user={user}&amp;password={password}&amp;useUnicode=true" />

        <javaModelGenerator targetPackage="io.github.asteria.domain" targetProject="src/test/java"/>

        <sqlMapGenerator targetPackage="mapper" targetProject="src/test/resources"/>

        <javaClientGenerator targetPackage="io.github.asteria.mapper" targetProject="src/test/java" type="MAPPER"/>

        <table tableName="t_dict_type">
            <generatedKey column="id" sqlStatement="JDBC"/>
            <domainObjectRenamingRule searchString="^T" replaceString=""/>
            <columnOverride column="is_valid" javaType="java.lang.Integer"/>
            <columnOverride column="status" javaType="java.lang.Integer"/>
        </table>
    </context>
</generatorConfiguration>

```
