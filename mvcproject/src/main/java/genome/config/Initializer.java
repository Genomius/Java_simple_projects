package genome.config;

import genome.filters.AuthFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class Initializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        FilterRegistration.Dynamic filter = servletContext.addFilter("authFilter", new AuthFilter());
        filter.addMappingForServletNames(null, true, "DispatcherServlet");
        //FilterRegistration.Dynamic loginFilter = servletContext.addFilter("loginFilter", new LoginFilter());
        //loginFilter.addMappingForUrlPatterns(null, false, "/", "*");
    }
    
    private AnnotationConfigWebApplicationContext getContext(){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("genome/config");
        
        return context;
    }
}
