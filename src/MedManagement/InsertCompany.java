package MedManagement;

import static MedManagement.MedManagement.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class InsertCompany extends MedManagement implements ActionListener {
    //Dashboard d = new Dashboard();

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));

    JLabel loginNameText = new JLabel();

    JPanel centerpane, toppane, sidepane, rightpane, loginpane;
    JPanel mainpane = new JPanel(new BorderLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc;
    CardLayout cardlayout = new CardLayout();
    public static JTextField companyNameText, companyContactText, companyAddressText, companyCountryText, companyEmailText;

    String name;

    public InsertCompany() {
        Init();

    }

    public void Init() {
        InsertCompanyFrame = new JFrame();
        InsertCompanyFrame.setSize(1320, 730);
        InsertCompanyFrame.setVisible(true);
        InsertCompanyFrame.setTitle("Medical Store Management");
        InsertCompanyFrame.add(mainpane);
        InsertCompanyFrame.setBackground(Color.BLUE.darker().darker());
        InsertCompanyFrame.setLocationRelativeTo(null);
        InsertCompanyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JLabel productName = new JLabel("COMPANY NAME");
        productName.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        v.add(productName, gbc1);

        JLabel companyName = new JLabel("COMPANY COUNTRY:");
        companyName.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        v.add(companyName, gbc1);

        JLabel productCategory = new JLabel("COMPANY EMAIL:");
        productCategory.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        v.add(productCategory, gbc1);

        JLabel productQuantity = new JLabel("COMPANY CONTACT NO.:");
        productQuantity.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        v.add(productQuantity, gbc1);

        JLabel productPrice = new JLabel("COMPANY ADDRESS:");
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

        companyNameText = new JTextField(12);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 0;
        v.add(companyNameText, gbc1);

        companyCountryText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 1;
        v.add(companyCountryText, gbc1);

        companyEmailText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 2;
        v.add(companyEmailText, gbc1);

        companyContactText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 3;
        v.add(companyContactText, gbc1);

        companyAddressText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 4;
        v.add(companyAddressText, gbc1);

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
                InsertCompanyFrame.setVisible(false);
                Companiesframe.setVisible(true);
                //Companies d = new Companies();
                // d.setName(loginNameText.getText());
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

    }

    public void actionPerformed(ActionEvent e) {
        try {
            String companyName = companyNameText.getText();
            String companyCountry = companyCountryText.getText();
            String companyAddress = companyAddressText.getText();
            String companyContact = companyContactText.getText();
            String companyEmail = companyEmailText.getText();

            con = DbConnection();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from companytable");

            rs.moveToInsertRow();
            rs.updateString("Company_Name", companyName);
            rs.updateString("Company_Country", companyCountry);
            rs.updateString("Company_Email", companyEmail);
            rs.updateString("Company_Contact_Number", companyContact);
            rs.updateString("Company_Address", companyAddress);
            rs.insertRow();
            JOptionPane.showMessageDialog(null, "Record updated successully!");

            st.close();
            rs.close();
            String ToDB = "select * from companytable";
            st = con.createStatement();
            rs = st.executeQuery(ToDB);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex);

        } catch (Exception ex) {
            Logger.getLogger(InsertCompany.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ImageIcon resizeImage(ImageIcon Path) {
        Image image1 = Path.getImage();
        Image newImage = image1.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        return icon;
    }

    @Override
    public void setName(String name) {
        loginNameText.setText(name.toUpperCase());
    }

//    public static void main(String[] v) {
//        new InsertCompany();
//    }

}
