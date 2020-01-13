package servlets;

import entities.Video;
import services.VideoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/connecte/ajoutVideo")
public class AjoutVideoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("txtNom");
        String dateVideo = request.getParameter("dateVideo");
        String id = request.getParameter("txtId");
        Date dv = Date.valueOf(dateVideo);
        Video video = new Video(nom,dv,id);
        System.out.println(video.toString());
        VideoService.getInstance().ajoutVideo(video);
        response.sendRedirect("admin");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
