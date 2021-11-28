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
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.DetalleClase;

/**
 *
 * @author brais.fernandezvazqu
 */
public class DetalleClaseDAO implements Dao {

    public static final int GETALL = 0;
    private static final String GETALLS = "select * from detalle_clase;";
    private static final String INSERT = "insert into detalle_clase(alumno, profesor, asignatura) values(?, ?, ?);";

    @Override
    public List get(int queryInt, String id, Connection conn) {
        List<DetalleClase> lista = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(GETALLS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new DetalleClase(rs.getString(1), rs.getString(2), rs.getString(3)));
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
        csv = new File(file);
        //csv = new File("src/batch/detalleClases.csv");
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
            System.out.println("No se pudo añadir la nueva matricula:"+sqlE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Object elemento, Connection conn) {
        DetalleClase matricula = (DetalleClase) elemento;

        try {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, matricula.getAlumno());
            ps.setString(2, matricula.getProfesor());
            ps.setString(3, matricula.getAsignatura());
            ps.executeUpdate();
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
