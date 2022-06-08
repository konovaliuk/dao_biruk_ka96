package ua.kpi.iasa.dao.sql;

import ua.kpi.iasa.connection.ConnectionPool;
import ua.kpi.iasa.dao.interfaces.IDiagnosticDAO;
import ua.kpi.iasa.entities.Diagnostic;
import ua.kpi.iasa.entities.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DiagnosticDAO implements IDiagnosticDAO {

    @Override
    public void create(int id, String medicines, String treatment, String complains,
                       String diagnose, String operation, float price) throws SQLException{
        final String SQL = "INSERT INTO diagnostic " +
                "(id_patient, medicines, treatment, complains, diagnose, operation, price, date_updated, date_created) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setInt(1, id);
            ps.setString(2, medicines);
            ps.setString(3, treatment);
            ps.setString(4, complains);
            ps.setString(5, diagnose);
            ps.setString(6, operation);
            ps.setFloat(7, price);
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(9,Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String SQL = "DELETE FROM diagnostics WHERE id="+id;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ){
            ps.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Diagnostic getDiagnostic(long id) throws SQLException {
        final String SQL = "SELECT * FROM diagnostics WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setLong(1, id);
            return GetThisDiagnostic(ps.executeQuery());
        }
    }

    private Diagnostic GetThisDiagnostic (ResultSet rs) throws SQLException {
        Diagnostic diagnostic = new Diagnostic();
        if (rs.isBeforeFirst()) rs.next();
        diagnostic.setId(rs.getInt(1));
        diagnostic.setId_patient(rs.getInt(2));
        diagnostic.setMedicines(rs.getString(3));
        diagnostic.setComplains(rs.getString(4));
        diagnostic.setDiagnose(rs.getString(5));
        diagnostic.setOperation(rs.getString(6));
        diagnostic.setPrice(rs.getFloat(7));
        diagnostic.setDate_updated(rs.getTimestamp(8));
        diagnostic.setDate_created(rs.getTimestamp(9));

        return diagnostic;
    }

    @Override
    public void update(Diagnostic diagnostic) throws SQLException {

    }
}
