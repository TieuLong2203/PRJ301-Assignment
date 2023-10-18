/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Campus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luulo
 */
public class CampusDBContext extends DBContext<Campus>{

    @Override
    public ArrayList<Campus> list() {
        ArrayList<Campus> campuses = new ArrayList<>();
        try {
            String sqlCampus = "SELECT campus_id, [address] FROM campus";
            PreparedStatement stm = connection.prepareStatement(sqlCampus);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Campus campus = new Campus();
                campus.setCampusId(rs.getString("campus_id"));
                campus.setCampusAddress(rs.getString("address"));
                campuses.add(campus);
            }
        } catch (SQLException ex) {            
            Logger.getLogger(CampusDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campuses;
    }

    @Override
    public void update(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Campus get(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
