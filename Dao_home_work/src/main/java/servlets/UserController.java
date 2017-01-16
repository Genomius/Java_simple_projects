package servlets;

import dao.AutoDao;
import dao.UserDao;
import factories.AutoFactory;
import factories.UserFactory;
import models.User;
import services.AutoService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController extends HttpServlet {
    private UserService userService;
    private AutoService autoService;

    public void init() throws ServletException {
        super.init();
        UserDao userDao = UserFactory.getInstance().getUserDao();
        AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        
        userService = new UserService(userDao);
        autoService = new AutoService(autoDao);
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("entity1Id") != null){
            if (request.getAttribute("entity2") != null){
                if (request.getAttribute("entity2Id") != null){ // /autos/id/users/id
                    User user = userService.getUserById(
                            Integer.parseInt(request.getAttribute("entity2Id").toString()));
                    
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/templates/user.jsp").forward(request, response);
                } else { // /autos/id/users
                    List<User> users = userService.getAllUsersByAutoId(
                            Integer.parseInt(request.getAttribute("entity1Id").toString()), autoService);
    
                    request.setAttribute("users", users);
                    request.getRequestDispatcher("/templates/users.jsp").forward(request, response);
                }
            } else { // /users/id
                User user = userService.getUserById(Integer.parseInt(request.getAttribute("entity1Id").toString()));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/templates/user.jsp").forward(request, response);
            }
        } else { // /users
            List<User> users = userService.getAllUsers();
            
            request.setAttribute("users", users);
            request.getRequestDispatcher("/templates/users.jsp").forward(request, response);
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action.equals("delete")){
            userService.deleteUserById(Integer.parseInt(request.getAttribute("entity1Id").toString()));
        } else if(action.equals("update")) {
            userService.updateUser(userService.getUserById(Integer.parseInt(request.getAttribute("entity1Id").toString())),
                    new User(request.getParameter("name"), Integer.parseInt(request.getParameter("age"))));
        } else {
            userService.saveUser(new User(request.getParameter("name"), Integer.parseInt(request.getParameter("age"))));
        }
    }
}
