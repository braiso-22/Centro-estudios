/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.AlumnoDAO;
import dao.AsignaturaDAO;
import dao.DetalleClaseDAO;
import dao.ProfesorDAO;
import java.sql.*;

/**
 *
 * @author Brais
 */
public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final int MYSQL = 1;

    public abstract Connection getConnection()
            throws Exception;
    // There will be a method for each DAO that can be // created. The concrete factories will have to
    // implement these methods.

    public abstract AlumnoDAO getAlumnoDAO();

    public abstract ProfesorDAO getProfesorDAO();

    public abstract AsignaturaDAO getAsignaturaDAO();

    public abstract DetalleClaseDAO getDetalleClaseDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySQLDAOFactory();

            /*case SQLServer:
                return new SQLServerDAOFactory();
             */
            default:
                return null;
        }
    }

    public boolean releaseConnection(Connection connection) {
// TODO Auto-generated method stub
        return false;
    }

    public int getSize() {
// TODO Auto-generated method stub
        return 0;
    }

    public void shutdown() throws Exception {
// TODO Auto-generated method stub
    }
}
