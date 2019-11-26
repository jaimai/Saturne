package services;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.User;

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
}
