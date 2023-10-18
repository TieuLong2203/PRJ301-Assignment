/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Campus;
import entity.Feature;
import entity.Role;
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
public class AccountDBContext extends DBContext<Account> {

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(Account entity) {
        try {
            String sqlGetAccount = "SELECT email, [password], campus_id FROM account \n"
                    + "WHERE email = ? AND [password] = ? AND campus_id = ?";
            PreparedStatement stm = connection.prepareStatement(sqlGetAccount);
            stm.setString(1, entity.getEmail());
            stm.setString(2, entity.getPassword());
            stm.setString(3, entity.getCampus().getCampusId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account loggedAccount = new Account();
                loggedAccount.setEmail(rs.getString("email"));
                Campus campus = new Campus();
                campus.setCampusId(rs.getString("campus_id"));
                loggedAccount.setCampus(campus);
                return loggedAccount;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    public ArrayList<Role> getRoleAndFeature(String email, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.role_id, r.role_name, f.feature_id, f.url FROM account a\n"
                    + "INNER JOIN account_role ar ON ar.email = a.email\n"
                    + "INNER JOIN [role] r ON r.role_id = ar.role_id\n"
                    + "INNER JOIN role_feature rf ON rf.role_id = r.role_id\n"
                    + "INNER JOIN feature f ON f.feature_id = rf.feature_id\n"
                    + "WHERE a.email = ? AND f.[url] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));

                Feature feature = new Feature();
                feature.setFeatureId(rs.getInt("feature_id"));
                feature.setUrl(rs.getString("url"));

                role.getFeatures().add(feature);
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
}
