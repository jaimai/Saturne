package dao.impl;

import dao.VideoDao;
import entities.User;
import entities.Video;

import java.sql.*;
import java.util.ArrayList;

import static dao.impl.DataSourceProvider.getDataSource;

public class VideoDaoImpl implements VideoDao {
    @Override
    public void ajoutVideo(Video video) {
        try (Connection connection = getDataSource().getConnection()) {
            String req = "INSERT INTO video(nom,dateV,url) VALUES(?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, video.getNom());
                preparedStatement.setDate(2, video.getDateVideo());
                preparedStatement.setString(3, video.getUrl());
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
                                resultSet.getString("url")
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
                                resultSet.getString("url")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
