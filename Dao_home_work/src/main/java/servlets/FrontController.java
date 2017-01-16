package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrontController extends HttpServlet{
    private static final Pattern pattern = Pattern.compile(
            "^(\\/)?([a-z]{5})?(\\/[0-9]+)?(\\/[a-z]{5})?(\\/[0-9]+)?(\\?action=(update|delete))?$");
    
    public void init() throws ServletException{
        
    }

    @Override
    public void destroy() {
        super.destroy();
    }
    
    private static boolean doMatch(String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestMethod = request.getMethod();
        String requestUri = request.getRequestURI();
        
        if(!doMatch(requestUri))
            request.getRequestDispatcher("/templates/404.jsp").forward(request, response);
        
        if (requestUri.equals("/"))
            request.getRequestDispatcher("/templates/index.jsp").forward(request, response);
    
        String[] paths = requestUri.split("/");
        System.out.println();
        for (int i=0; i<paths.length; i++){
            switch (i){
                case 1:
                    request.setAttribute("entity1", paths[1]); break;
                case 2:
                    request.setAttribute("entity1Id", paths[2]); break;
                case 3:
                    request.setAttribute("entity2", paths[3]); break;
                case 4:
                    request.setAttribute("entity2Id", paths[4]); break;
            }
        }
        
        request.getRequestDispatcher("/" + (request.getAttribute("entity2") == null ? paths[1] : paths[3]))
                .forward(request, response);
    }
}
