package com.trjst.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration //标记配置类
@EnableSwagger2 //开启在线接口文档
public class SwaggerConfig {
    /**
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：接口文档")
                        // .description("描述：用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...")
                        .contact(new Contact("Alex Chin", null, null))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.trjst.controller.api"))
                .paths(PathSelectors.any())
                .build();

/**
 * 当上线时开启，关闭接口文档
 * */
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("标题：车保姆_接口文档")
//                        // .description("描述：用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...")
//                        .contact(new Contact("Alex Chin", null, null))
//                        .version("版本号:1.0")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.bonne.controller"))
//                .paths(PathSelectors.none())//如果是线上环境，添加路径过滤，设置为全部都不符合
//                .build();


    }
}
