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
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vo.Asignatura;

/**
 *
 * @author brais.fernandezvazqu
 */
public class AsignaturaDAO implements Dao {

    ArrayList<String> querys = new ArrayList<>();
    public final static int GETALL = 0, GETBYCODIGO = 1, GETBYNOMBRE = 2, GETBYALUMNO = 3, GETBYPROFESOR = 4;
    public final static String INSERT = "insert into asignatura(codigo, nombre) values(?, ?);";

    public AsignaturaDAO() {
        querys.add("select * from  asignatura");
        querys.add("select * from asignatura where codigo = ?");
        querys.add("select * from asignatura where nombre = ?");
        querys.add("select * from asignatura_con_alumno where alumno = ?");
        querys.add("select * from asignatura_con_profesor where profesor = ?");

    }

    @Override
    public List<Asignatura> get(int queryInt, String id, Connection conn) {
        List<Asignatura> lista = new ArrayList<>();
        try {
            String query = querys.get(queryInt);
            PreparedStatement ps = conn.prepareStatement(query);

            if (queryInt != 0) {
                ps.setString(1, id);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Asignatura(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public void insertUsingFile(String file, Connection conn) {
        File csv;
        //csv = new File(file);
        csv = new File("src/batch/asignaturas.csv");
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
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Object elemento, Connection conn) {
        Asignatura asig = (Asignatura) elemento;

        try {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, asig.getCodigo());
            ps.setString(2, asig.getNombre());
            ps.executeUpdate();
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
