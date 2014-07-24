package com.thoughtvalley.poc.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.thoughtvalley.poc.constants.IConfigConstants;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer{

    private static final Logger log = Logger.getLogger(AppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        log.info("onStartup() method invoked");
        AnnotationConfigWebApplicationContext webContext = getContext();
        servletContext.addListener(new ContextLoaderListener(webContext));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(webContext));
        dispatcher.setLoadOnStartup(IConfigConstants.LOAD_ON_STARTUP_VALUE);
        dispatcher.addMapping(IConfigConstants.MAPPING_URL);
        servletContext.addFilter("shiroFilter", new DelegatingFilterProxy("shiroFilter", webContext))
                .addMappingForUrlPatterns(null, true, "/api/*");

    }


    private AnnotationConfigWebApplicationContext getContext()
    {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        return context;
    }
}
