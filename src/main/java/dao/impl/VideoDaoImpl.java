package dao.impl;

import dao.VideoDao;
import entities.User;
import entities.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.impl.DataSourceProvider.getDataSource;

public class VideoDaoImpl implements VideoDao {
    @Override
    public void ajoutVideo(Video video) {
        try (Connection connection = getDataSource().getConnection()) {
            String req = "INSERT INTO video(nom,dateV,url,visible,idCategorie) VALUES(?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, video.getNom());
                preparedStatement.setDate(2, video.getDateVideo());
                preparedStatement.setString(3, video.getUrl());
                preparedStatement.setString(4,video.getVisible());
                preparedStatement.setInt(5,video.getIdCategorie());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Video> listVideo() {
        ArrayList<Video> lesVideos = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM video")) {
                    while (resultSet.next()) {
                        Video video = new Video(
                                resultSet.getString("nom"),
                                resultSet.getDate("dateV"),
                                resultSet.getString("url"),
                                resultSet.getString("visible")

                        );
                        lesVideos.add(video);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesVideos;
    }

    @Override
    public Video laVideo(String nom) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM video WHERE nom = ?")) {
                preparedStatement.setString(1, nom);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Video(
                                resultSet.getString("nom"),
                                resultSet.getDate("dateV"),
                                resultSet.getString("url"),
                                resultSet.getString("visible")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Video> listVideoBde() {
        List<Video> lesVideos = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM video INNER JOIN categorie ON video.idCategorie = categorie.idCategorie WHERE nomCategorie = 'bde'")) {
                    while (resultSet.next()) {
                        Video video = new Video(
                                resultSet.getString("nom"),
                                resultSet.getDate("dateV"),
                                resultSet.getString("url"),
                                resultSet.getString("visible")
                        );
                        lesVideos.add(video);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesVideos;
    }

    @Override
    public List<Video> listVideoBds() {
        List<Video> lesVideos = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM video INNER JOIN categorie ON video.idCategorie = categorie.idCategorie WHERE nomCategorie = 'bds'")) {
                    while (resultSet.next()) {
                        Video video = new Video(
                                resultSet.getString("nom"),
                                resultSet.getDate("dateV"),
                                resultSet.getString("url"),
                                resultSet.getString("visible")
                        );
                        lesVideos.add(video);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesVideos;
    }

    @Override
    public void updateVisible(String nomVideo, String visible) {
        try(Connection connection = getDataSource().getConnection()){
            String req = "UPDATE video set visible = ? WHERE nom = ? ";
            try(PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1,visible);
                preparedStatement.setString(2,nomVideo);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
