package com.cubegalaxy.cubestage.mybatisdemo.config;
/**
 * Copyright (c) 2019-2119,CubeGalaxy.com All rights reserved.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lyh
 * @version 1.0
 * @description
 * @date 2021/7/27 15:52
 * @package com.cubegalaxy.cubestage.mybatisdemo.utils.corsConfig
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Open cross-domain
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Set the routes that are allowed across the domain
        registry.addMapping("/**")
                // Set the domain name that allows cross-domain requests
                .allowedOriginPatterns("*")
                // whether to allow certificates (cookies)
                .allowCredentials(true)
                // Set the allowed methods
                .allowedMethods("*")
                // Allowed time across domains
                .maxAge(3600);
    }
}
