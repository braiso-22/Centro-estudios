/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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

    /*
        para hacer selects simples en la misma tabla
     */
    List<T> get(int query, String id, Connection conn);

    /*
        para usar procedimientos almacenados
     */
    List<T> getByProc(int query, String id, Connection conn);

    /*
        para insertar desde un archivo
     */
    void insertUsingFile(Connection conn);
    
    /*
        actualizar la vista de SQL metodo que se usa cada vez que se inserta datos
    */
    void updateView(Connection conn);

}
