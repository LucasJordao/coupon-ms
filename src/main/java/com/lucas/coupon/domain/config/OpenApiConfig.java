package com.lucas.coupon.domain.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Coupon API",
                version = "v1",
                description = "API para gerenciamento de cupons"
        )
)
public class OpenApiConfig {
}