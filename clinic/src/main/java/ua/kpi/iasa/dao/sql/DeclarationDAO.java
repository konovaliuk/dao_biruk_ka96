package ua.kpi.iasa.dao.sql;

import ua.kpi.iasa.connection.ConnectionPool;
import ua.kpi.iasa.dao.interfaces.IDeclarationDAO;
import ua.kpi.iasa.entities.Declarations;
import ua.kpi.iasa.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeclarationDAO implements IDeclarationDAO {

    private Declarations GetDeclaration (ResultSet rs) throws SQLException {
        Declarations declarations = new Declarations();
        declarations.setId_patient(rs.getInt(1));
        declarations.setId_doctor(rs.getInt(2));

        return declarations;
    }

    @Override
    public List<Declarations> getAllDeclarations() {
        String SQL = "SELECT * FROM declarations";
        List<Declarations> declarationsList = new ArrayList<Declarations>();

        try (Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL)
        ){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Declarations declaration = GetDeclaration(resultSet);
                declarationsList.add(declaration);
            }
            resultSet.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return declarationsList;
    }

    @Override
    public List<Declarations> getByIdDoctor(long id) throws IllegalArgumentException {
        String SQL = "SELECT * FROM declarations WHERE id_doctor = "+id;
        List<Declarations> declarationsList = new ArrayList<Declarations>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Declarations declaration = GetDeclaration(resultSet);
                declarationsList.add(declaration);
            }
            resultSet.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return declarationsList;
    }

    @Override
    public void createDeclaration(Declarations declarations) throws SQLException {
        final String SQL = "INSERT INTO declarations (id_patient, id_doctor) VALUES (?, ?);";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.setInt(1, declarations.getId_patient());
            ps.setInt(2, declarations.getId_doctor());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateDeclaration(long idPatient, long idDoctor) throws SQLException {
        String SQL = "UPDATE declarations SET id_doctor = "+idDoctor
                +" WHERE id_patient="+idPatient;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ) {
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDeclaration(long idPatient) {
        String SQL = "DELETE FROM declarations WHERE id_patient="+idPatient;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)
        ){
            ps.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
