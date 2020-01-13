package dao;

import entities.Video;

import java.util.ArrayList;

public interface VideoDao {
    public void ajoutVideo(Video video);
    public ArrayList<Video> listVideo();
    public Video laVideo(String nom);
}
