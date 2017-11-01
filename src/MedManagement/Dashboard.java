package MedManagement;

import java.awt.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Dashboard extends MedManagement implements ActionListener {

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));

    CardLayout cardlayout = new CardLayout();
    JPanel mainpane = new JPanel(cardlayout), centerpane, toppane, sidepane, rightpane, loginpane, dashpane = new JPanel(new BorderLayout());
    public JButton med, companies, viewSales, newSales, changePass;
    JButton searchButton;
    public static JButton searchButton1;
//    JCheckBox searchButton1;

    public TableRowSorter<TableModel> sorter;
    Connection con;
    Statement st;
    ResultSet rs;

    JTable table;

    public Dashboard() {
        Init();

    }

    public void Init() {

        DashboardFrame = new JFrame();
        DashboardFrame.add(mainpane);
        DashboardFrame.setLayout(cardlayout);
        DashboardFrame.setTitle("Medical Store Management");
        DashboardFrame.setSize(1320, 730);
        DashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DashboardFrame.setVisible(true);
        DashboardFrame.setLocationRelativeTo(null);
        DashboardFrame.setBackground(Color.BLUE.darker().darker());

        //this is the top panel for search
        toppane = new JPanel(new GridBagLayout());
        toppane.setBackground(Color.BLUE.darker().darker());
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.insets = new Insets(15, 10, 10, 30);
        gbc0.weightx = 0.01;

        JPanel y = new JPanel(new BorderLayout());
        y.setBackground(Color.BLUE.darker().darker());
        searchBox = new JTextField(60);
        y.add(searchBox, BorderLayout.EAST);
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.gridx = 8;
        gbc0.gridy = 1;
        toppane.add(y, gbc0);

        JPanel y1 = new JPanel(new BorderLayout());

        searchButton1 = new JButton("Show Table");
        searchButton1.addActionListener(this);
        searchButton1.setEnabled(true);

        //searchButton1.setForeground(Color.WHITE);
        y1.add(searchButton1, BorderLayout.EAST);

        y1.setBackground(Color.BLUE.darker().darker());
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        y1.add(searchButton, BorderLayout.WEST);
        gbc0.gridx = 9;
        gbc0.gridy = 1;

        toppane.add(y1, gbc0);

        JPanel pa = new JPanel(new BorderLayout());
        pa.setBackground(Color.BLUE.darker().darker());
        JLabel loginText = new JLabel("You as Logged in as: ");
        //gbc0.insets = new Insets(5, 90, 0, 40);
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.gridx = 8;
        gbc0.gridy = 3;
        gbc0.ipady = 5;
        pa.add(loginText, BorderLayout.EAST);
        toppane.add(pa, gbc0);

        loginNameText = new JLabel();
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.gridx = 9;
        gbc0.gridy = 3;
        gbc0.ipadx = 5;
        toppane.add(loginNameText, gbc0);
        dashpane.add(toppane, BorderLayout.NORTH);

        centerpane = new JPanel(new BorderLayout(5, 5));
        centerpane.setBackground(Color.BLUE.darker().darker());
        JPanel loginPane = new JPanel();

        loginPane.setBackground(Color.BLUE.darker().darker());
        centerpane.add(loginPane, BorderLayout.NORTH);

        JPanel loginpane1 = new JPanel();

        loginpane1.setBackground(Color.BLUE.darker().darker());
        centerpane.add(loginpane1, BorderLayout.WEST);
        update();

        dashpane.add(Box.createGlue());

        JPanel loginpane4 = new JPanel();

        loginpane4.setBackground(Color.BLUE.darker().darker());
        centerpane.add(loginpane4, BorderLayout.SOUTH);

        JPanel loginpane3 = new JPanel();

        loginpane3.setBackground(Color.BLUE.darker().darker());
        centerpane.add(loginpane3, BorderLayout.EAST);

        dashpane.add(centerpane, BorderLayout.CENTER);

        rightpane = new JPanel(new GridLayout(3, 1));
        rightpane.setBackground(Color.BLUE.darker().darker());

        JPanel topSidePane = new JPanel();
        topSidePane.setBackground(Color.BLUE.darker().darker());

        JPanel logoPane = new JPanel();
        JLabel logoTop = new JLabel();
        logoTop.setIcon(resizeImage(image));
        logoPane.setBackground(Color.BLUE.darker().darker());
        logoPane.add(logoTop);
        topSidePane.add(logoPane);

        rightpane.add(topSidePane);

        //This panel is responsible for the middle buttons
        JPanel middleSidePane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(10, 50, 2, 10);
        gbc3.weightx = 1;
        gbc3.weighty = 1;

        med = new JButton("Medicines");
        med.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        middleSidePane.add(med, gbc3);

        companies = new JButton("Companies");
        companies.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        middleSidePane.add(companies, gbc3);

        viewSales = new JButton("View Sales Record");
        viewSales.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        middleSidePane.add(viewSales, gbc3);

        newSales = new JButton("New Sales");
        newSales.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 3;
        middleSidePane.add(newSales, gbc3);

        changePass = new JButton("Change Password");
        changePass.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 4;
        middleSidePane.add(changePass, gbc3);

        middleSidePane.setBackground(Color.BLUE.darker().darker());
        rightpane.add(middleSidePane);

        //Panel for the down button (Create a User and Log out Button)
        sidepane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.insets = new Insets(31, 50, 10, 10);
        gbc2.weightx = 0.01;

        sidepane.setSize(100, 100);
        sidepane.setVisible(true);
        sidepane.setBackground(Color.BLUE.darker().darker());
        createUser = new JButton("Create a User");
        createUser.addActionListener(this);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        sidepane.add(createUser, gbc2);

        JButton logOut = new JButton("Log Out");
        logOut.addActionListener(this);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        sidepane.add(logOut, gbc2);

        rightpane.add(sidepane);
        dashpane.add(rightpane, BorderLayout.WEST);
        mainpane.add(dashpane);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Medicines":
                DashboardFrame.setVisible(false);
                Medicine m = new Medicine();
                m.setName(loginNameText.getText());

                break;
            case "Companies":
                DashboardFrame.setVisible(false);
                Companies c = new Companies();
                c.setName(loginNameText.getText());
                break;

            case "View Sales Record":
                DashboardFrame.setVisible(false);
                viewSalesRecord v = new viewSalesRecord();
                v.setName(loginNameText.getText());
                break;

            case "New Sales":
                DashboardFrame.setVisible(false);
                newSales record = new newSales();
                break;

            case "Create a User":
                DashboardFrame.setVisible(false);
                createANewUser user = new createANewUser();
                user.setName(loginNameText.getText());
                break;

            case "Log Out":
                DashboardFrame.setVisible(false);
                new LoginPage();
                break;

            case "Change Password":
                DashboardFrame.setVisible(false);
                changePassword pass = new changePassword();
                pass.setName(loginNameText.getText());

                break;

            case "Search":

                searchMethod();

                break;

            case "Show Table":
                try {

                    con = DbConnection();
                    PreparedStatement pre = con.prepareStatement("select * from producttable");
                    // pre.setString(1, searchBox.getText());
                    ResultSet rs = pre.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    if (con != null) {
                        con.close();

                    }
                } catch (Exception ex) {
                    Logger.getLogger(Dashboard.class
                            .getName()).log(Level.SEVERE, null, ex);

                }
                break;

        }

    }

    public void setName(String name) {
        loginNameText.setText(name.toUpperCase());

    }

    public void searchMethod() {

        String text = searchBox.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(text));
        }
//        try {
//
//            con = DbConnection();
//            PreparedStatement pre = con.prepareStatement("select * from producttable where Product_Name = ?");
//            pre.setString(1, searchBox.getText());
//            ResultSet rs = pre.executeQuery();
//            table.setModel(DbUtils.resultSetToTableModel(rs));
//
//            if (con != null) {
//                con.close();
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
    }

    public void update() {

        try {
            ResultSet data = getProductTable();

            table = new JTable(DbUtils.resultSetToTableModel(data));
            sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            table.setDragEnabled(false);
            table.setEnabled(false);
            JScrollPane scroll = new JScrollPane(table);
            centerpane.add(scroll, BorderLayout.CENTER);
            data.close();
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ImageIcon resizeImage(ImageIcon Path) {
        Image image1 = Path.getImage();
        Image newImage = image1.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        return icon;
    }

}
