package services;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.User;

import java.util.ArrayList;

public class UserService {
    private static class UserServiceHolder {
        private static UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }
    private UserDao userDao= new UserDaoImpl();
    private UserService() {
    }
    public boolean verifUser(String email,String mdp){
        return userDao.verifUser(email,mdp);
    }
    public  void addUser(User user){
        userDao.addUSer(user);
    }
    public User getUser(String email) {return userDao.getUser(email);}
    public ArrayList<User> lesUsers() {return userDao.ListUsers();}
}
