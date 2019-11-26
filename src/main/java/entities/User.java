package entities;

public class User {
    private String email = "";
    private String mdp = "";
    private int level = 0;
    public User(String email, String mdp, int level){
        this.email = email;
        this.mdp = mdp;
        this.level = level;
    }
    public User(String email, String mdp){
        this.email = email;
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getLevel() {return this.level;}

    public void setLevel(int newLevel) {this.level = newLevel;}

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
