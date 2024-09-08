package dao;

import dto.Invoice;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import utils.util;

public class InvoiceDao {

    public ArrayList<Invoice> getInvoiceList(String userId) throws SQLException {

        ArrayList<Invoice> listinv = new ArrayList<>();
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Invoice inv = null;
        String invID;
        String invDate;
        String customer;
        String userID;

        try {

            conn = util.createConnection();
            p = conn.prepareStatement("Select * from[dbo].[tblInvoices]  where [userID]=?");
            p.setString(1, userId);
            rs = p.executeQuery();
            while (rs.next()) {

                invID = rs.getString("invID");
                invDate = rs.getString("invDate");
                customer = rs.getString("customer");
                userID = rs.getString("userID");

                inv = new Invoice(invID, invDate, customer, userID);
                listinv.add(inv);
            }

        } catch (SQLException e) {
            System.out.println("Error access database");
        } finally {
            //close connection
            if (rs != null) {
                rs.close();
            }
            if (p != null) {
                p.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listinv;
    }

    public boolean DeleteInvoice(String invID) {
        boolean check = false;

        try {
            Connection conn = util.createConnection();
            PreparedStatement p = conn.prepareStatement("delete from tblInvoices where invid=?");
            p.setString(1, invID);
            int value = p.executeUpdate();
            if (value > 0) {
                check = true;
            }
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error connect DB");
        }
        return check;
    }

    public boolean CheckDetail(String invID) {
        boolean check = false;
        String sql = "select *\n"
                + "from [dbo].[tblInv_Detail]\n"
                + "where [dbo].[tblInv_Detail].invID= ?";
        try {
            Connection conn = util.createConnection();
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, invID);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                check = true;
            }
            conn.close();
            p.close();
        } catch (SQLException e) {
            System.out.println("Error connect  DB");
        }

        return check;

    }

    public boolean UpdateInvoice(Invoice inv) {
        boolean check = false;
        String sql = "update tblInvoices set invDate=?, customer=? where invid=?";       
        try {
            Connection conn = util.createConnection();
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, inv.getInvDate());
            p.setString(2, inv.getCustomer());
            p.setString(3, inv.getInvID());
            int value = p.executeUpdate();
            if (value > 0) {
                check = true;
            }
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error connect  DB");
        }
        return check;
    }

    public boolean InsertInvoice(Invoice inv) {
        boolean check = false;

        try {
            Connection conn = util.createConnection();
            String sql = "Insert into tblInvoices (invID, invDate, customer, userID) Values(?,?,?,?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, inv.getInvID());
            p.setString(2, inv.getInvDate());
            p.setString(3, inv.getCustomer());
            p.setString(4, inv.getUserID());
            int value = p.executeUpdate();
            if (value > 0) {
                check = true;
            }
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error connect  DB");
        }
        return check;
    }

    public boolean CheckDuplicate(String invid) {
        boolean check = false;

        try {
            Connection conn = util.createConnection();
            PreparedStatement p = conn.prepareStatement("select * from tblInvoices where invid=?");
            p.setString(1, invid);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            p.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error connect  DB");
        }
        return check;
    }

    public String SumInvoices(String userId) {
        String sum = null;
        try {
            Connection conn = util.createConnection();
            String url = "select sum([price])as 'TOTAL'\n"
                + "from [dbo].[tblInv_Detail] inner join [dbo].[tblInvoices]\n"
                + "on [dbo].[tblInv_Detail].invID = [dbo].[tblInvoices].invID\n"
                + "where [dbo].[tblInvoices].userID like ?";
            PreparedStatement p = conn.prepareStatement(url);
            p.setString(1, userId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                sum = rs.getString("TOTAL");
            }
            p.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error access database");
        }
        return sum;
    }

    public String SumInvoicesBySearch(String userId, String searchValue) {
        String sum = null;
        try {
            Connection conn = util.createConnection();
            String url = "select sum([price])as 'TOTAL'\n"
                + "from [dbo].[tblInv_Detail] inner join [dbo].[tblInvoices]\n"
                + "on [dbo].[tblInv_Detail].invID = [dbo].[tblInvoices].invID\n"
                + "where [dbo].[tblInvoices].userID = ? and "
                + "[dbo].[tblInvoices].customer like ?";
            PreparedStatement p = conn.prepareStatement(url);
            p.setString(1, userId);
            p.setString(2, "%"+searchValue+"%");
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                sum = rs.getString("TOTAL");
            }
            p.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error access database");
        }
        return sum;
    }

    public ArrayList<Invoice> getInvoiceSearchList(String searchvalue, String userIDlogin) throws SQLException {

        ArrayList<Invoice> listinv = new ArrayList<>();
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Invoice inv;
        String invID;
        String invDate;
        String customer;
        String userID;

        try {

            conn = util.createConnection();
            String url = "select * from tblInvoices where customer like ? and userID like ?";
            p = conn.prepareStatement(url);
            p.setString(1, "%" + searchvalue + "%");
            p.setString(2, userIDlogin);
            rs = p.executeQuery();

            while (rs.next()) {
                invID = rs.getString("invID");
                invDate = rs.getString("invDate");
                customer = rs.getString("customer");
                userID = rs.getString("userID");

                inv = new Invoice(invID, invDate, customer, userID);
                listinv.add(inv);
            }
        } catch (SQLException e) {
            System.out.println("Error access database");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (p != null) {
                p.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listinv;
    }

}
