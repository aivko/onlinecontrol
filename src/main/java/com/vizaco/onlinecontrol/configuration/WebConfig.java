package com.vizaco.onlinecontrol.configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.vizaco.onlinecontrol.converters.*;
import com.vizaco.onlinecontrol.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.datasource.init.CompositeDatabasePopulator;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Created by super on 6/5/15.
 */
@Configuration
@ComponentScan({
        "com.vizaco.onlinecontrol.aspects",
        "com.vizaco.onlinecontrol.service",
        "com.vizaco.onlinecontrol.controller",
        "com.vizaco.onlinecontrol.dao",
        "com.vizaco.onlinecontrol.utils",
        "com.vizaco.onlinecontrol.model",
        "com.vizaco.onlinecontrol.validators",
        "com.vizaco.onlinecontrol.converters",
        "com.vizaco.onlinecontrol.configuration",
        "com.vizaco.onlinecontrol.security"})
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource("classpath:spring/data-access.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Environment environment;

    @Bean
    public Converter<String, Clazz> stringToClazz() {
        return new StringToClazz();
    }

    @Bean
    public Converter<String, User> stringToUser() {
        return new StringToUser();
    }

    @Bean
    public Converter<String, Period> stringToPeriod() {
        return new StringToPeriod();
    }

    @Bean
    public Converter<String, Subject> stringToSubject() {
        return new StringToSubject();
    }

    @Bean
    public Converter<String, Teacher> stringToTeacher() {
        return new StringToTeacher();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());

        CompositeDatabasePopulator compositeDatabasePopulator = new CompositeDatabasePopulator();

        Resource resourceInit = new DefaultResourceLoader().getResource(environment.getProperty("jdbc.initLocation"));
        ResourceDatabasePopulator resourceDatabasePopulatorInit = new ResourceDatabasePopulator(false, false, "utf8", resourceInit);
        compositeDatabasePopulator.addPopulators(resourceDatabasePopulatorInit);

        Resource resourceData = new DefaultResourceLoader().getResource(environment.getProperty("jdbc.dataLocation"));
        ResourceDatabasePopulator resourceDatabasePopulatorData = new ResourceDatabasePopulator(false, false, "utf8", resourceData);
        compositeDatabasePopulator.addPopulators(resourceDatabasePopulatorData);

        dataSourceInitializer.setDatabasePopulator(compositeDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.vizaco.onlinecontrol.model"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(Boolean.parseBoolean(environment.getProperty("jpa.showSql")));

        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));

        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("/jsp/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .useJaf(false)
                .ignoreAcceptHeader(true)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .defaultContentType(MediaType.TEXT_HTML);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        registry.viewResolver(resolver);

        registry.beanName();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, Clazz.class, stringToClazz());
        registry.addConverter(String.class, User.class, stringToUser());
        registry.addConverter(String.class, Period.class, stringToPeriod());
        registry.addConverter(String.class, Subject.class, stringToSubject());
        registry.addConverter(String.class, Teacher.class, stringToTeacher());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        converters.stream().filter(converter -> converter instanceof StringHttpMessageConverter).forEach(converter -> {
            int index = converters.indexOf(converter);
            StringHttpMessageConverter newConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
            converters.set(index, newConverter);
        });
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public Locale locale() {
        return new Locale("uk", "Ua");
    }

    @Bean
    public TimeZone timeZone() {
        return TimeZone.getTimeZone("Europe/Kiev");
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(locale());
        return localeResolver;
    }

}