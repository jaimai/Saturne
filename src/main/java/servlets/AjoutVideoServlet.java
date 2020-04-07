package servlets;

import dao.CategorieDao;
import entities.Video;
import services.CategorieService;
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
        String visible = request.getParameter("selectVisible");
        String categorie = request.getParameter("selectCategorie");
        Date dv = Date.valueOf(dateVideo);
        int idCategorie = CategorieService.getInstance().idCategorie(categorie);
        Video video = new Video(nom,dv,id,visible,idCategorie);
        VideoService.getInstance().ajoutVideo(video);
        response.sendRedirect("admin");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
