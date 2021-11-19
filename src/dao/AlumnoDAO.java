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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import vo.Alumno;

/**
 *
 * @author brais.fernandezvazqu
 */
public class AlumnoDAO implements Dao {

    public final static int GETALL = 0, GETBYDNI = 1, GETBYNOMBRE = 2, GETBYAPELLIDO = 3, GETBYCURSO = 4, GETBYNACIMIENTO = 5, GETBYASIGNATURA = 6; //selects
    public final static int INSERTBYFILE = 0;//inserts
    public final static int UPDATEBYASIGNATURA = 0, GETBYPROFESOR = 1;

    public AlumnoDAO() {
        querys.add("select * from alumno;"); //0
        querys.add("select * from alumno where dni = ?;"); //1
        querys.add("select * from alumno where nombre = ?;"); //2
        querys.add("select * from alumno where apellidos like ?;"); //3
        querys.add("select * from alumno where curso = ?;"); //4
        querys.add("select * from alumno where year(fecha_nacimiento)= ?;"); //5
        querys.add("select * from alumno_con_asignatura " + "where asi.nombre=?;");//6
        querys.add("select * from alumno_con_profesor " + "where p.nombre=?;");//7
        querys.add("select * from alumno where fecha_nacimiento < DATE_SUB(now(),interval 18 YEAR);");// 8 alumnos mayores de edad

        inserts.add("insert into alumno(dni, nombre, apellidos, curso, fecha_nacimiento) values(?, ?, ?, ?, ?);");//0

        views.add("create or replace view alumno_con_asignatura as select a.dni, a.nombre, a.apellidos, a.curso, a.fecha_nacimiento "
                + "from alumno as a inner join detalle_clase as dc on a.dni = dc.alumno "
                + "inner join asignatura as asi on dc.asignatura = asi.codigo;");//0
        views.add("create or replace view alumno_con_profesor as select a.dni, a.nombre, a.apellidos, a.curso, a.fecha_nacimiento "
                + "from alumno as a inner join detalle_clase as dc on a.dni = dc.alumno "
                + "inner join profesor as p on dc.profesor = p.dni;");//1
    }

    @Override
    public List<Alumno> get(int queryInt, String id, Connection conn) {
        List<Alumno> lista = new ArrayList<>();
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
                    ps.setString(1, id);
                    break;
                case GETBYNACIMIENTO:
                    int year=Integer.valueOf(id);
                    ps.setInt(1, year);
                    break;

                default:
                    break;
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(formarAlumno(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private Alumno formarAlumno(ResultSet rs) {
        Alumno al = null;
        try {
            String dni = rs.getString(1);
            String nombre = rs.getString(2);
            String apellidos = rs.getString(3);
            String curso = rs.getString(4);
            LocalDate fechaNacimiento = LocalDate.parse(rs.getDate(5).toString());
            al = new Alumno(dni, nombre, apellidos, curso, fechaNacimiento);

        } catch (SQLException e) {
            System.out.println("Error al formar el alumno " + e.getMessage());
        }
        return al;
    }

    @Override
    public void insertUsingFile(File f, Connection conn) {
        File csv;
        csv = new File("src/batch/alumnos.csv");
        String cadena;

        try {
            FileReader fr = new FileReader(csv);
            BufferedReader bfr = new BufferedReader(fr);
            PreparedStatement ps = conn.prepareStatement(inserts.get(INSERTBYFILE));

            while ((cadena = bfr.readLine()) != null) {
                String[] cadenas = cadena.split(";");
                int i = 1;
                for (String a : cadenas) {
                    switch (i) {
                        case 1:
                        case 4:
                        case 8:
                            ps.setInt(i, Integer.parseInt(a));
                            break;
                        case 5:
                            ps.setDate(i, Date.valueOf(a));
                            break;
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
    public void add(Object elemento, Connection conn
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getByProc(int query, String id, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateView(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
