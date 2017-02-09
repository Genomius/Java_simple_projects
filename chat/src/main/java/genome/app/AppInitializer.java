package genome.app;

import genome.config.SpringConfig;
import genome.config.WebSocketConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebSocketConfig.class, SpringConfig.class};
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebSocketConfig.class, SpringConfig.class};
    }
    
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
