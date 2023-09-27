package com.d103.newreka.oauth.infra.oauth.config;

// import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.d103.newreka.oauth.infra.oauth.kakao.client"})
public class HttpInterfaceConfig {
}
