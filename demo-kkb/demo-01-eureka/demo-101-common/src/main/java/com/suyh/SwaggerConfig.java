package com.suyh;

import org.springframework.context.annotation.Bean;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-03 17:01
 */
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 通过分组可以在生成的文档是下拉选择，查看哪一个分组
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("所有文档")
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class);
    }

    private ApiInfo apiInfo() {
        // 前端访问地址：http://localhost:8080/swagger-ui.html
        return new ApiInfoBuilder()
                .title("TITLE 显示标题")
                .description("description 描述说明")
                .version("v1.0")
                .build();
    }

}
