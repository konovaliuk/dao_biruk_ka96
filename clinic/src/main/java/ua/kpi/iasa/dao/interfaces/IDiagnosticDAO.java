package ua.kpi.iasa.dao.interfaces;

import ua.kpi.iasa.entities.Diagnostic;

import java.sql.SQLException;

public interface IDiagnosticDAO {
    public void create(int id, String medicines, String treatment, String complains,
                       String diagnose, String operation, float price) throws SQLException;
    void delete(long id) throws SQLException;
    Diagnostic getDiagnostic(long id) throws  SQLException;
    void update(Diagnostic diagnostic) throws SQLException;
}
