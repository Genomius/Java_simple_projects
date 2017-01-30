package genome.server;

import genome.models.Chat;
import genome.models.Message;
import genome.models.User;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

import javax.sql.DataSource;

@SpringBootApplication//(scanBasePackages = {"genome.config"})
//@EnableAutoConfiguration
@ComponentScan("genome.config")
public class Program {
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.addAnnotatedClass(User.class);
        //builder.addAnnotatedClass(Chat.class);
        //builder.addAnnotatedClass(Message.class);
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        
        return builder.buildSessionFactory();
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/client_server_app");
        driverManagerDataSource.setUsername("den");
        driverManagerDataSource.setPassword("123321");
        
        return driverManagerDataSource;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
        System.out.println("Hell");
    }
}
