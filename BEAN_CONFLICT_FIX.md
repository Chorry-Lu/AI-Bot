# Bean冲突问题修复说明

## 问题描述

项目在启动时遇到了Bean冲突错误：

```
BeanDefinitionOverrideException: Invalid bean definition with name 'chatClientBuilderConfigurer' 
defined in class path resource [org/springframework/ai/model/chat/client/autoconfigure/ChatClientAutoConfiguration.class]: 
Cannot register bean definition ... for bean 'chatClientBuilderConfigurer' since there is already 
[Root bean: class=null; ... factoryBeanName=org.springframework.ai.autoconfigure.chat.client.ChatClientAutoConfiguration; 
factoryMethodName=chatClientBuilderConfigurer
```

## 问题原因

1. **版本不匹配**：
   - Spring AI OpenAI starter 使用 `1.0.0-M6` 版本
   - Alibaba DashScope starter 依赖的 Spring AI 核心库需要 `1.0.0` 版本
   - 两个版本使用了不同的自动配置类路径：
     - `org.springframework.ai.autoconfigure.chat.client.ChatClientAutoConfiguration` (1.0.0-M6)
     - `org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration` (1.0.0)

2. **Bean名称冲突**：
   - 两个自动配置类都尝试创建名为 `chatClientBuilderConfigurer` 的Bean
   - Spring默认不允许Bean定义覆盖

## 解决方案

### 1. 统一依赖版本管理

在 `pom.xml` 的 `dependencyManagement` 中强制指定Alibaba依赖使用的Spring AI核心库版本：

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-bom</artifactId>
            <version>${spring-ai.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!-- 强制Alibaba依赖使用的Spring AI核心库版本为1.0.0，避免版本冲突 -->
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-commons</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-model</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-rag</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-autoconfigure-model-chat-client</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### 2. 排除冲突的自动配置类

在主应用类中排除冲突的自动配置：

```java
@SpringBootApplication(exclude = {
    org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration.class
})
public class AiBotApplication {
    // ...
}
```

### 3. 允许Bean定义覆盖

在 `application.yml` 中配置允许Bean定义覆盖：

```yaml
spring:
  main:
    allow-bean-definition-overriding: true
```

### 4. 使用条件注解

在配置类中使用 `@ConditionalOnBean` 确保只在相关Bean存在时创建ChatClient：

```java
@Bean("dashscopeChatClient")
@ConditionalOnBean(name = "dashScopeChatModel")
public ChatClient dashScopeChatClient(@Qualifier("dashScopeChatModel") ChatModel dashScopeChatModel) {
    return ChatClient.builder(dashScopeChatModel)
            .defaultSystem("你是一个有用的AI助手。")
            .build();
}
```

## 验证

修复后，项目应该能够：
1. ✅ 正常编译：`mvn clean compile`
2. ✅ 正常启动：`mvn spring-boot:run`
3. ✅ 无Bean冲突错误
4. ✅ 两个模型（OpenAI和DashScope）都能正常工作

## 注意事项

1. **版本兼容性**：当前解决方案允许不同版本的Spring AI库共存，但建议在Spring AI正式版发布后统一版本
2. **Bean覆盖**：`allow-bean-definition-overriding: true` 允许Bean覆盖，需要确保覆盖的Bean是正确的
3. **自动配置**：排除自动配置类后，需要手动创建必要的Bean（如ChatClient）

## 后续优化建议

1. 等待Spring AI 1.0.0正式版发布后，统一所有依赖版本
2. 考虑使用Spring AI的统一BOM管理所有依赖
3. 监控Spring AI和Alibaba依赖的版本更新，及时调整
