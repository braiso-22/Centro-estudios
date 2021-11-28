/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author brais.fernandezvazqu
 * @param <T>
 */
public interface Dao<T> {

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
     * para insertar desde un archivo
     *
     * @param file
     * @param conn
     */
    void insertUsingFile(String file, Connection conn);

    /**
     * para insertar uno por uno
     *
     * @param elemento
     * @param conn
     */
    void add(T elemento, Connection conn);
}
