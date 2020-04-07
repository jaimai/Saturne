package servlets;

import entities.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import services.UserService;
import utils.MotDePasseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/inscription")
public class AddUserServlet extends GenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String mdp= request.getParameter("mdp");
        mdp = MotDePasseUtil.genererMotDePasse(mdp);
        User user = new User(email,mdp,0);
        boolean error = false;

        if(email.contains("@") &&  email.contains("yncrea") && !mdp.isEmpty()){
            try {
                UserService.getInstance().addUser(user);
            } catch (IllegalArgumentException e) {
                request.getSession().setAttribute("errorMessage", e.getMessage());
            }
                request.getSession().setAttribute("email",email);
                response.sendRedirect("connecte/accueil");
          }
        else {
            response.sendRedirect("../inscription");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = (String) request.getSession().getAttribute("errorMessage");
        request.getSession().removeAttribute("erreur");
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(request.getServletContext());
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix("/webfonts/templates/");
        resolver.setSuffix(".html");

        //Webcontext
        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(request.getServletContext());
        templateEngine.process("inscription", context, response.getWriter());
    }
}
