# asteria-generate-core

## plugins

| name                     | description        |
|--------------------------|--------------------|
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
