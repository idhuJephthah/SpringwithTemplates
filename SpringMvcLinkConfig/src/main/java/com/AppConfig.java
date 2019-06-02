package com;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//@EnableWebMvc
@Configuration
public class AppConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public ITemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver spReTeRe =
                new SpringResourceTemplateResolver();
        spReTeRe.setApplicationContext(this.applicationContext);
        spReTeRe.setPrefix("classpath:/templates/");
        spReTeRe.setCacheable(false);
        spReTeRe.setSuffix(".html");
        spReTeRe.setTemplateMode(TemplateMode.HTML);
        spReTeRe.setCharacterEncoding("UTF-8");
        spReTeRe.setCheckExistence(true);
        //spReTeRe.setOrder(1);
        return spReTeRe;
    }

    @Bean
    public ITemplateResolver spResourceTempRes(){
        SpringResourceTemplateResolver springResourceTemplateResolver =
                new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);
        springResourceTemplateResolver.setPrefix("classpath:/template2/");
        springResourceTemplateResolver.setCacheable(false);
       springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        springResourceTemplateResolver.setCheckExistence(true);
        //springResourceTemplateResolver.setOrder(2);
        return springResourceTemplateResolver;

    }

/**
    //@Bean
    public SpringTemplateEngine springTemplateEngine2(){
        SpringTemplateEngine sPTeEn = new SpringTemplateEngine();
        sPTeEn.setTemplateResolver(spResourceTempRes());
        sPTeEn.setEnableSpringELCompiler(true);
        return sPTeEn;
    }
**/
    //@Bean
    public ISpringTemplateEngine springTemplateEngine(ITemplateResolver iTemplateResolver){
        SpringTemplateEngine sPTeEn = new SpringTemplateEngine();
        sPTeEn.setTemplateResolver(iTemplateResolver);
        return sPTeEn;
    }

    @Bean
    public ViewResolver viewResolver(){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(
                springTemplateEngine(springResourceTemplateResolver()));
       // thymeleafViewResolver.setViewNames();
        return thymeleafViewResolver;
    }

    @Bean
    public ViewResolver viewResolver2(){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(
        springTemplateEngine(spResourceTempRes()));
        return thymeleafViewResolver;
    }
}
