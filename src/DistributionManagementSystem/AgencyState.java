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
public class AgencyState {
    
    private int AgencyID;
    private String Agency_Name;
    private String Warehouse_Address;
    private int phoneNo;
    
    public AgencyState(int ID,String name,String address,int phoneNo){
        AgencyID = ID;
        Agency_Name = name;
        Warehouse_Address = address;
        this.phoneNo = phoneNo;
    }
    public int getID(){
        return AgencyID;
    }
    public String getName(){
        return Agency_Name;
    }
    public String getWarehouseAddress(){
        return Warehouse_Address;
    }
    public int getPhoneNo(){
        return phoneNo;
    }
    public void update(int ID,String name,String address,int phoneNo){
        AgencyID = ID;
        Agency_Name = name;
        Warehouse_Address = address;
        this.phoneNo = phoneNo;
    }
    public String toString(){
        return "AgencyID = " + AgencyID + "\nAgency_Name = " + Agency_Name;
    }
}
