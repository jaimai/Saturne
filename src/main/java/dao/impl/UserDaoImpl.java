package dao.impl;

import dao.UserDao;
import entities.User;
import utils.MotDePasseUtil;

import java.sql.*;

import static dao.impl.DataSourceProvider.getDataSource;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUSer(User user) {
        try(Connection connection = getDataSource().getConnection()){
            String req = "INSERT INTO users(email,mdp) VALUES(?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1,user.getEmail());
                preparedStatement.setString(2,user.getMdp());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Boolean verifUser(String email, String mdp) {
        boolean existe = false;
        try(Connection connection = getDataSource().getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=?")){
                preparedStatement.setString(1,email);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        String mdp2 = resultSet.getString("mdp");
                        boolean verifMdp = MotDePasseUtil.validerMotDePasse(mdp,mdp2);
                        if(verifMdp){
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
        try(Connection connection = getDataSource().getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")){
                preparedStatement.setString(1,email);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
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

}