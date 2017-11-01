package MedManagement;

import static MedManagement.MedManagement.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.*;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class newSales extends MedManagement implements ActionListener {

    JTable table, billTable;
    Vector<Vector<String>> employee1;
    double totalAmount, price;
    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));
    int quantity = 0, totalQuantity = 0;
    ;
    JLabel loginNameText = new JLabel();
    int productQuantity;
    JPanel centerpane, toppane, sidepane, v3, rightpane, p, loginpane;
    JPanel mainpane = new JPanel(new BorderLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc;
    String productname = null;
    String companyname = null;
    String productcategory = null;

    String priceperunit = null;
    // CardLayout cardlayout = new CardLayout();

    JTextField salesQuantity;
    JButton doneButton, addToCart;
    JComboBox MedicineName;
    String name, ProductName;

    public newSales() {
        Init();

    }

    public void Init() {
        newSalesFrame = new JFrame();
        newSalesFrame.setSize(1320, 730);
        newSalesFrame.setVisible(true);
        newSalesFrame.setTitle("Medical Store Management");
        newSalesFrame.add(mainpane);
        newSalesFrame.setBackground(Color.BLUE.darker().darker());
        newSalesFrame.setLocationRelativeTo(null);
        newSalesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainpane.setBackground(Color.BLUE.darker().darker());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 50, 30);
        gbc.weightx = 0.001;

        centerpane = new JPanel(new GridBagLayout());
        centerpane.setVisible(true);
        centerpane.setBackground(Color.BLUE.darker().darker());
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(5, 5, 5, 20);
        gbc1.weightx = 0.01;

        JPanel v2 = new JPanel(new BorderLayout());
        v2.setBackground(Color.BLUE.darker().darker());
        v3 = new JPanel(new BorderLayout(10, 10));
        v3.setBackground(Color.BLUE.darker().darker());

        JLabel logo = new JLabel();
        logo.setIcon(resizeImage(image));
        v2.add(logo, BorderLayout.LINE_START);

        JPanel v = new JPanel(new GridBagLayout());
        v.setBackground(Color.BLUE.darker().darker());

        JLabel productName2 = new JLabel("PRODUCT NAME:");
        productName2.setForeground(Color.BLUE.darker().darker());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        v.add(productName2, gbc1);

        JLabel productName3 = new JLabel("PRODUCT NAME:");
        productName3.setForeground(Color.BLUE.darker().darker());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        v.add(productName3, gbc1);

        JLabel productName4 = new JLabel("PRODUCT NAME:");
        productName4.setForeground(Color.BLUE.darker().darker());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 0;
        v.add(productName4, gbc1);

        JLabel productName0 = new JLabel("ENTER QUANTITY:");
        productName0.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 3;
        gbc1.gridy = 0;
        v.add(productName0, gbc1);

        JLabel productName1 = new JLabel("ENTER QUANTITY:");
        productName1.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 3;
        gbc1.gridy = 1;
        v.add(productName1, gbc1);

        MedicineName = new JComboBox();
        try {
            Statement st = DbConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select Product_Name from producttable");
            while (rs.next()) {
                MedicineName.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(InsertMedicine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(updateCompany.class.getName()).log(Level.SEVERE, null, ex);
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 4;
        gbc1.gridy = 0;
        v.add(MedicineName, gbc1);

        salesQuantity = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 4;
        gbc1.gridy = 1;
        v.add(salesQuantity, gbc1);

        JLabel productName = new JLabel("PRODUCT NAME:");
        productName.setForeground(Color.BLUE.darker().darker());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 6;
        gbc1.gridy = 0;
        v.add(productName, gbc1);

        JLabel productName5 = new JLabel("PRODUCT NAME:");
        productName5.setForeground(Color.BLUE.darker().darker());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 7;
        gbc1.gridy = 0;
        v.add(productName5, gbc1);

        JLabel productName6 = new JLabel("PRODUCT NAME:");
        productName6.setForeground(Color.BLUE.darker().darker());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 8;
        gbc1.gridy = 0;
        v.add(productName6, gbc1);

        addToCart = new JButton("Add To Cart");
        addToCart.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 9;
        gbc1.gridy = 0;
        gbc1.ipadx = 1;
        gbc1.ipady = 50;
        v.add(addToCart, gbc1);

        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.fill = GridBagConstraints.VERTICAL;
        gbc1.gridx = 9;
        gbc1.gridy = 1;
        gbc1.ipadx = 1;
        gbc1.ipady = 50;
        v.add(doneButton, gbc1);

        v2.add(v, BorderLayout.SOUTH);

        //
        // data();
        JPanel bpane7 = new JPanel();
        bpane7.setBackground(Color.BLUE.darker().darker());
        JLabel logo9 = new JLabel("........................................................................................");
        logo9.setForeground(Color.BLUE.darker().darker());
        // bpane.add(b, BorderLayout.NORTH);
        bpane7.add(logo9, BorderLayout.CENTER);
        v3.add(bpane7, BorderLayout.WEST);

        v3.add(v2, BorderLayout.NORTH);

        update();
        data();

        centerpane.add(v3, gbc);
        mainpane.add(centerpane, BorderLayout.PAGE_START);

        sidepane = new JPanel(new BorderLayout(50, 50));
        sidepane.setBackground(Color.BLUE.darker().darker());

        JLabel logo1 = new JLabel("......................");
        logo1.setForeground(Color.BLUE.darker().darker());
        bill();
        JLabel logo2 = new JLabel("................................................................");
        logo2.setForeground(Color.BLUE.darker().darker());
        sidepane.add(logo2, BorderLayout.WEST);
        sidepane.add(logo1, BorderLayout.EAST);
        //bpane.add(b, BorderLayout.SOUTH);
        mainpane.add(sidepane, BorderLayout.WEST);

        toppane = new JPanel();
        toppane.setBackground(Color.BLUE.darker().darker());
        // Button b2 = new Button("Back");
        JPanel bpane1 = new JPanel(new BorderLayout(50, 50));
        bpane1.setBackground(Color.BLUE.darker().darker());
        //bpane1.add(b2);
        JLabel logo3 = new JLabel("....................");
        logo3.setForeground(Color.BLUE.darker().darker());
        bpane1.add(logo3, BorderLayout.WEST);
        toppane.add(bpane1, BorderLayout.SOUTH);
        mainpane.add(toppane, BorderLayout.EAST);

        rightpane = new JPanel(new BorderLayout(50, 50));
        rightpane.setBackground(Color.BLUE.darker().darker());

        JPanel bpane = new JPanel(new BorderLayout(100, 100));
        bpane.setBackground(Color.BLUE.darker().darker());
        JButton b = new JButton("Back");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newSalesFrame.setVisible(false);
                Medicine d = new Medicine();
                d.setName(loginNameText.getText());
            }
        });
        JLabel logo5 = new JLabel("");
        logo5.setForeground(Color.BLUE.darker().darker());
        bpane.add(b, BorderLayout.NORTH);
        bpane.add(logo5, BorderLayout.CENTER);

        JPanel bpane2 = new JPanel(new BorderLayout());
        bpane2.setBackground(Color.BLUE.darker().darker());
        JLabel logo4 = new JLabel("....................");
        logo4.setForeground(Color.BLUE.darker().darker());
        bpane2.add(logo4, BorderLayout.NORTH);
        rightpane.add(bpane, BorderLayout.WEST);
        //bill();
        rightpane.add(bpane2, BorderLayout.EAST);
        mainpane.add(rightpane, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add To Cart":
                // updateQuantity();
                addToCart();
                updateQuantity();
                break;
            case "Done":
                CalcBills();
                break;
            case "Print":
                try {
                    table1.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(newSales.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }

    }

    public ImageIcon resizeImage(ImageIcon Path) {
        Image image1 = Path.getImage();
        Image newImage = image1.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        return icon;
    }

    public void setName(String name) {
        loginNameText.setText(name.toUpperCase());
    }

    public void update() {
        JPanel pp = new JPanel();
        pp.setBackground(Color.BLUE.darker().darker());

        try {

            table = new JTable();
            table.setModel(new DefaultTableModel(new Object[][]{{null, null, null, null, null, null}}, new String[]{
                "ProductID",
                "Product_Name",
                "Company_Name",
                "Product_Category",
                "Quantity",
                "Price_Per_Unit"
            }
            ));

            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(table);
            GroupLayout la = new GroupLayout(pp);
            pp.setLayout(la);
            la.setAutoCreateGaps(true);
            la.setAutoCreateContainerGaps(true);

            la.setHorizontalGroup(la.createSequentialGroup()
                    .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            );

            la.setVerticalGroup(la.createSequentialGroup()
                    .addGroup(la.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    )
            );

            v3.add(pp, BorderLayout.CENTER);

        } catch (Exception ex) {

        }

    }

    public void bill() {
        try {
            JPanel pp = new JPanel();
            JPanel pp1 = new JPanel(new BorderLayout());
            pp.setBackground(Color.BLUE.darker().darker());

            billTable = new JTable();
            billTable.setModel(new DefaultTableModel(new Object[][]{{null, null}}, new String[]{
                "Values",
                "Bills"
            }
            ));

            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(billTable);
            JLabel l = new JLabel("..........................");
            l.setForeground(Color.BLUE.darker().darker());
            GroupLayout la = new GroupLayout(pp);
            pp.setLayout(la);
            la.setAutoCreateGaps(true);
            la.setAutoCreateContainerGaps(true);

            la.setHorizontalGroup(la.createSequentialGroup()
                    .addComponent(l)
                    .addGroup(la.createParallelGroup(GroupLayout.Alignment.LEADING))
                    .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 10, Short.MAX_VALUE)
            );

            la.setVerticalGroup(la.createSequentialGroup()
                    .addComponent(l)
                    .addGroup(la.createParallelGroup(GroupLayout.Alignment.LEADING))
                    .addGroup(la.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)
                    )
            );

            JPanel bpane7 = new JPanel(new BorderLayout());
            JButton Back = new JButton("Back");
            Back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    newSalesFrame.setVisible(false);
                    DashboardFrame.setVisible(true);
                }

            });
            bpane7.setBackground(Color.BLUE.darker().darker());
            JLabel logo9 = new JLabel(".....................................................................");
            logo9.setForeground(Color.BLUE.darker().darker());
            JPanel bpane3 = new JPanel(new GridBagLayout());
            bpane3.setBackground(Color.BLUE.darker().darker());
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 4;
            gbc.gridy = 9;
            gbc.ipadx = 40;
            gbc.ipady = 5;
            bpane3.add(Back, gbc);
            bpane7.add(bpane3, BorderLayout.PAGE_END);
            bpane7.add(logo9, BorderLayout.EAST);
            pp1.add(bpane7, BorderLayout.WEST);

            JPanel bpane = new JPanel();
            JButton print = new JButton("Print");
            print.addActionListener(this);
            bpane.setBackground(Color.BLUE.darker().darker());
            JLabel logo = new JLabel(".............................................................................");
            logo.setForeground(Color.BLUE.darker().darker());
            // bpane.add(b, BorderLayout.NORTH);
            bpane.add(logo, BorderLayout.CENTER);

            JPanel bpane1 = new JPanel(new GridBagLayout());
            bpane1.setBackground(Color.BLUE.darker().darker());
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 50, 20);
            gbc.weightx = 0.001;
            // gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 4;
            gbc.gridy = 9;
            gbc.ipadx = 40;
            gbc.ipady = 40;
            bpane1.add(print, gbc);
            bpane.add(bpane1, BorderLayout.EAST);
            pp1.add(bpane, BorderLayout.EAST);

            pp1.add(pp, BorderLayout.CENTER);
            v3.add(pp1, BorderLayout.SOUTH);
        } catch (Exception ex) {
            Logger.getLogger(newSales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void data() {
        employee1 = new Vector<>();
        MedicineName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ProductName = MedicineName.getSelectedItem().toString();
                try {

                    Connection con = DbConnection();
                    PreparedStatement p = con.prepareStatement("select * from producttable where Product_Name = ? ");
                    p.setString(1, ProductName);
                    ResultSet data = p.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(data));
                } catch (SQLException ex) {
                    Logger.getLogger(updateCompany.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    public void addToCart() {
        if (DBRetrieveData() == true) {
            DBinsertToSales();
        } else {
            JOptionPane.showMessageDialog(null, "Quantity Empty!");
        }

    }

    private void updateQuantity() {
        productQuantity = Integer.parseInt(salesQuantity.getText());
        int name = Integer.parseInt(salesQuantity.getText());
        int DBQuantity = 0;
        totalQuantity += productQuantity;
        try {

            Connection con = DbConnection();
            Statement pre = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String stt = "select Quantity, Price_Per_Unit from producttable where Product_Name = '" + ProductName + "'";
            ResultSet rs = pre.executeQuery(stt);
            if (rs.next()) {
                DBQuantity = rs.getInt("Quantity");
                price = rs.getDouble("Price_Per_Unit");
            }
            double subtotal1 = price * productQuantity;

            totalAmount += subtotal1;

            int calcQuantity = DBQuantity - productQuantity;

            try (Connection con1 = DbConnection(); Statement st1 = con1.createStatement()) {
                String update = "update producttable set Quantity = '" + calcQuantity + "'where Product_Name = '" + ProductName + "'";
                st1.executeUpdate(update);
                data();
            }

        } catch (Exception ex) {

            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void CalcBills() {

        Vector v = new Vector();
        v.add("Values");
        v.add("Bills");

        Vector<String> v1 = new Vector();

        v1.add(totalQuantity + "");
        v1.add(String.valueOf(totalAmount) + "");

        Vector<Vector<String>> v2 = new Vector();
        v2.add(v1);

        billTable.setModel(new DefaultTableModel(v2, v));

    }

    private void DBinsertToSales() {
        try {
            con = DbConnection();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from salestable");

            rs.moveToInsertRow();
            rs.updateString("Product_Name", productname);
            rs.updateString("Company_Name", companyname);
            rs.updateString("Date_Of_Sales", time().toGMTString());
            rs.updateInt("Quantity", quantity);
            rs.updateString("Price_Per_Unit", priceperunit);
            rs.insertRow();
            JOptionPane.showMessageDialog(null, "Record updated successully!");

            st.close();
            rs.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex);

        }

    }

    private boolean DBRetrieveData() {
        ProductName = MedicineName.getSelectedItem().toString();
        quantity = Integer.parseInt(salesQuantity.getText());
        int q = 0;
        try {
            Connection con = DbConnection();
            PreparedStatement p = con.prepareStatement("select * from producttable where Product_Name = ? ");
            p.setString(1, ProductName);
            ResultSet rs1 = p.executeQuery();
            while (rs1.next()) {
                productname = rs1.getString("Product_Name");
                companyname = rs1.getString("Company_Name");
                productcategory = rs1.getString("Product_Category");
                priceperunit = rs1.getString("Price_Per_Unit");
                q = rs1.getInt("Quantity");

            }

            if (q != 0) {
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(newSales.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

}
