package com.vizaco.onlinecontrol.configuration;

import com.vizaco.onlinecontrol.converters.StringToClazz;
import com.vizaco.onlinecontrol.converters.StringToUser;
import com.vizaco.onlinecontrol.security.impl.CustomUserDetailsServiceImpl;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.service.impl.ClazzServiceImpl;
import com.vizaco.onlinecontrol.service.impl.UserServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.CompositeDatabasePopulator;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityMetadataSourceAdvisor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by super on 6/5/15.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.vizaco.onlinecontrol.aspects",
        "com.vizaco.onlinecontrol.service",
        "com.vizaco.onlinecontrol.controller",
        "com.vizaco.onlinecontrol.dao",
        "com.vizaco.onlinecontrol.model",
        "com.vizaco.onlinecontrol.validators",
        "com.vizaco.onlinecontrol.converters",
        "com.vizaco.onlinecontrol.configuration",
        "com.vizaco.onlinecontrol.security"})
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource("classpath:spring/data-access.properties")
public class WebConfig extends WebMvcConfigurerAdapter implements TransactionManagementConfigurer {

    @Autowired
    Environment environment;

//    @Autowired
//    ClazzService clazzService;
//
//    @Autowired
//    UserService userService;
//
    @Autowired
    private StringToClazz stringToClazz;

    @Autowired
    private StringToUser stringToUser;
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsServiceImpl();
//    }
//    @Bean
//    public UserService userService() {
//        return new UserServiceImpl();
//    }
//
//    @Bean
//    public ClazzService clazzService() {
//        return new ClazzServiceImpl();
//    }
//
//    @Bean
//    public StringToClazz stringToClazz() {
//        return new StringToClazz();
//    }
//
//    @Bean
//    public StringToUser stringToUser() {
//        return new StringToUser();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public DataSource dataSource() {
        JDBCDataSource dataSource = new JDBCDataSource();
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

        compositeDatabasePopulator.addPopulators(new ResourceDatabasePopulator(new DefaultResourceLoader().getResource(environment.getProperty("jdbc.initLocation"))));
        compositeDatabasePopulator.addPopulators(new ResourceDatabasePopulator(new DefaultResourceLoader().getResource(environment.getProperty("jdbc.dataLocation"))));
        dataSourceInitializer.setDatabasePopulator(compositeDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.vizaco.onlinecontrol.model"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.HSQL);
        vendorAdapter.setShowSql(true);

        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");

        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
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
        registry.addConverter(stringToClazz);
        registry.addConverter(stringToUser);
    }

    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("ru"));
        return localeResolver;
    }

}