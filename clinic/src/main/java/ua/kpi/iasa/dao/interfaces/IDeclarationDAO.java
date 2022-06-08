package ua.kpi.iasa.dao.interfaces;

import ua.kpi.iasa.entities.Declarations;

import java.sql.SQLException;
import java.util.List;

public interface IDeclarationDAO {
    List<Declarations> getAllDeclarations();
    List<Declarations> getByIdDoctor(long idDoctor) throws IllegalArgumentException;
    void createDeclaration(Declarations declarations) throws SQLException;
    void updateDeclaration(long idPatient, long idDoctor) throws SQLException;
    void deleteDeclaration(long idPatient);
}
