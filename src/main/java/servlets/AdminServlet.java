package servlets;
import entities.Categorie;
import entities.User;
import entities.Video;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.CategorieService;
import services.UserService;
import services.VideoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/connecte/admin")
public class AdminServlet extends GenericServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ArrayList<Video> lesVideos = VideoService.getInstance().lesVideos();
        ArrayList<User> lesUsers = UserService.getInstance().lesUsers();
        List<Categorie> lesCategories = CategorieService.getInstance().lesCategories();
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("lesCategories",lesCategories);
        context.setVariable("lesUsers",lesUsers);
        context.setVariable("lesVideos",lesVideos);
        TemplateEngine templateEngine = createTemplateEngine(request.getServletContext());
        templateEngine.process("admin", context, response.getWriter());

    }

}
