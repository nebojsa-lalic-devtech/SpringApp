package test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter{


    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/customers").setViewName("customers");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/403").setViewName("403");
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/simple_application");
        driverManagerDataSource.setUsername("exampleUser");
        driverManagerDataSource.setPassword("P@ssword");
        return driverManagerDataSource;
    }

//        --ZA SADA NIJE POTREBNO(PROBATI BEZ TOGA POKRENUTI)--
//        @Bean
//        public InternalResourceViewResolver viewResolver() {
//            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//            resolver.setPrefix("/WEB-INF/jsp/");
//            resolver.setSuffix(".jsp");
//            return resolver;
//
//        }
}