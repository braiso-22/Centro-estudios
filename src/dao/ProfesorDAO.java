/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vo.Profesor;

/**
 *
 * @author brais.fernandezvazqu
 */
public class ProfesorDAO implements Dao {

    ArrayList<String> querys = new ArrayList<>();

    public final static int GETALL = 0, GETBYDNI = 1, GETBYNOMBRE = 2, GETBYAPELLIDO = 3, GETBYDEPARTAMENTO = 4, GETBYSUELDO = 5, GETBYASIGNATURA = 6, GETBYALUMNO = 7;
    public final static int INSERTBYFILE = 0;
    private final static String INSERT = "insert into profesor(dni, nombre, apellidos, departamento, sueldo) values(?, ?, ?, ?, ?);";

    public ProfesorDAO() {
        querys.add("select * from profesor;"); //0
        querys.add("select * from profesor where dni = ?;"); //1
        querys.add("select * from profesor where nombre = ?;"); //2
        querys.add("select * from profesor where apellidos like ?;"); //3
        querys.add("select * from profesor where departamento = ?;"); //4
        querys.add("select * from profesor where sueldo > ?;"); //5
        querys.add("select dni, nombre, apellidos, departamento, sueldo from alumno_con_asignatura " + "where asignatura=?;");//6
        querys.add("select dni, nombre, apellidos, curso, fecha_nacimiento from alumno_con_profesor " + "where profesor=?;");//7
        querys.add("select * from alumno where fecha_nacimiento < DATE_SUB(now(),interval 18 YEAR);");// 8 alumnos mayores de edad

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
                    ps.setString(1, id);
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
            System.out.println("Error al formar el profesor " + e.getMessage());
        }
        return al;
    }

    @Override
    public void insertUsingFile(String file, Connection conn) {
        File csv;
        //csv = new File(file);
        csv = new File("src/batch/profesores.csv");
        String cadena;

        try {
            FileReader fr = new FileReader(csv);
            BufferedReader bfr = new BufferedReader(fr);
            PreparedStatement ps = conn.prepareStatement(INSERT);

            while ((cadena = bfr.readLine()) != null) {
                String[] cadenas = cadena.split(";");
                int i = 1;
                for (String a : cadenas) {
                    switch (i) {
                        default:
                            ps.setString(i, a);
                            break;
                    }
                    i++;
                }
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (IOException ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Object elemento, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
