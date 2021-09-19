package com.glaze.wstest.ws

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class CorsConfigurer: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/ws/**")
            .allowedOrigins("http://localhost:19006/")
            .allowCredentials(true)
    }
}