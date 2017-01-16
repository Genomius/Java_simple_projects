package servlets;

import dao.AutoDao;
import dao.UserDao;
import factories.AutoFactory;
import factories.UserFactory;
import models.Auto;
import models.User;
import services.AutoService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Main extends HttpServlet{
    private UserService userService;
    private AutoService autoService;
    
    public void init() throws ServletException {
        super.init();
        UserDao userDao = UserFactory.getInstance().getUserDao();
        AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        
        userService = new UserService(userDao);
        autoService = new AutoService(autoDao);
    }
    
    public Main(){
        super();
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    private void renderToResponse(HttpServletRequest request, HttpServletResponse response, String entity){
        try {
            Main mainServletClass = Main.class.newInstance();
            Method init_method = Main.class.getDeclaredMethod("init");
            init_method.invoke(mainServletClass);
        
            Method method = Main.class.getDeclaredMethod(entity, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(mainServletClass, request, response);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    
        String servletPath = request.getServletPath();
        String[] paths = servletPath.split("/");
        
        if(paths.length == 4){
            String entity = paths[1].toLowerCase();
            String action = paths[2].toLowerCase();
            String secondEntity = paths[3].toLowerCase();
                     
            if(entity.equals("users")){
                if(action.equals("get")){
                    if(secondEntity.equals("autos")){
                        renderToResponse(request, response, entity + "_" + secondEntity);
                    }
                }
            }
            
        } else if(paths.length == 3){
            String entity = paths[1].toLowerCase();
            String action = paths[2].toLowerCase();
            
            if(action.equals("get")){
                if(entity.equals("users")){
                    renderToResponse(request, response, "user");
                } else if(entity.equals("autos")){
                    renderToResponse(request, response, "auto");
                }
            }
        } else if(paths.length == 2) {
            String entity = paths[1].toLowerCase();
            
            renderToResponse(request, response, entity);
        } else{
            request.setAttribute("Title", "Главная страница");
            request.getRequestDispatcher("/templates/index.jsp").forward(request, response);
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
    
        String servletPath = request.getServletPath();
        String[] paths = servletPath.split("/");
    
        if(paths.length == 3){
            String entity = paths[1].toLowerCase();
            String action = paths[2].toLowerCase();
            
            if(action.equals("delete")){
                String id = request.getParameter("id");
                
                if(entity.equals("users")){
                    userService.deleteUserById(Integer.parseInt(id));
                } else if(entity.equals("autos")){
                    autoService.deleteAutoById(Integer.parseInt(id));
                }
            } else if(action.equals("add")){
                if(entity.equals("users")){
                    String name = request.getParameter("name");
                    String age = request.getParameter("age");
                    
                    userService.saveUser(new User(name, Integer.parseInt(age)));
                } else if(entity.equals("autos")){
                    String model = request.getParameter("model");
                    String color = request.getParameter("color");
                    String userId = request.getParameter("user_id");
                    
                    autoService.saveAuto(new Auto(model, color, Integer.parseInt(userId)));
                }
            } else if(action.equals("update")){
                if(entity.equals("users")){
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String age = request.getParameter("age");
        
                    userService.updateUser(userService.getUserById(Integer.parseInt(id)), new User(name, Integer.parseInt(age)));
                } else if(entity.equals("autos")){
                    String id = request.getParameter("id");
                    String model = request.getParameter("model");
                    String color = request.getParameter("color");
                    String userId = request.getParameter("user_id");
        
                    autoService.updateAuto(autoService.getAutoById(Integer.parseInt(id)), new Auto(model, color, Integer.parseInt(userId)));
                }
            }
        }
    }
    
    public void users(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> user_list = userService.getAllUsers();
    
        request.setAttribute("Title", "Пользователи");
        request.setAttribute("users", user_list);
    
        request.getRequestDispatcher("/templates/users.jsp").forward(request, response);
    }
    
    public void autos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Auto> auto_list = autoService.getAllAutos();
        
        request.setAttribute("Title", "Автомобили");
        request.setAttribute("autos", auto_list);
        
        request.getRequestDispatcher("/templates/autos.jsp").forward(request, response);
    }
    
    public void users_autos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.getUserByIdWithAuto(Integer.parseInt(id), autoService);
        List<Auto> autos = user.getAutos();
        
        request.setAttribute("autos", autos);
        request.setAttribute("user", user);
    
        request.getRequestDispatcher("/templates/autos.jsp").forward(request, response);
    
    }
    
    public void user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.parseInt(id));
        
        request.setAttribute("Title", user.getName());
        request.setAttribute("user", user);
        
        request.getRequestDispatcher("/templates/user.jsp").forward(request, response);
    }
}
