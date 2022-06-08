package ua.kpi.iasa.dao.sql;

import ua.kpi.iasa.connection.ConnectionPool;
import ua.kpi.iasa.dao.interfaces.IRoleDAO;
import ua.kpi.iasa.entities.Declarations;
import ua.kpi.iasa.entities.Role;
import ua.kpi.iasa.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements IRoleDAO {

    @Override
    public Role getRole(int id) throws SQLException {
        final String SQL = "SELECT * FROM role WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setInt(1, id);
            return GetThisRole(ps.executeQuery());
        }
    }

    private Role GetThisRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        if (rs.isBeforeFirst()) rs.next();
        role.setId(rs.getInt(1));
        role.setName(rs.getString(2));
        return role;
    }

    @Override
    public Role getRole(String name) throws SQLException {
        final String SQL = "SELECT * FROM role WHERE name = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setString(1, name);
            return GetThisRole(ps.executeQuery());
        }
    }

    @Override
    public Role getUserRole(User user) throws SQLException {
        final int id = user.getId();
        return getRole(id);
    }
}
