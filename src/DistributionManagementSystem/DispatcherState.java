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
public class DispatcherState {
    private int employeeID;
    private String employee_Name;
    private String employee_Address;
    private String employee_Phone_No;
    
    public DispatcherState(int id, String name, String address, String phoneNo){
        employeeID = id;
        employee_Name = name;
        employee_Address = address;
        employee_Phone_No = phoneNo;
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the employee_Name
     */
    public String getEmployee_Name() {
        return employee_Name;
    }

    /**
     * @param employee_Name the employee_Name to set
     */
    public void setEmployee_Name(String employee_Name) {
        this.employee_Name = employee_Name;
    }

    /**
     * @return the employee_Address
     */
    public String getEmployee_Address() {
        return employee_Address;
    }

    /**
     * @param employee_Address the employee_Address to set
     */
    public void setEmployee_Address(String employee_Address) {
        this.employee_Address = employee_Address;
    }

    /**
     * @return the employee_Phone_No
     */
    public String getEmployee_Phone_No() {
        return employee_Phone_No;
    }

    /**
     * @param employee_Phone_No the employee_Phone_No to set
     */
    public void setEmployee_Phone_No(String employee_Phone_No) {
        this.employee_Phone_No = employee_Phone_No;
    }
    
    public String toString(){
        return "DispatcherID = " + employeeID + "Dispatcher_Name = " + employee_Name;
    }
    
}
