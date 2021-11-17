/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brais.fernandezvazqu
 * @param <T>
 */
public interface Dao<T> {

    ArrayList<String> querys = new ArrayList<>();
    ArrayList<String> views = new ArrayList<>();
    ArrayList<String> inserts = new ArrayList<>();
    ArrayList<String> procedures = new ArrayList<>();

    /**
     * para hacer selects simples en la misma tabla
     *
     * @param query
     * @param id
     * @param conn
     * @return
     */
    List<T> get(int query, String id, Connection conn);

    /**
     * para usar procedimientos almacenados
     *
     * @param query
     * @param id
     * @param conn
     * @return
     */
    List<T> getByProc(int query, String id, Connection conn);

    /**
     * para insertar desde un archivo
     *
     * @param file
     * @param conn
     */
    void insertUsingFile(File file, Connection conn);

    /**
     * para insertar uno por uno
     *
     * @param elemento
     * @param conn
     */
    void add(T elemento, Connection conn);

    /**
     * actualizar la vista de SQL metodo que se usa cada vez que se inserta
     * datos
     *
     * @param conn
     */
    void updateView(Connection conn);

}
