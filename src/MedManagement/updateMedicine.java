package MedManagement;

import static MedManagement.MedManagement.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class updateMedicine extends MedManagement implements ActionListener {

    private Vector<Vector<String>> data;
    private Vector<String> header;
    JTable table;
    Vector<Vector<String>> employee1;
    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));

    JLabel loginNameText = new JLabel();

    JPanel centerpane, toppane, sidepane, rightpane, loginpane;
    JPanel mainpane = new JPanel(new BorderLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc;
    // CardLayout cardlayout = new CardLayout();

    JTextField updatePriceText, updateQuantityText;
    JButton updateQuantityButton, updatePriceButton;
    JComboBox MedicineName;
    String name, ProductName;

    public updateMedicine() {
        Init();

    }

    public void Init() {
        updateMedicineFrame = new JFrame();
        updateMedicineFrame.setSize(1320, 730);
        updateMedicineFrame.setVisible(true);
        updateMedicineFrame.setTitle("Medical Store Management");
        updateMedicineFrame.add(mainpane);
        updateMedicineFrame.setBackground(Color.BLUE.darker().darker());
        updateMedicineFrame.setLocationRelativeTo(null);
        updateMedicineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainpane.setBackground(Color.BLUE.darker().darker());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 50, 30);
        gbc.weightx = 0.001;

        centerpane = new JPanel(new GridBagLayout());
        centerpane.setVisible(true);
        centerpane.setBackground(Color.BLUE.darker().darker());
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(30, 10, 20, 20);

        JPanel v3 = new JPanel(new BorderLayout());
        v3.setBackground(Color.BLUE.darker().darker());

        JLabel logo = new JLabel();
        logo.setIcon(resizeImage(image));
        v3.add(logo, BorderLayout.LINE_START);

        JPanel v = new JPanel(new GridBagLayout());
        v.setBackground(Color.BLUE.darker().darker());
        JLabel productName = new JLabel("PRODUCT NAME:");
        productName.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        v.add(productName, gbc1);

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
        gbc1.gridx = 2;
        gbc1.gridy = 3;
        v.add(MedicineName, gbc1);

        updatePriceText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 4;
        v.add(updatePriceText, gbc1);

        updateQuantityText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 5;
        v.add(updateQuantityText, gbc1);

        updatePriceButton = new JButton("Update Price");
        updatePriceButton.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 3;
        gbc1.gridy = 4;
        v.add(updatePriceButton, gbc1);

        updateQuantityButton = new JButton("Update Quantity");
        updateQuantityButton.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 3;
        gbc1.gridy = 5;
        v.add(updateQuantityButton, gbc1);

        v3.add(v, BorderLayout.SOUTH);

        centerpane.add(v3, gbc);
        mainpane.add(centerpane, BorderLayout.PAGE_START);

        update();
        data();

        sidepane = new JPanel(new BorderLayout(50, 50));
        sidepane.setBackground(Color.BLUE.darker().darker());

        JLabel logo1 = new JLabel("......................");
        logo1.setForeground(Color.BLUE.darker().darker());

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
                updateMedicineFrame.setVisible(false);
                Medicine d = new Medicine();
                d.setName(loginNameText.getText());
            }
        });
        JLabel logo5 = new JLabel("..................................................");
        logo5.setForeground(Color.BLUE.darker().darker());
        bpane.add(b, BorderLayout.NORTH);
        bpane.add(logo5, BorderLayout.CENTER);

        JPanel bpane2 = new JPanel(new BorderLayout(100, 100));
        bpane2.setBackground(Color.BLUE.darker().darker());
        bpane.add(b, BorderLayout.NORTH);
        JLabel logo4 = new JLabel("....................");
        logo4.setForeground(Color.BLUE.darker().darker());
        bpane2.add(logo4, BorderLayout.NORTH);
        rightpane.add(bpane, BorderLayout.WEST);
        rightpane.add(bpane2, BorderLayout.EAST);
        mainpane.add(rightpane, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Update Price":
                updatePrice();
                break;
            case "Update Quantity":
                updateQuantity();
                break;
            case "Back":
                updateMedicineFrame.setVisible(false);
                MedicineFrame.setVisible(true);

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
        try {
            Vector<Vector<String>> OptionsVector = new Vector<Vector<String>>();
            Vector<String> employee = new Vector<String>();
            employee.add(null);
            employee.add(null);
            employee.add(null);
            employee.add(null);
            employee.add(null);
            employee.add(null);
            OptionsVector.add(employee);

            header = new Vector<String>();
            header.add("ProductID");
            header.add("Product_Name");
            header.add("Company_Name");
            header.add("Product_Category");
            header.add("Quantity");
            header.add("Price_Per_Unit");

            table = new JTable(OptionsVector, header);
            table.setDragEnabled(false);
            table.setEnabled(false);
            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(table);
            mainpane.add(scroll, BorderLayout.CENTER);

            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void data() {
        employee1 = new Vector<>();
        MedicineName.addItemListener(new ItemListener() {
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
                } catch (Exception ex) {
                    Logger.getLogger(updateCompany.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    public void updatePrice() {
        String name = updatePriceText.getText();

        try {
            Connection con1 = DbConnection();
            Statement pre = con1.createStatement();
            String stt = "update producttable set Price_Per_Unit = '" + name + "' where Product_Name = '" + ProductName + "'";
            pre.executeUpdate(stt);
            con1.close();
            pre.close();

            Connection con = DbConnection();
            Statement pre1 = con.createStatement();
            PreparedStatement p = con.prepareStatement("select * from producttable where Product_Name = ? ");
            p.setString(1, ProductName);
            ResultSet data = p.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(data));

        } catch (Exception ex) {

            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateQuantity() {
        String name = updateQuantityText.getText();

        try {
            Connection con1 = DbConnection();
            Statement pre = con1.createStatement();
            String stt = "update producttable set Quantity = '" + name + "' where Product_Name = '" + ProductName + "'";
            pre.executeUpdate(stt);
            con1.close();
            pre.close();

            Connection con = DbConnection();
            Statement pre1 = con.createStatement();
            PreparedStatement p = con.prepareStatement("select * from producttable where Product_Name = ? ");
            p.setString(1, ProductName);
            ResultSet data = p.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(data));

        } catch (Exception ex) {

            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   // public static void main(String[] args) {
    //  new updateMedicine();
    // }
}
