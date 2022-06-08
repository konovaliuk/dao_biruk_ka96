package ua.kpi.iasa.dao.interfaces;

import ua.kpi.iasa.entities.Role;
import ua.kpi.iasa.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDAO {
    Role getRole(int id) throws SQLException;
    Role getRole(String name) throws SQLException;
    Role getUserRole(User user) throws SQLException;
}
