/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistributionManagementSystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Nikith_Shetty
 */
public class OrderInfo {
    private int orderID;
    private int itemID;
    private String name;
    private String streetAddress;
    private String Landmark;
    private String City;
    private String State;
    private int Pincode;
    private String phoneNo;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private Date date_of_order = new Date();
    private Date date_of_delivery = new Date();
    private Calendar calendar = Calendar.getInstance();
    
    public void updateDate (){
        calendar.setTime(date_of_order);
        calendar.add(calendar.DATE, 5);
        date_of_delivery = calendar.getTime();
    }

    /**
     * @return the itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the Landmark
     */
    public String getLandmark() {
        return Landmark;
    }

    /**
     * @param Landmark the Landmark to set
     */
    public void setLandmark(String Landmark) {
        this.Landmark = Landmark;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return the State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(String State) {
        this.State = State;
    }

    /**
     * @return the Pincode
     */
    public int getPincode() {
        return Pincode;
    }

    /**
     * @param Pincode the Pincode to set
     */
    public void setPincode(int Pincode) {
        this.Pincode = Pincode;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the date_of_order
     */
    public java.sql.Date getDate_of_order() {
        return new java.sql.Date(date_of_order.getTime());
    }

    /**
     * @return the date_of_delivery
     */
    public java.sql.Date getDate_of_delivery() {
        return new java.sql.Date(date_of_delivery.getTime());
    }

    /**
     * @param date_of_delivery the date_of_delivery to set
     */
    public void setDate_of_delivery(Date date_of_delivery) {
        this.date_of_delivery = date_of_delivery;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
