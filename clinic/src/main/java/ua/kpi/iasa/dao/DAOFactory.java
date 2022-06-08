package ua.kpi.iasa.dao;

import ua.kpi.iasa.dao.interfaces.IDeclarationDAO;
import ua.kpi.iasa.dao.interfaces.IDiagnosticDAO;
import ua.kpi.iasa.dao.interfaces.IRoleDAO;
import ua.kpi.iasa.dao.interfaces.IUserDAO;
import ua.kpi.iasa.dao.sql.DeclarationDAO;
import ua.kpi.iasa.dao.sql.DiagnosticDAO;
import ua.kpi.iasa.dao.sql.RoleDAO;
import ua.kpi.iasa.dao.sql.UserDAO;

public class DAOFactory {
    public IUserDAO getUserDAO() {
        return new UserDAO();
    }
    public IRoleDAO getRoleDAO() { return new RoleDAO(); }
    public IDeclarationDAO getDeclarationDAO() {return new DeclarationDAO(); }
    public IDiagnosticDAO getDiagnosticDAO(){return new DiagnosticDAO();}
}
