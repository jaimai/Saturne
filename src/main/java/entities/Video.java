package entities;

import java.sql.Date;

public class Video {
    private String nom = "";
    private Date dateVideo;
    private String url = "";
    private String visible = "";
    private int idCategorie = 0;

    public Video(String nom, Date dateVideo, String url) {
        this.nom = nom;
        this.dateVideo = dateVideo;
        this.url = url;
    }
    public Video(String nom, Date dateVideo, String url, String visible) {
        this.nom = nom;
        this.dateVideo = dateVideo;
        this.url = url;
        this.visible = visible;
    }
    public Video(String nom, Date dateVideo, String url, String visible,int idCategorie) {
        this.nom = nom;
        this.dateVideo = dateVideo;
        this.url = url;
        this.visible = visible;
        this.idCategorie = idCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }
    public void setVisible (String visible) {
        this.visible = visible;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getVisible() {
        return visible;
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
