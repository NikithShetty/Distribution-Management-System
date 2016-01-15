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
public class DBAState {
    private int employeeID;
    private String employee_Name;
    private String employee_Address;
    private String employee_Phone_No;
    
    public DBAState(int id, String name, String address, String phoneNo){
        employeeID = id;
        employee_Name = name;
        employee_Address = address;
        employee_Phone_No = phoneNo;
    }
    public int getID(){
        return employeeID;
    }
    public String getName(){
        return employee_Name;
    }
    public String getAddress(){
        return employee_Address;
    }
    public String getPhoneNo(){
        return employee_Phone_No;
    }
    @Override
    public String toString(){
        return "DBAID = " + employeeID + "DBA_Name = " + employee_Name;
    }
}
