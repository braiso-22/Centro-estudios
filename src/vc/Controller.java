/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vc;

import dao.AlumnoDAO;
import factory.DAOFactory;
import java.util.List;
import vo.Alumno;
import java.sql.Connection;

/**
 *
 * @author brais.fernandezvazqu
 */
public class Controller {

    static List<Alumno> alumnos;
    static DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    static AlumnoDAO alumnoDAO = mySQLFactory.getAlumnoDAO();
    static View v = new View();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        v.showMessage("Cargando...");

        int opcion = -1;
        Connection conn = null;
        do {
            opcion = v.menuTablas();
            int opcion2 = -1;
            String output;
            do {
                output = new String();
                try {
                    conn = mySQLFactory.getConnection();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                switch (opcion) {

                    case 1:
                        opcion2 = v.menuAlumno();
                        output += alumno(opcion2, conn);
                        break;
                    case 0:
                        v.showMessage("Saliendo...");
                    default:;

                }
                mySQLFactory.releaseConnection(conn);
                v.showMessage(output);
            } while (opcion2 != 0 && opcion != 0);
        } while (opcion != 0);

    }

    public static String alumno(int opcion, Connection conn) {
        String output = "";
        String id;
        switch (opcion) {
            case 1:
                alumnos = alumnoDAO.get(AlumnoDAO.GETALL, "", conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
                break;
            case 2:
                id = v.showMessageString("Introduce el DNI");
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYDNI, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
                break;
            case 3:
                id = v.showMessageString("Introduce el nombre");
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYNOMBRE, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
            case 4:
                id = v.showMessageString("Introduce los apellidos");
                id = "%" + id + "%";
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYAPELLIDO, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
                break;
            case 5:
                id = v.showMessageString("Introduce el curso");
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYCURSO, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
                break;
            case 6:
                id = v.showMessageString("Introduce el año");
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYNACIMIENTO, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
                break;
            case 7:
                id = v.showMessageString("Introduce el nombre de asignatura:");
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYASIGNATURA, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
            case 8:
                id = v.showMessageString("Introduce el nombre del profesor:");
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYPROFESOR, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
            case 9:
                id = v.showMessageString("Introduce el archivo:");
                alumnoDAO.insertUsingFile(id, conn);
                
            case 0:
                break;
            default:

        }
        return output;
    }

}
