package MedManagement;

import static MedManagement.MedManagement.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class InsertMedicine extends MedManagement implements ActionListener {
    //Dashboard d = new Dashboard();

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));

    JLabel loginNameText = new JLabel();

    JPanel centerpane, toppane, sidepane, rightpane, loginpane;
    JPanel mainpane = new JPanel(new BorderLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc;
    CardLayout cardlayout = new CardLayout();

    JTextField productNameText, productQuantityText, productPriceText, addCategoryText;

    JComboBox productCategoryText, companyNameText;
    String name;

    public InsertMedicine() {

        Init();
        try {
            ResultSet rs = st.executeQuery("select Product_Category from producttable");
            while (rs.next()) {
                productCategoryText.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertMedicine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Init() {

        InsertMedicineFrame = new JFrame();
        InsertMedicineFrame.setSize(1320, 730);
        InsertMedicineFrame.setVisible(true);
        InsertMedicineFrame.setTitle("Medical Store Management");
        InsertMedicineFrame.add(mainpane);
        InsertMedicineFrame.setBackground(Color.BLUE.darker().darker());
        InsertMedicineFrame.setLocationRelativeTo(null);
        InsertMedicineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainpane.setBackground(Color.BLUE.darker().darker());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 50, 30);
        gbc.weightx = 0.001;

        centerpane = new JPanel(new GridBagLayout());
        centerpane.setVisible(true);
        centerpane.setBackground(Color.BLUE.darker().darker());
        //loginpane = new JPanel(new GridBagLayout());
        //loginpane.setBackground(Color.BLACK);

        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(30, 10, 20, 20);
        //gbc1.weightx = 0.01;

        JPanel v = new JPanel(new GridBagLayout());
        v.setBackground(Color.BLUE.darker().darker());

        JLabel productName = new JLabel("PRODUCT NAME");
        productName.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        v.add(productName, gbc1);

        JLabel companyName = new JLabel("COMPANY NAME:");
        companyName.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        v.add(companyName, gbc1);

        JLabel productCategory = new JLabel("PRODUCT CATEGORY:");
        productCategory.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        v.add(productCategory, gbc1);

        JLabel productQuantity = new JLabel("PRODUCT QUANTITY:");
        productQuantity.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        v.add(productQuantity, gbc1);

        JLabel productPrice = new JLabel("PRODUCT PRICE:");
        productPrice.setForeground(Color.WHITE);
        productQuantity.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 4;
        v.add(productPrice, gbc1);

        JLabel space = new JLabel("              ");
        space.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        v.add(space, gbc1);

        productNameText = new JTextField(12);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 0;
        v.add(productNameText, gbc1);

        companyNameText = new JComboBox();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 1;
        v.add(companyNameText, gbc1);

        productCategoryText = new JComboBox();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 2;
        v.add(productCategoryText, gbc1);

        addCategoryText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 3;
        gbc1.gridy = 2;
        v.add(addCategoryText, gbc1);

        productQuantityText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 3;
        v.add(productQuantityText, gbc1);

        productPriceText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 4;
        v.add(productPriceText, gbc1);

        JButton Insert = new JButton("Insert");
        Insert.addActionListener(this);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 4;
        gbc1.gridy = 9;
        gbc1.ipadx = 50;
        gbc1.ipady = 50;
        v.add(Insert, gbc1);

        centerpane.add(v, gbc);
        mainpane.add(centerpane, BorderLayout.CENTER);

        sidepane = new JPanel(new BorderLayout());
        JLabel logo = new JLabel();
        logo.setIcon(resizeImage(image));
        sidepane.add(logo, BorderLayout.NORTH);
        sidepane.setBackground(Color.BLUE.darker().darker());

        JPanel bpane = new JPanel(new BorderLayout(50, 50));
        bpane.setBackground(Color.BLUE.darker().darker());
        JButton b = new JButton("Back");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InsertMedicineFrame.setVisible(false);
                MedicineFrame.setVisible(true);
                //Medicine d = new Medicine();
                //d.setName(loginNameText.getText());
            }
        });
        bpane.add(b, BorderLayout.CENTER);
        JLabel logo1 = new JLabel("........");
        logo1.setForeground(Color.BLUE.darker().darker());

        JLabel logo2 = new JLabel("...");
        logo2.setForeground(Color.BLUE.darker().darker());
        bpane.add(logo2, BorderLayout.WEST);
        bpane.add(logo1, BorderLayout.SOUTH);
        sidepane.add(bpane, BorderLayout.SOUTH);
        mainpane.add(sidepane, BorderLayout.WEST);
        try {
            ResultSet rs = st.executeQuery("select Company_Name from companytable");
            while (rs.next()) {
                companyNameText.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertMedicine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actionPerformed(ActionEvent e) {

        String productName = productNameText.getText();
        String productQuantity = productQuantityText.getText();
        Integer a = new Integer(productQuantity);
        String productPrice = productPriceText.getText();
        String companyName = companyNameText.getSelectedItem().toString();

        String addcategory = addCategoryText.getText();
        Integer b = new Integer(productPrice);
        try {
            con = DbConnection();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from producttable");

            if (addcategory.equals("") && addcategory.startsWith(" ")) {
                String productCategory = productCategoryText.getSelectedItem().toString();
                rs.moveToInsertRow();
                rs.updateString("Product_Name", productName);
                rs.updateInt("Quantity", a);
                rs.updateInt("Price_Per_Unit", b);
                rs.updateString("Company_Name", companyName);
                //rs.updateString("Product_Category", productCategory);
                rs.insertRow();
                JOptionPane.showMessageDialog(null, "Record updated successfully!");
            } else {
                rs.moveToInsertRow();
                rs.updateString("Product_Name", productName);
                rs.updateInt("Quantity", a);
                rs.updateInt("Price_Per_Unit", b);
                rs.updateString("Company_Name", companyName);
                rs.updateString("Product_Category", addcategory);
                rs.insertRow();
                JOptionPane.showMessageDialog(null, "Record updated successfully!");
            }
            st.close();
            rs.close();
            String ToDB = "select * from producttable";
            st = con.createStatement();
            rs = st.executeQuery(ToDB);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex);

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

}
