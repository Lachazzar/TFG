package es.udc.tfgproject.frontend;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
	CookieLocaleResolver clr = new CookieLocaleResolver();
	clr.setDefaultLocale(new Locale("es"));
	return clr;
    }

    @Bean
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
	SpringTemplateEngine engine = new SpringTemplateEngine();
	engine.addDialect(new Java8TimeDialect());
	engine.setTemplateResolver(templateResolver);
	return engine;
    }

    @Bean
    public MessageSource messageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

	messageSource.setBasename("classpath:messages");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
    }

    @Bean
    @Override
    public LocalValidatorFactoryBean getValidator() {
	LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	bean.setValidationMessageSource(messageSource());
	return bean;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
	LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	lci.setParamName("lang");
	return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
	registro.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/").setViewName("home");
	registry.addViewController("/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/images/", "/css/").addResourceLocations("classpath:/static/images/",
		"classpath:/static/css/");
    }
}
