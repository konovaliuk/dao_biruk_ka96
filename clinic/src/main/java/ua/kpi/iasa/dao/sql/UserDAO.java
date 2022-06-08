package ua.kpi.iasa.dao.sql;

import ua.kpi.iasa.connection.ConnectionPool;
import ua.kpi.iasa.dao.interfaces.IUserDAO;
import ua.kpi.iasa.entities.Declarations;
import ua.kpi.iasa.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    @Override
    public User getUser(long id) throws SQLException {
        final String SQL = "SELECT * FROM user WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setLong(1, id);
            return GetThisUser(ps.executeQuery());
        }
    }

    private User GetThisUser (ResultSet rs) throws SQLException {
        User user = new User();
        if (rs.isBeforeFirst()) rs.next();
        user.setId(rs.getInt(1));
        user.setEmail(rs.getString(2));
        user.setFirstName(rs.getString(3));
        user.setLastName(rs.getString(4));
        user.setPassword(rs.getString(5));
        user.setRoleId(rs.getInt(6));

        return user;
    }

    @Override
    public User getUser(String email) throws SQLException {
        final String SQL = "SELECT * FROM user WHERE email = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setString(1, email);
            return GetThisUser(ps.executeQuery());
        }
    }

    @Override
    public void create(User user) throws SQLException {
        final String SQL = "INSERT INTO user (email, firstName, lastName, password, roleId) VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRoleId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<User> getDoctors() {
        String SQL = "SELECT * FROM user WHERE roleId = 1";
        List<User> doctorsList = new ArrayList<User>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                User doctor = GetThisUser(resultSet);
                doctorsList.add(doctor);
            }
            resultSet.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return doctorsList;
    }

    @Override
    public void delete(long id) {
        String SQL = "DELETE FROM user WHERE id="+id;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ){
            ps.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
