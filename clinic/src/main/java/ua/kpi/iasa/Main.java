package ua.kpi.iasa;

import ua.kpi.iasa.dao.DAOFactory;
import ua.kpi.iasa.dao.sql.DeclarationDAO;
import ua.kpi.iasa.dao.sql.DiagnosticDAO;
import ua.kpi.iasa.dao.sql.UserDAO;
import ua.kpi.iasa.entities.Declarations;
import ua.kpi.iasa.entities.Diagnostic;
import ua.kpi.iasa.entities.User;

import java.sql.SQLException;
import java.util.List;

public class Main {

    private static void CreateUser(UserDAO dao, User newUser) throws SQLException {
        dao.create(newUser);
        System.out.println("Successfully created new user '" + newUser.getFirstName() +' '+ newUser.getLastName() + "'");
    }

    private static int GetUserByEmail(UserDAO dao, String email) throws SQLException {
        User user = dao.getUser(email);
        if (user == null) {
            System.out.println("No user found");
            return -1;
        }
        else System.out.println("User with username " + email + " has id = " + user.getId());
        return user.getId();
    }

    private static void GetUserById(UserDAO dao, int id) throws SQLException {
        User user = dao.getUser(id);
        if (user == null) System.out.println("No user found");
        else System.out.println("User with id = " + id + " is " + user.getFirstName() + ' ' + user.getLastName());
    }

    private static void createDeclaration(DeclarationDAO dao, Declarations newDeclaration) throws SQLException{
        dao.createDeclaration(newDeclaration);
        System.out.println("Successfully created new declaration id_patient = " + newDeclaration.getId_patient() +" id_doctor = " + newDeclaration.getId_doctor());

    }

    private static void printAllDeclarations(){
        DAOFactory dao = new DAOFactory();
        DeclarationDAO declarationDAO =  (DeclarationDAO) dao.getDeclarationDAO();
        List<Declarations> declarationsList = declarationDAO.getAllDeclarations();
        for (Declarations declaration : declarationsList) {
            System.out.println("Declaration id_patient = " + declaration.getId_patient()+" id_doctor = " +declaration.getId_doctor());
        }
    }

    private static void printAllDoctors(){
        DAOFactory dao = new DAOFactory();
        UserDAO userDAO = (UserDAO) dao.getUserDAO();
        List<User> doctorsList = userDAO.getDoctors();
        System.out.println("You can make an appointment with the following doctors");
        for (User doctor : doctorsList) {
            System.out.println(doctor.getId() + " " + doctor.getFirstName() + " " + doctor.getLastName());
        }
    }

    private static void GetByIdDoctor(DeclarationDAO dao, UserDAO userDAO, long id) throws SQLException {
        List<Declarations> declarationList = dao.getByIdDoctor(id);
        User user = userDAO.getUser(id);
        System.out.println(user.getFirstName() + " "+user.getLastName()+" has the following patients");
        for (Declarations declaration: declarationList) {
            user = userDAO.getUser(declaration.getId_patient());
            System.out.println(user.getId() + " " + user.getFirstName() + " " + user.getLastName());
        }
    }

    private static void UpdateDeclaration(DeclarationDAO dao, long idPatient, long idDoctor) throws SQLException {
        System.out.println("Rewrite the patient's declaration with id = "+idPatient+" with the new doctor with id = "+idDoctor);
        dao.updateDeclaration(idPatient, idDoctor);
    }

    private static void DeleteDeclaration(DeclarationDAO dao, long id){
        dao.deleteDeclaration(id);
        System.out.println("Delete the patient's declaration with id = "+id);
    }
    private static void CreateDiagnostic(DiagnosticDAO dao, int id, String medicines, String treatment, String complains,
                                         String diagnose, String operation, float price) throws SQLException {
        dao.create(id, medicines, treatment, complains, diagnose, operation, price);
        System.out.println("Successfully created new diagnostic for id_patient = "+ id);
    }

    public static void main(String[] args) throws SQLException {
        DAOFactory dao = new DAOFactory();
        UserDAO userDAO = (UserDAO) dao.getUserDAO();
        DeclarationDAO declarationDAO =  (DeclarationDAO) dao.getDeclarationDAO();
        DiagnosticDAO diagnosticDAO = (DiagnosticDAO) dao.getDiagnosticDAO();

        try {
            //Test User
    //        userDAO.delete(15);
            CreateUser(userDAO, new User("newuser@gmail.com",  "Oliver", "Dorwil", "qwerty123",3));
            int id = GetUserByEmail(userDAO, "newuser@gmail.com");
            printAllDoctors();

            //Test Declaration
            createDeclaration(declarationDAO, new Declarations(id, 1));
            printAllDeclarations();
            GetByIdDoctor(declarationDAO, userDAO, 1);
            UpdateDeclaration(declarationDAO, 13, 2);
            DeleteDeclaration(declarationDAO, id);

            //Test Diagnostic
            CreateDiagnostic(diagnosticDAO, 13, "Amoxicillin, Gabapentin",
                    "Drink tea and bed rest", "Headache, fever", "Flu", "None", 170);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}