package genome.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan("genome.config")
public class Program {
    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
        System.out.println("Hell");
    }
}
