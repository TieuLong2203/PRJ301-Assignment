/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BaseEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luulo
 * @param <T>
 */
public abstract class DBContext<T extends BaseEntity> {

    protected Connection connection;

    DBContext() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment_1";
            String user = "sa";
            String password = "longdongtao2002";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract ArrayList<T> list();

    public abstract void update(T entity);

    public abstract void delete(T entity);

    public abstract void insert(T entity);

    public abstract T get(T entity);
}
