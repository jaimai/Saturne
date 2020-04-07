package dao.impl;

import dao.CategorieDao;
import entities.Categorie;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static dao.impl.DataSourceProvider.getDataSource;

public class CategorieDaoImpl implements CategorieDao {
    @Override
    public List<Categorie> listCategorie() {
        List<Categorie> lesCategories = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM categorie")) {
                    while (resultSet.next()) {
                        Categorie categorie = new Categorie(
                                resultSet.getInt("idCategorie"),
                                resultSet.getString("nomCategorie")
                        );
                        lesCategories.add(categorie);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesCategories;
    }

    @Override
    public int getIdCategorie(String nom) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT idCategorie FROM categorie WHERE nomCategorie = ?")) {
                preparedStatement.setString(1, nom);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                       return resultSet.getInt("idCategorie");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
