package servlets;

import entities.User;
import entities.Video;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.UserService;
import services.VideoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connecte/accueil")
public class AccueilServlet extends GenericServlet{
    String nomVideo = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nomVideo = request.getParameter("nomVideo");
        response.sendRedirect("accueil");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Video video;
        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(request.getServletContext());
        //"raid hei 2019"
        if(nomVideo != ""){
            video =  VideoService.getInstance().getVideo(nomVideo);
        }
        else {
            video =  VideoService.getInstance().getVideo("raid hei 2019");
        }
        String emailAuth = request.getSession().getAttribute("email").toString();
        int level = UserService.getInstance().getUser(emailAuth).getLevel();
        context.setVariable("level",level);
        context.setVariable("laVideo",video);
        templateEngine.process("accueil", context, response.getWriter());
    }
}
