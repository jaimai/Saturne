package entities;

import java.sql.Date;

public class Video {
    private String nom = "";
    private Date dateVideo;
    private String url = "";

    public Video(String nom, Date dateVideo, String url) {
        this.nom = nom;
        this.dateVideo = dateVideo;
        this.url = url;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateVideo() {
        return dateVideo;
    }

    public void setDateVideo(Date dateVideo) {
        this.dateVideo = dateVideo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" +
                "nom='" + nom + '\'' +
                ", dateVideo=" + dateVideo +
                ", url='" + url + '\'' +
                '}';
    }
}
