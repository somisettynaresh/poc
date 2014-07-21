package com.thoughtvalley.poc.config;


import com.thoughtvalley.poc.constants.IConfigConstants;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackages={"com.thoughtvalley.poc"})
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter  {

    private static final Logger log = Logger.getLogger(AppConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        log.info("addResourceHandlers() method invoked");

        registry.addResourceHandler(IConfigConstants.RESOURCE_HANDLER).addResourceLocations(IConfigConstants.RESOURCE_LOCATION).setCachePeriod(IConfigConstants.CACHE_PERIOD_DEFAULT);
        registry.addResourceHandler(IConfigConstants.IMAGE_HANDLER).addResourceLocations(IConfigConstants.IMAGE_LOCATION).setCachePeriod(IConfigConstants.CACHE_PERIOD_DEFAULT);
        registry.addResourceHandler(IConfigConstants.CSS_HANDLER).addResourceLocations(IConfigConstants.CSS_LOCATION).setCachePeriod(IConfigConstants.CACHE_PERIOD_DEFAULT);
        registry.addResourceHandler(IConfigConstants.SCRIPT_HANDLER).addResourceLocations(IConfigConstants.SCRIPT_LOCATION).setCachePeriod(IConfigConstants.CACHE_PERIOD_DEFAULT);
    }


    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        log.info("configureDefaultServletHandling() method invoked");

        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {

        log.info("getInternalResourceViewResolver() method invoked");

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }




}

