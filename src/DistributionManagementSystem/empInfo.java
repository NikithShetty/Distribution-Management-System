/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistributionManagementSystem;

/**
 *
 * @author Nikith_Shetty
 */
public class empInfo {
    private int ID;
    private String Name;
    private String Address;
    private String Designation;
    private String PhoneNo;
    private int Salary;
    private String password;
    
    public empInfo(){
        Name = null;
        Address = null;
        Designation = null;
        PhoneNo = null;
        Salary = 0;
        password = null;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the Designation
     */
    public String getDesignation() {
        return Designation;
    }

    /**
     * @param Designation the Designation to set
     */
    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    /**
     * @return the PhoneNo
     */
    public String getPhoneNo() {
        return PhoneNo;
    }

    /**
     * @param PhoneNo the PhoneNo to set
     */
    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    /**
     * @return the Salary
     */
    public int getSalary() {
        return Salary;
    }

    /**
     * @param Salary the Salary to set
     */
    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }
}
