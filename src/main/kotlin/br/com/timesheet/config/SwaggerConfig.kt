package br.com.timesheet.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Classe de configuração do Swagger
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    /**
     * Habilitando Swagger no projeto
     */
    @Bean
    fun api(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .groupName("timesheet-api")
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.timesheet.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())

    private fun apiInfo(): ApiInfo =
        ApiInfoBuilder()
            .title("Timesheet")
            .description("Sistema de registro de banco de horas")
            .build()
}