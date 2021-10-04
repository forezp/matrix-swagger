package io.github.forezp.matrixswaggerstarter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static io.github.forezp.matrixswaggerstarter.SwaggerConstants.*;

@Configuration //表示这是一个配置类
@EnableSwagger2  //开启Swagger使用
public class SwaggerConfig {

    @Value("${" + BASE_PACKAGE + ":" + DEFAULT_BASE_PACKAGE + "}")
    String basePackage;

    @Value("${" + TITLE + ":" + DEFAULT_TITLE + "}")
    String title;

    @Value("${" + DESCRIPTION + ":" + DEFAULT_DESCRIPTION + "}")
    String description;

    @Value("${" + VERSION + ":" + DEFAULT_VERSION + "}")
    String version;


    @Bean
    public Docket createTestApi() {// 创建API基本信息
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.any())
                //扫描所有的包 可以扫描指定的包
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title(title)//标题
                .description(description)// API描述
                .version(version)//接口的版本
                .build();
    }

}
