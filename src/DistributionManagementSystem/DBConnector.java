/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistributionManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nikith_Shetty
 */
public class DBConnector {
    
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/dms";
    static String user = "root";
    static String password = "1304mysql";
    Connection conn;
    Statement stmt = null;
    ResultSet res = null;
    
    //classes to store state of application login
    AgencyState agency = null;
    DBAState DBA = null;
    ManagerState Manager = null;
    DispatcherState Dispatcher = null;
    OrderInfo OrderInfo = null;
    OrderInfo info = null;
    static empInfo emp = null;

    public DBConnector (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting...");
            conn = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Connected.");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            conn = null;
        } 
    }
    
    public DBConnector (String url,String usr,String pass){
        DB_URL = url;
        user = usr;
        password = pass;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting...");
            conn = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Connected.");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            conn = null;
        } 
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public boolean customerLogin(String usrname, String password){
        try{
            stmt = conn.createStatement();
            String sql = "select * from agencydetails , agencylogin where `AgencyID`=`AgencyDetails_AgencyID` and `Agency_Name`=? and password=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, usrname);
            st.setString(2, password);
            System.out.println("Agency : " + usrname + " " + password);
            res = st.executeQuery();
            if(res.next()){
                agency = new AgencyState(res.getInt("agencyid"), usrname, res.getString("warehouse_address"), res.getInt("Phone No."));
                System.out.println(agency.toString());
                info = new OrderInfo();
                info.setOrderID(getOrderID());
                return true;
            }
        } catch (SQLException e){
        } 
        return false;
    }
    
    public boolean DBALogin(String usrname, String password) {
        try{
            stmt = conn.createStatement();
            String sql = "select * from employeedetails, employeelogin where employeeID=EmployeeDetails_employeeID and employee_Name=? and Emppassword=? and designation='DBA'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, usrname);
            st.setString(2, password);
            System.out.println("DBA : " + usrname + " " + password);
            res = st.executeQuery();
            if(res.next()){
               DBA = new DBAState(res.getInt("employeeid"), usrname, res.getString("employee_address"), res.getString("employee_phone_no"));
               System.out.println(DBA.toString());
               return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean ManagerLogin(String usrname, String password) {
        try{
            stmt = conn.createStatement();
            String sql = "select * from employeedetails, employeelogin where employeeID=EmployeeDetails_employeeID and employee_Name=? and Emppassword=? and designation='Manager'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, usrname);
            st.setString(2, password);
            System.out.println("Manager : " + usrname + " " + password);
            res = st.executeQuery();
            if(res.next()){
               Manager = new ManagerState(res.getInt("employeeid"), usrname, res.getString("employee_address"), res.getString("employee_phone_no"));
               System.out.println(Manager.toString());
               return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean DispatcherLogin(String usrname, String password) {
        try{
            stmt = conn.createStatement();
            String sql = "select * from employeedetails, employeelogin where employeeID=EmployeeDetails_employeeID and employee_Name=? and Emppassword=? and designation='Dispatcher'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, usrname);
            st.setString(2, password);
            System.out.println("Dispatcher : " + usrname + " " + password);
            res = st.executeQuery();
            if(res.next()){
               Dispatcher = new DispatcherState(res.getInt("employeeid"), usrname, res.getString("employee_address"), res.getString("employee_phone_no"));
               System.out.println(Dispatcher.toString());
               return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean populateOrderDetails(DefaultTableModel model){
        try{
            System.out.println("Inside method!!!");
            stmt = conn.createStatement();
            String sql = "select * from orderdetails where agencydetails_agencyid=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, String.valueOf(agency.getID()));
            res = st.executeQuery();
            System.out.println("Executed statement!!!");
            model.setRowCount(0);
            while(res.next()){
                System.out.println(res.getInt("orderID") + " " + res.getString("customer_name"));
                model.addRow(new Object[]{res.getInt("orderID"),res.getInt("itemID"),res.getString("customer_Name"),res.getString("delivery_state")});
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean populateDispatchOrderDetails(DefaultTableModel model){
        try{
            System.out.println("Inside method!!!");
            stmt = conn.createStatement();
            String sql = "select * from orderdetails o,employeedetails e,dispatch d where o.orderID=d.orderID and o.itemID=d.itemID and e.employeeID=d.DispatchCrewID and e.employeeID=" + Dispatcher.getEmployeeID() + "";
            res = stmt.executeQuery(sql);
            System.out.println("Executed statement!!!");
            model.setRowCount(0);
            while(res.next()){
                System.out.println(res.getInt("orderID") + " " + res.getString("customer_name"));
                model.addRow(new Object[]{res.getInt("orderID"),res.getInt("itemID"),res.getString("customer_Name"),res.getString("delivery_state"),res.getString("deliver_by_date")});
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean populateEmployeeTables(DefaultTableModel empModel){
        try{
            System.out.println("Inside method!!!");
            stmt = conn.createStatement();
            String sql = "select * from employeedetails";
            PreparedStatement st = conn.prepareStatement(sql);
            res = st.executeQuery();
            System.out.println("Executed statement!!!");
            empModel.setRowCount(0);
            while(res.next()){
                System.out.println(res.getInt("employeeID") + " " + res.getString("employee_name"));
                empModel.addRow(new Object[]{res.getInt("employeeID"),res.getString("employee_Name"),
                    res.getString("designation"),res.getInt("salary"),res.getString("employee_Phone_No"),
                    res.getString("employee_Address")});
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean populateAccountsTables(DefaultTableModel accountModel){
        try{
            System.out.println("Inside method!!!");
            stmt = conn.createStatement();
            String sql = "select * from orderdetails";
            PreparedStatement st = conn.prepareStatement(sql);
            res = st.executeQuery();
            System.out.println("Executed statement!!!");
            accountModel.setRowCount(0);
            while(res.next()){
                System.out.println(res.getInt("orderID") + " " + res.getInt("itemID"));
                accountModel.addRow(new Object[]{res.getInt("orderID"),res.getInt("itemID"),res.getString("customer_Name"),res.getString("delivery_state")});
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean insertOrderTable (){
        if(info == null){
            JOptionPane.showMessageDialog(null, "Error : Null value OrderInfo Class");
            return false;
        }
        try {
            int executeUpdate1,executeUpdate2,executeUpdate3;
            ArrayList array;
            array = new ArrayList();
            info.updateDate();
            stmt = conn.createStatement();
            String sql = "INSERT INTO dms.orderdetails (`orderID`, `itemID`, `customer_Name`, date_of_order, deliver_by_date, delivery_state, `AgencyDetails_AgencyID`)" +
                    " VALUES (" + info.getOrderID() +", " + info.getItemID() + ", '" + info.getName() + "', '" +
                    info.getDate_of_order() + "', '" + info.getDate_of_delivery() + "', 'accepted', " + 
                    agency.getID() + ");";
            executeUpdate1 = stmt.executeUpdate(sql);
            sql = "INSERT INTO dms.order_deliveryaddress (`orderID`, `itemID`, `streetAddress`, `Landmark`, `City`, `State`, `Pincode`, `PhoneNo`) \n" +
                    " VALUES (" + info.getOrderID() + "," + info.getItemID() + ", '" + info.getStreetAddress() + "', '" + info.getLandmark() + 
                    "', '" + info.getCity() + "', '" + info.getState() + "', " + info.getPincode() + 
                    ", '" + info.getPhoneNo() + "')";
            executeUpdate1 = stmt.executeUpdate(sql);
            sql = "UPDATE orderid SET value=" + info.getOrderID() + ";";
            executeUpdate2 = stmt.executeUpdate(sql);
            sql = "select * from employeedetails where designation='Dispatcher'";
            res = stmt.executeQuery(sql);
            System.out.println("Code point : Executed query");
            while(res.next()){
                array.add(res.getInt("employeeID"));
            }
            System.out.println("Code point : added array values");
            int index = new Random().nextInt(array.size());
            int dispatcherID = Integer.valueOf(array.get(index).toString());
            sql = "INSERT INTO dms.`dispatch` (`orderID`, `itemID`, `DispatchCrewID`) VALUES (" +
                    info.getOrderID() + "," + info.getItemID() + "," + dispatcherID + ");";
            System.out.println("Code point : Executed insert query");
            executeUpdate3 = stmt.executeUpdate(sql);
            if(executeUpdate1 == 1 && executeUpdate2 == 1 && executeUpdate3 == 1){
                JOptionPane.showMessageDialog(null, "Update Successfull");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean insertEmployeeTable (String inp_password){
        if(emp == null){
            JOptionPane.showMessageDialog(null, "Error : Null value EmployeeInfo Class");
            return false;
        }
        try {
            int executeUpdate;
            String sql = "INSERT INTO dms.employeedetails (`employee_Name`, `employee_Address`, designation, `employee_Phone_No`, `Salary`)" +
                    " VALUES ( '" + emp.getName() + "' , '" + emp.getAddress() + "','" + emp.getDesignation() + 
                    "','" + emp.getPhoneNo() + "', " + emp.getSalary() + ")";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            executeUpdate = st.executeUpdate();
            executeUpdate = 0;
            res = st.getGeneratedKeys();
            res.last();
            emp.setID(res.getInt(1));
            System.out.println("Generated ID = " + emp.getID());
            System.out.println("Password = " + inp_password);
            if(inp_password!=null){
                sql = "";
                sql = "INSERT INTO dms.employeelogin (`Emppassword`, `EmployeeDetails_employeeID`)" +
                     " VALUES ('" + inp_password + "','" + emp.getID() + "')";
                executeUpdate = stmt.executeUpdate(sql);
            }
            if(executeUpdate == 1){
                JOptionPane.showMessageDialog(null, "Update Successfull");
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public void runQuery(String query , JTextArea result_text , JTable result_table){
        
        ResultSetMetaData metadata;
        DefaultTableModel model = (DefaultTableModel)result_table.getModel();
        System.out.println(query);
        model.setRowCount(0);
        model.setColumnCount(0);
        try{
            //Run Query
            stmt = conn.createStatement();
            String qArray[] = query.split(" ");
            if(!qArray[0].toLowerCase().matches("create|alter|update|drop|rename|insert|delete")){
                PreparedStatement st = conn.prepareStatement(query);
                res = st.executeQuery();
                //Strip query
                metadata = res.getMetaData();
                String cols[] = new String[metadata.getColumnCount()];
                model.setColumnCount(cols.length);
                System.out.println("Code Point 1");
                for(int i=0;i< cols.length;i++)
                {
                    System.out.println("Code Point 2");
                    cols[i]= metadata.getColumnLabel(i+1);
                    System.out.println("Code Point 3");
                    result_table.getColumnModel().getColumn(i).setHeaderValue(cols[i]);
                    System.out.println("Code Point 4");
                }
                while(res.next()){
                    Object data[] = new Object[cols.length];
                    System.out.println("Code Point 5");
                    for(int i=0;i<data.length;i++){
                        data[i] = res.getObject(i+1);
                        System.out.println("Code Point 6");
                 }
                    model.addRow(data);
                    System.out.println("Code Point 7");
                }
                result_table.setVisible(true);
                result_text.setVisible(false);
                System.out.println("Code Point 8");
            }else{
               stmt = conn.createStatement();
                int executeUpdate = stmt.executeUpdate(query);
                if(executeUpdate==1){
                   //return true;
                   result_text.setText("Operation Completed Successfully!!");
                   result_table.setVisible(false);
                   result_text.setVisible(true);
                } 
            }
        }catch(Exception e){
            e.printStackTrace();
            result_table.setVisible(false);
            result_text.setVisible(true);
            result_text.setText("Error!!!" + "\n\n" + e.getMessage() + " " );
        }
    }
    
    public void searchEmp (int empID){
        try{
            emp = new empInfo();
            System.out.println("Code point 1 ID = " + empID);
            String sql = "select * from employeedetails where employeeID='" + empID + "';";
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            System.out.println("Code point : executed query");
            while(res.next()){
                emp.setName(res.getString("employee_Name"));
                emp.setAddress(res.getString("employee_Address"));
                emp.setDesignation(res.getString("designation"));
                emp.setPhoneNo(res.getString("employee_Phone_No"));
                emp.setSalary(res.getInt("Salary"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public int searchOrder (int orderID , int itemID){
        try{
            OrderInfo = new OrderInfo();
            //System.out.println("Code point 1 ID = " + empID);
            String sql = "select * from orderdetails o,order_deliveryaddress r,dispatch d,employeedetails emp where o.orderID=r.orderID and o.itemID=r.itemID and d.DispatchCrewID=emp.employeeID";
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            //System.out.println("Code point : executed query");
            res.next();{
                if(res.getInt("orderID")==orderID && res.getInt("itemID")==itemID && res.getInt("employeeID")==Dispatcher.getEmployeeID()){
                    OrderInfo.setName(res.getString("customer_Name"));
                    OrderInfo.setPhoneNo(res.getString("PhoneNo"));
                    String state = res.getString("delivery_state");
                    if(state =="accepted"){
                        return 0;
                    } else if(state=="dispatched") {
                        return 1;
                    }else if(state=="delivered"){
                       return 2;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public ResultSet loadEmpIDCombo (){
        String sql = "select employeeID from dms.employeedetails;";
        try{
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            System.out.println("Code point 1");
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    
    public ResultSet refreshForm (int orderID , int itemID){
        String sql = "select * from dms.orderdetails where orderID="+ orderID +" and itemID=" + itemID + ";";
        try{
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            System.out.println("Code point 1");
            return res;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    
    public boolean deleteEmployee(int empID){
        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.NO_OPTION){
            return false;
        }
        try{
            String sql = "DELETE FROM dms.employeedetails WHERE `employeeID` =" + empID;
            stmt = conn.createStatement();
            int executeUpdate = stmt.executeUpdate(sql);
            if(executeUpdate==1){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateEmployee(int empID){
        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.NO_OPTION){
            return false;
        }
        try{
            String sql = "UPDATE employeedetails SET employee_Name = '" + emp.getName() + "'," +
                    "employee_Address = '" + emp.getAddress() + "'," +
                    "designation = '" + emp.getDesignation() + "'," +
                    "employee_Phone_No = '" + emp.getPhoneNo() + "'," +
                    "Salary = " + emp.getSalary() + " " +
                    "WHERE employeeID = '" + empID + "';";
            System.out.println(sql);
            stmt = conn.createStatement();
            int executeUpdate = stmt.executeUpdate(sql);
            if(executeUpdate==1){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateOrderdetails(){
        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.NO_OPTION){
            return false;
        }
        try{
            String sql = "UPDATE orderdetails SET delivery_state='" + OrderInfo.getState() + "' where orderID=" + OrderInfo.getOrderID() + " and itemID=" + OrderInfo.getItemID() + "";
            System.out.println(sql);
            stmt = conn.createStatement();
            int executeUpdate = stmt.executeUpdate(sql);
            if(executeUpdate==1){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public int getOrderID(){
        try{
            String sql = "select * from orderID";
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            res.next();
            return res.getInt("value");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
