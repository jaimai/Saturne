package services;

import dao.UserDao;
import dao.VideoDao;
import dao.impl.UserDaoImpl;
import dao.impl.VideoDaoImpl;
import entities.User;
import entities.Video;

import java.util.ArrayList;

public class VideoService {

    private static class VideoServiceHolder {
        private static services.VideoService instance = new services.VideoService();
    }

    public static services.VideoService getInstance() {
        return services.VideoService.VideoServiceHolder.instance;
    }

    private VideoDao videoDao = new VideoDaoImpl();

    private VideoService() {
    }

    public void ajoutVideo(Video video) {
        videoDao.ajoutVideo(video);
    }

    public Video getVideo(String nom) {
        return videoDao.laVideo(nom);
    }
    public ArrayList<Video> lesVideos() {
        return videoDao.listVideo();
    }
}

