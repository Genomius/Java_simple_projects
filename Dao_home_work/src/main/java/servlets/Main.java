package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        
        PrintWriter pw = resp.getWriter();
        pw.println("<H1>Hello, world! или Привет мир</H1>");
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
