package MedManagement;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.util.Date;
import javax.swing.UIManager.LookAndFeelInfo;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

public class MedManagement extends JFrame {

    static Connection con;
    static Statement st;
    static ResultSet rs;
    static String names;
    static PreparedStatement pre1;
    static JButton createUser;
    static String passw;
    public static JTable table1;
    public static JTextField searchBox;
    public static JLabel loginNameText;
    public static JFrame LoginPageFrame, DashboardFrame, MedicineFrame, Companiesframe, viewSalesRecordFrame, newSalesFrame, InsertMedicineFrame,
            InsertCompanyFrame, changePasswordFrame, createAUserFrame, updateMedicineFrame, updateCompanyFrame;

    MedManagement() {

    }

    public Connection DbConnection() {

        try {
            // Loading the Class Path;
            //  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("com.mysql.jdbc.Driver");

            //Class.forName("sqlite:jdbc:MedMgtDB");
            // Creating  a new connection
            // String myDb = "jdbc:odbc:MedMgtDB";
            String myDB = "jdbc:mysql://localhost:3306/MedMgtDB";
            con = DriverManager.getConnection(myDB, "root", "");
            //  con = DriverManager.getConnection(myDb);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public ResultSet getProductTable() throws Exception {
        //Vector<Vector<String>> OptionsVector = new Vector<Vector<String>>();

        Connection con = DbConnection();
        PreparedStatement pre = con.prepareStatement("select * from producttable");
        ResultSet rs = pre.executeQuery();

//        while (rs.next()) {
//            Vector<String> employee = new Vector<String>();
//            employee.add(rs.getString(1));
//            employee.add(rs.getString(2));
//            employee.add(rs.getString(3));
//            employee.add(rs.getString(4));
//            employee.add(rs.getString(5));
//            employee.add(rs.getString(6));
//            OptionsVector.add(employee);
//        }
//        if (con != null) {
//            con.close();
//        }
        return rs;
    }

    public ResultSet getSalesTable() throws Exception {
        //Vector<Vector<String>> OptionsVector = new Vector<Vector<String>>();

        Connection con = DbConnection();
        PreparedStatement pre = con.prepareStatement("select * from salestable");
        ResultSet rs = pre.executeQuery();

        return rs;

    }

    public Date time() {
        Date date = new Date();
        //date.toGMTString()
        return date;
    }

    public Vector getCompanyTable() throws Exception {
        Vector<Vector<String>> OptionsVector = new Vector<Vector<String>>();

        con = DbConnection();
        PreparedStatement pre = con.prepareStatement("select * from companytable");
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            Vector<String> employee = new Vector<String>();
            employee.add(rs.getString(1));
            employee.add(rs.getString(2));
            employee.add(rs.getString(3));
            employee.add(rs.getString(4));
            employee.add(rs.getString(5));
            employee.add(rs.getString(6));
            OptionsVector.add(employee);
        }
        if (con != null) {
            con.close();
        }

        return OptionsVector;
    }

    public Vector getMedicineTable() throws Exception {
        Vector<Vector<String>> OptionsVector = new Vector<>();

        con = DbConnection();
        PreparedStatement pre = con.prepareStatement("select * from producttable");
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            Vector<String> employee = new Vector<String>();
            employee.add(rs.getString(1));
            employee.add(rs.getString(2));
            employee.add(rs.getString(3));
            employee.add(rs.getString(4));
            employee.add(rs.getString(5));
            employee.add(rs.getString(6));
            OptionsVector.add(employee);
        }
        if (con != null) {
            con.close();
        }

        return OptionsVector;
    }

    public ResultSet getSearchTable() throws Exception {
        //Vector<Vector<String>> OptionsVector = new Vector<Vector<String>>();
        String search = searchBox.getText();
        con = DbConnection();
        String s = "select * from producttable";
        PreparedStatement pre = con.prepareStatement("select * from producttable where Options_Name = ?");

        pre.setString(1, searchBox.getText());
        ResultSet rs = pre.executeQuery();


        /*while (rs.next()) {
         Vector<String> employee = new Vector<String>();
         employee.add(rs.getString(1));
         employee.add(rs.getString(2));
         employee.add(rs.getString(3));
         employee.add(rs.getString(4));
         employee.add(rs.getString(5));
         employee.add(rs.getString(6));
         OptionsVector.add(employee);
         }*/
        //if (con != null) {
        //con.close();
        // }
        return rs;
    }

    public ArrayList<Options> getData(String catID) {
        String catID2 = "1";
        ArrayList<Options> list = new ArrayList<Options>();
        Connection con;
        Statement st;
        ResultSet rs;

        try {
            con = DbConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from companytable where Company_ID = " + catID2);

            Options p;
            while (rs.next()) {
                p = new Options(
                        rs.getString("Company_ID"),
                        rs.getString("Company_Name"),
                        rs.getString("Company_Country"),
                        rs.getString("Company_Email"),
                        rs.getString("Company_Contact_Number")
                //rs.getString("Company_Address")

                );
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MedManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ResultSet getCompanyUpdate() throws Exception {
        con = DbConnection();
        PreparedStatement pre = con.prepareStatement("insert * from producttable where Options_Name = ?");
        //ResultSet rs = pre.executeUpdate();
        return rs;

    }

    public static void main(String[] args) // Look and Feel codes
    {

        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MedManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MedManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MedManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MedManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    MedManagement m = new MedManagement();
                    //Creating a new Statement;
                    st = m.DbConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = st.executeQuery("select * from producttable,companytable, user");
                    new LoginPage();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }

        });

    }
}
