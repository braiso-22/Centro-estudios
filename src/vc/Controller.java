/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vc;

import dao.AlumnoDAO;
import dao.AsignaturaDAO;
import dao.ProfesorDAO;
import factory.DAOFactory;
import java.util.List;
import vo.Alumno;
import java.sql.Connection;
import java.time.LocalDate;
import vo.Asignatura;
import vo.Profesor;

/**
 *
 * @author brais.fernandezvazqu
 */
public class Controller {

    static List<Alumno> alumnos;
    static List<Profesor> profesores;
    static List<Asignatura> asignaturas;
    static DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    static AlumnoDAO alumnoDAO = mySQLFactory.getAlumnoDAO();
    static ProfesorDAO profesorDAO = mySQLFactory.getProfesorDAO();
    static AsignaturaDAO asignaturaDAO = mySQLFactory.getAsignaturaDAO();
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
                    case 2:
                        opcion2 = v.menuProfesor();
                        output += profesor(opcion2, conn);
                        break;
                    case 3:
                        opcion2 = v.menuAsignatura();
                        output += asignatura(opcion2, conn);
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
                output = proccesAlumnos("Introduce el DNI", AlumnoDAO.GETBYDNI, conn);
                break;
            case 3:
                output = proccesAlumnos("Introduce el nombre", AlumnoDAO.GETBYNOMBRE, conn);
                break;
            case 4:

                id = v.showMessageString("Introduce los apellidos");
                id = "%" + id + "%";
                alumnos = alumnoDAO.get(AlumnoDAO.GETBYAPELLIDO, id, conn);
                for (Alumno al : alumnos) {
                    output += al.toString();
                }
                break;
            case 5:
                output = proccesAlumnos("Introduce el curso", AlumnoDAO.GETBYCURSO, conn);
                break;
            case 6:
                output = proccesAlumnos("Introduce el año", AlumnoDAO.GETBYNACIMIENTO, conn);
                break;
            case 7:
                output = proccesAlumnos("Introduce el nombre de asignatura:", AlumnoDAO.GETBYASIGNATURA, conn);
                break;
            case 8:
                output = proccesAlumnos("Introduce el DNI del profesor:", AlumnoDAO.GETBYPROFESOR, conn);
                break;
            case 9:
                id = v.showMessageString("Introduce el archivo:");
                alumnoDAO.insertUsingFile(id, conn);
                break;
            case 10:
                String dni,
                 nombre,
                 apellido,
                 curso;
                LocalDate fecha;

                dni = v.showMessageString("Introduce el dni");
                nombre = v.showMessageString("Introduce el nombre");
                apellido = v.showMessageString("introduce los apellidos");
                curso = v.showMessageString("Introduce el curso");

                try {
                    fecha = LocalDate.parse(v.showMessageString("Introduce la fecha de nacimiento YYYY-MM-DD"));
                    alumnoDAO.add(new Alumno(dni, nombre, apellido, curso, fecha), conn);
                } catch (Exception e) {
                    System.out.println("No se pudo añadir el alumno" + e.getMessage());
                }
                break;
            case 0:
                break;
            default:

        }
        return output;
    }

    private static String proccesAlumnos(String mensaje, int num, Connection conn) {
        String id, output = "";
        id = v.showMessageString(mensaje);
        alumnos = alumnoDAO.get(num, id, conn);
        for (Alumno al : alumnos) {
            output += al.toString();
        }
        return output;
    }

    private static String profesor(int opcion, Connection conn) {
        String output = "";
        String id;
        switch (opcion) {
            case 1:
                profesores = profesorDAO.get(ProfesorDAO.GETALL, "", conn);
                for (Profesor prof : profesores) {
                    output += prof.toString();
                }

                break;
            case 2:
                output = processProfesores("Introduce el DNI", ProfesorDAO.GETBYDNI, conn);
                break;
            case 3:
                output = processProfesores("Introduce el nombre", ProfesorDAO.GETBYNOMBRE, conn);
                break;
            case 4:
                id = v.showMessageString("Introduce los apellidos");
                id = "%" + id + "%";
                profesores = profesorDAO.get(ProfesorDAO.GETBYAPELLIDO, id, conn);
                for (Profesor prof : profesores) {
                    output += prof.toString();
                }
                break;
            case 5:
                output = processProfesores("Introduce el departamento", ProfesorDAO.GETBYDEPARTAMENTO, conn);
                break;
            case 6:
                output = processProfesores("Introduce el sueldo", ProfesorDAO.GETBYSUELDO, conn);
                break;
            case 7:
                output = processProfesores("Introduce la asignatura", ProfesorDAO.GETBYASIGNATURA, conn);
                break;
            case 8:
                output = processProfesores("Introduce el DNI del alumno", ProfesorDAO.GETBYALUMNO, conn);
                break;
            case 9:
                id = v.showMessageString("Introduce el archivo");
                profesorDAO.insertUsingFile(id, conn);
                break;
            default:

        }
        return output;

    }

    private static String processProfesores(String mensaje, int num, Connection conn) {
        String id = "", output = "";
        id = v.showMessageString(mensaje);
        profesores = profesorDAO.get(num, id, conn);
        for (Profesor prof : profesores) {
            output += prof.toString();
        }
        return output;
    }

    private static String asignatura(int opcion, Connection conn) {
        String output = "";
        String id;
        switch (opcion) {
            case 1:
                asignaturas = asignaturaDAO.get(AsignaturaDAO.GETALL, "", conn);
                for (Asignatura as : asignaturas) {
                    output += as;
                }
                break;
            case 2:
                proccesAsignatura("Escribe el codigo", AsignaturaDAO.GETBYCODIGO, conn);
                break;
            case 3:
                proccesAsignatura("Escribe el nombre", AsignaturaDAO.GETBYNOMBRE, conn);
                break;
            case 4:
                proccesAsignatura("Escribe el dni del alumno", AsignaturaDAO.GETBYALUMNO, conn);
                break;
            case 5:
                proccesAsignatura("Escribe el dni del profesor", AsignaturaDAO.GETBYPROFESOR, conn);
                break;
            case 6:
                id = v.showMessageString("Introduce el archivo");
                asignaturaDAO.insertUsingFile(id, conn);
                break;
            case 7:
                String codigo = v.showMessageString("introduce el codigo");
                String nombre = v.showMessageString("Introduce el nombre");
                asignaturaDAO.add(new Asignatura(codigo, nombre), conn);
                break;
            default:
                break;
        }
        return output;
    }

    private static String proccesAsignatura(String mensaje, int num, Connection conn) {
        String id = "", output = "";
        id = v.showMessageString(mensaje);
        asignaturas = asignaturaDAO.get(num, id, conn);
        for (Asignatura asig : asignaturas) {
            output += asig.toString();
        }
        return output;
    }
}
