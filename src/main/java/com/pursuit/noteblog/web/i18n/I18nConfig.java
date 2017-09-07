package com.pursuit.noteblog.web.i18n;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

public class I18nConfig extends WebMvcConfigurerAdapter{

    @Bean
    public LocaleResolver localeResolver() {
    	//cookie区域解析器
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        //session区域解析器
        //SessionLocaleResolver resolver = new SessionLocaleResolver();
        //FixedLocaleResolver resolver = new FixedLocaleResolver ();
        
        //设置默认区域,
        resolver.setDefaultLocale(Locale.CHINA);
        resolver.setCookieName("LEANOTE_LANG");
        //resolver.setDefaultLocale(Locale.US);
        resolver.setCookieMaxAge(3600);//设置cookie有效期.
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 设置请求地址的参数,默认为：locale
        //lci.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
