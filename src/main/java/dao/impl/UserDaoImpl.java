package dao.impl;

import dao.UserDao;
import entities.User;
import utils.MotDePasseUtil;

import java.io.PipedReader;
import java.sql.*;
import java.util.ArrayList;

import static dao.impl.DataSourceProvider.getDataSource;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUSer(User user) {
        try (Connection connection = getDataSource().getConnection()) {
            String req = "INSERT INTO users(email,mdp) VALUES(?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getMdp());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Boolean verifUser(String email, String mdp) {
        boolean existe = false;
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=?")) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String mdp2 = resultSet.getString("mdp");
                        boolean verifMdp = MotDePasseUtil.validerMotDePasse(mdp, mdp2);
                        if (verifMdp) {
                            existe = true;
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }

    @Override
    public User getUser(String email) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(
                                resultSet.getString("email"),
                                resultSet.getString("mdp"),
                                resultSet.getInt("level")
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
    public ArrayList<User> ListUsers() {
        ArrayList<User> lesUsers = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
                    while (resultSet.next()) {
                        User user = new User(
                                resultSet.getString("email"),
                                resultSet.getString("mdp"),
                                resultSet.getInt("level")
                        );
                        lesUsers.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesUsers;
    }

    @Override
    public void deleteUser(String email) {
        try(Connection connection = getDataSource().getConnection()){
            String req = "DELETE FROM user WHERE email = ? ";
            try(PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1,email);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLevel(String email, int level) {
        try(Connection connection = getDataSource().getConnection()){
            String req = "UPDATE users set level = ? WHERE email = ? ";
            try(PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1,level);
                preparedStatement.setString(2,email);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
