/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author luulo
 */
public class Campus extends BaseEntity{

    private String campusId;
    private String campusAddress;

    public Campus() {
    }

    public Campus(String campusId, String campusAddress) {
        this.campusId = campusId;
        this.campusAddress = campusAddress;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public String getCampusAddress() {
        return campusAddress;
    }

    public void setCampusAddress(String campusAddress) {
        this.campusAddress = campusAddress;
    }

}
