package dao;

import entities.User;

public interface UserDao {
    public void addUSer(User user);
    public Boolean verifUser(String email, String mdp);
    public User getUser(String email);
}
