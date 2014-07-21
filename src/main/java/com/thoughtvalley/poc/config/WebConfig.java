package com.thoughtvalley.poc.config;

import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Naresh on 7/21/2014.
 */
@Configuration
@ComponentScan(basePackages={"com.thoughtvalley.poc"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(iniRealm());
        return securityManager;
    }

    @Bean
    public IniRealm iniRealm(){

        return new IniRealm();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setSuccessUrl("/dashboard.html");
        shiroFilter.setUnauthorizedUrl("/unauthorized.html");
        shiroFilter.setFilterChainDefinitions("/test = authc");
        return shiroFilter;
    }
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(){
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{securityManager()});
        return methodInvokingFactoryBean;
    }

    @Bean
    @DependsOn(value="lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

}
