package com.tenpo.math.configuration;

import com.tenpo.math.audit.AuditInterceptor;
import com.tenpo.math.audit.IAuditService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final IAuditService auditService;

    public MvcConfig(IAuditService auditService){
        this.auditService = auditService;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AuditInterceptor(auditService));
    }
}
