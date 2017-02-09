package genome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("genome")
public class SpringConfig extends WebMvcConfigurerAdapter{
    
    @Bean
    public ViewResolver viewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/webjars/")
                .addResourceLocations("/static/")
                .addResourceLocations("*.ico");
    }
}
