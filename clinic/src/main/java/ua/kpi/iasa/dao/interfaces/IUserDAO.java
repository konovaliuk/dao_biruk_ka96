package ua.kpi.iasa.dao.interfaces;

import ua.kpi.iasa.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    User getUser(long id) throws SQLException;
    User getUser(String email) throws SQLException;
    void create(User user) throws SQLException;
    List<User> getDoctors();
    void delete(long id);
}
