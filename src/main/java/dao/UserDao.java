package dao;

import entities.User;

import java.util.ArrayList;

public interface UserDao {
    public void addUSer(User user);
    public Boolean verifUser(String email, String mdp);
    public User getUser(String email);
    public ArrayList<User> ListUsers();
    public void deleteUser(String email);
    public void updateLevel(String email, int level);
}
