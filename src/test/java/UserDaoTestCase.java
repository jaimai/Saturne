import dao.UserDao;
import dao.impl.DataSourceProvider;
import dao.impl.UserDaoImpl;
import entities.User;
import org.junit.Test;
import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class UserDaoTestCase {
    private UserDao userDao= new UserDaoImpl();

    @Test
    public void shouldAddUser() throws SQLException {
        User user = new User("thomas.ferrer@hei.yncrea.fr","toto");
        userDao.addUSer(user);
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email = 'thomas.ferrer@hei.yncrea.fr' AND  mdp = 'toto'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getString("email")).isEqualTo("thomas.ferrer@hei.yncrea.fr");
                assertThat(rs.getString("mdp")).isEqualTo("toto");
                assertThat(rs.next()).isTrue();
            }
        }
    }
    @Test
    public void shouldVerifUser(){
        // WHEN
        boolean userExist = userDao.verifUser("thomas.ferrer@hei.yncrea.fr","toto");
        // THEN
        assertThat(userExist).isTrue();
    }

}
