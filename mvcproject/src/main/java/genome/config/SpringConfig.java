package genome.config;

import genome.models.Auto;
import genome.models.User;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("genome")
public class SpringConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
    }
    
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.addAnnotatedClass(User.class);
        builder.addAnnotatedClass(Auto.class);
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
    
        return builder.buildSessionFactory();
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/user_auto_dao");
        driverManagerDataSource.setUsername("den");
        driverManagerDataSource.setPassword("123321");
        
        return driverManagerDataSource;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/webjars/")
                .addResourceLocations("/WEB-INF/static/")
                .addResourceLocations("*.ico");
    }
}