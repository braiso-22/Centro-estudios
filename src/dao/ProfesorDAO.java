/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.AlumnoDAO.GETBYASIGNATURA;
import static dao.AlumnoDAO.GETBYCURSO;
import static dao.AlumnoDAO.GETBYNACIMIENTO;
import static dao.AlumnoDAO.GETBYPROFESOR;
import static dao.Dao.querys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import vo.Profesor;

/**
 *
 * @author brais.fernandezvazqu
 */
public class ProfesorDAO implements Dao {

    public final static int GETALL = 0, GETBYDNI = 1, GETBYNOMBRE = 2, GETBYAPELLIDO = 3;

    public ProfesorDAO() {
        querys.add("select * from profesor;"); //0
        querys.add("select * from profesor where dni = ?;"); //1
        querys.add("select * from profesor where nombre = ?;"); //2
        querys.add("select * from profesor where apellidos like ?;"); //3
        querys.add("select * from profesor where departamento = ?;"); //4
        querys.add("select * from profesor where sueldo= ?;"); //5
        querys.add("select dni, nombre, apellidos, curso, fecha_nacimiento from alumno_con_asignatura " + "where asignatura=?;");//6
        querys.add("select dni, nombre, apellidos, curso, fecha_nacimiento from alumno_con_profesor " + "where profesor=?;");//7
        querys.add("select * from alumno where fecha_nacimiento < DATE_SUB(now(),interval 18 YEAR);");// 8 alumnos mayores de edad

        inserts.add("insert into alumno(dni, nombre, apellidos, curso, fecha_nacimiento) values(?, ?, ?, ?, ?);");//0
    }

    @Override
    public List get(int queryInt, String id, Connection conn) {
        List<Profesor> lista = new ArrayList<>();
        try {
            String query = querys.get(queryInt);

            PreparedStatement ps = conn.prepareStatement(query);
            switch (queryInt) {
                case GETALL:
                    break;
                case GETBYDNI:
                case GETBYNOMBRE:
                case GETBYAPELLIDO:
                case GETBYCURSO:
                case GETBYASIGNATURA:
                case GETBYPROFESOR:
                    ps.setString(1, id);
                    break;
                case GETBYNACIMIENTO:
                    int year = Integer.valueOf(id);
                    ps.setInt(1, year);
                    break;
                default:
                    break;
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(formarProfesor(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private Profesor formarProfesor(ResultSet rs) {
        Profesor al = null;
        try {
            String dni = rs.getString(1);
            String nombre = rs.getString(2);
            String apellidos = rs.getString(3);
            String departamento = rs.getString(4);
            float sueldo = rs.getFloat(5);
            al = new Profesor(dni, nombre, apellidos, departamento, sueldo);

        } catch (SQLException e) {
            System.out.println("Error al formar el alumno " + e.getMessage());
        }
        return al;
    }

    @Override
    public void insertUsingFile(String file, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Object elemento, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateView(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getByProc(int query, String id, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
