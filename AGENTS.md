# AI 助手指令: My-AI-assistant

## 项目概述
RAG（检索增强生成）知识库系统的后端。使用 Java 21 + Spring Boot 3.2.0，集成 LangChain4j 与阿里巴巴通义千问 LLM 和 Redis 向量数据库。

## 架构
- **入口点**: `com.greendam.ai.RunApp`
- **基础包**: `com.greendam.ai`
- **上下文路径**: `/ai` (服务器端口: 33453)
- **配置文件**: 默认激活 `dev` profile

## 构建与运行

```bash
# 编译
mvn clean compile

# 运行应用
mvn spring-boot:run

# 运行测试
mvn test

# 打包
mvn clean package

#清理
mvn clean
```

## 配置密钥（重要）

**切勿提交凭据。** 文件 `application-dev.yml` 已被 gitignore，包含以下内容：
- `langchain4j.api-key`: 通义千问 API 密钥
- `redis.host/port/password`: Redis 连接（端口 7001 = 集群节点）
- `mysql.*`: 数据库凭据

如果此文件缺失，请从环境变量创建或询问用户获取值。

## 关键依赖
- Spring Boot 3.2.0 (Web, WebFlux, Data Redis)
- LangChain4j 1.0.0-beta3 (OpenAI 适配器用于通义千问)
- MyBatis-Plus 3.5.6
- Redis 向量数据库通过 `langchain4j-community-redis-spring-boot-starter`

## 测试说明
- 单个测试类: `RunAppTests.java`
- 使用 Spring Boot 测试启动器
- 需要 Redis 连接（测试使用 `StringRedisTemplate`）

## 国际化
- 消息文件位于 `src/main/resources/i18n/`
- 默认编码: UTF-8
- 支持: 中文 (`message_zh_CN.properties`) 和英文 (`message_en_US.properties`)

## 开发规范
- 使用 Lombok `@Slf4j` 进行日志记录
- 结果包装器: `com.greendam.ai.common.Result`
- 业务异常: `BusinessException`
- `pom.xml` 中强制 UTF-8 编码

## 语言要求
**所有编辑此项目的 AI 必须使用中文进行思考（thinking）或与用户交流。**
# 代码审查任务：执行修改后自动校验
请你严格按照以下规则，对刚刚修改/生成的代码进行**全自动代码审查**，确保代码 100% 可运行、无语法错误、不爆红。

## 审查规则（严格执行）
1. 语法检查
    - 无任何语法错误、拼写错误、符号错误
    - 无缺失分号、未闭合括号、未定义类/方法
    - 无导入错误、无爆红、无IDE报错
    - 无类型错误、无明显空指针问题

2. 自动编译检查
   要求：
    - 必须显示BUILD SUCCESS
    - 若编译失败，自动定位错误文件、行号、原因
    - 自动修复错误并重新编译

3. 运行检查
    - 确保启动类可正常加载
    - 无类找不到、无配置错误、无启动报错
    - 确保修改不破坏原有项目结构
    - 当检测到输出**启动成功**或者 **Started xxx in xxx seconds (process running for xxx)** 类似的日志时，视为运行成功

## 输出格式
1. 审查结论：通过 / 不通过
2. 若有错误：自动修复错误并重新审查，直到程序可以正常运行
3. 若无错误：✅ 代码审查通过，无语法错误，编译成功，可正常运行

