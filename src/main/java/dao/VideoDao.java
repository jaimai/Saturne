package dao;

import entities.Video;

import java.util.ArrayList;
import java.util.List;

public interface VideoDao {
    public void ajoutVideo(Video video);
    public ArrayList<Video> listVideo();
    public Video laVideo(String nom);
    public List<Video> listVideoBde();
    public List<Video> listVideoBds();
    public void updateVisible(String nomVideo, String visible);
}
