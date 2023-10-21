/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Slot;
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
public class SlotDbContext extends DBContext<Slot>{

    @Override
    public ArrayList<Slot> list() {
        ArrayList<Slot> slots = new ArrayList<>();
        try {
            String sqlSlot = "SELECT slot_id, start_time, end_time FROM slot";
            PreparedStatement stm = connection.prepareStatement(sqlSlot);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Slot slot = new Slot();
                slot.setSlotId(rs.getInt("slot_id"));
                slot.setStartTime(rs.getTime("start_time").toString());
                slot.setEndTime(rs.getTime("end_time").toString());
                slots.add(slot);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotDbContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slots;
    }

    @Override
    public void update(Slot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Slot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Slot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Slot get(Slot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
