package servlets;

import entities.Video;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.VideoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/connecte/videoBde")
public class ShowVideoBdeServlet extends GenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(request.getServletContext());

        List<Video> lesVideos = VideoService.getInstance().lesVideoBde();
        context.setVariable("lesVideo",lesVideos);

        templateEngine.process("videoBde", context, response.getWriter());
    }
}
