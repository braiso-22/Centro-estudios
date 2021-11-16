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
import pool.BasicConnectionPool;

/**
 *
 * @author Brais
 */
public class MySQLDAOFactory extends DAOFactory {

    final static String url = "jdbc:mysql:///centro_estudios";
    final static String user = "admin";
    final static String password = "abc123.";
    static BasicConnectionPool bcp;

    public MySQLDAOFactory() {
        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return bcp.getConnection();
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return bcp.releaseConnection(connection);
    }

    @Override
    public int getSize() {
        return bcp.getSize();
    }
    //add getUser, getURL....

    @Override
    public void shutdown() throws SQLException {
        bcp.shutdown();
    }

    @Override
    public AlumnoDAO getAlumnoDAO() {
        return new AlumnoDAO();
    }

    @Override
    public ProfesorDAO getProfesorDAO() {
        return new ProfesorDAO();
    }

    @Override
    public AsignaturaDAO getAsignaturaDAO() {
        return new AsignaturaDAO();
    }

    @Override
    public DetalleClaseDAO getDetalleClaseDAO() {
        return new DetalleClaseDAO();
    }
}
