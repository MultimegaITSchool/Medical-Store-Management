package MedManagement;

import static MedManagement.MedManagement.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class createANewUser extends MedManagement implements ActionListener {
    //Dashboard d = new Dashboard();

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));

    JLabel loginNameText = new JLabel();
    

    JPanel centerpane, toppane, sidepane, rightpane, loginpane;
    JPanel mainpane = new JPanel(new BorderLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc;
    CardLayout cardlayout = new CardLayout();

    JTextField userNameText, repeatPassText, emailText, contactNumberText, newPassText, addressText;

    JComboBox userTypeText;
    String name;

    public createANewUser() {
        Init();

    }

    public void Init() {
        createAUserFrame = new JFrame();
        createAUserFrame.setSize(1320, 730);
        createAUserFrame.setVisible(true);
        createAUserFrame.setTitle("Medical Store Management");
        createAUserFrame.add(mainpane);
        createAUserFrame.setBackground(Color.BLUE.darker().darker());
        createAUserFrame.setLocationRelativeTo(null);
        createAUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        //gbc1.weightx = 0.01;

        
        JPanel v = new JPanel(new GridBagLayout());
        v.setBackground(Color.BLUE.darker().darker());

        JLabel userName = new JLabel("User Name");
        userName.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        v.add(userName, gbc1);

        JLabel newPass = new JLabel("New Password:");
        newPass.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        v.add(newPass, gbc1);

        JLabel repeatPass = new JLabel("Repeat Password:");
        repeatPass.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        v.add(repeatPass, gbc1);

        JLabel userType = new JLabel("User Type:");
        userType.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        v.add(userType, gbc1);

        JLabel email = new JLabel("Email:");
        email.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 4;
        v.add(email, gbc1);

        JLabel contactNumber = new JLabel("Contact Number:");
        contactNumber.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 5;
        v.add(contactNumber, gbc1);

        JLabel address = new JLabel("Address:");
        address.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 6;
        v.add(address, gbc1);

        JLabel space = new JLabel("              ");
        space.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        v.add(space, gbc1);

        userNameText = new JTextField(12);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 0;
        v.add(userNameText, gbc1);

        newPassText = new JPasswordField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 1;
        v.add(newPassText, gbc1);

        repeatPassText = new JPasswordField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 2;
        v.add(repeatPassText, gbc1);

        userTypeText = new JComboBox();
        userTypeText.addItem("Admin");
        userTypeText.addItem("User");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 3;
        v.add(userTypeText, gbc1);

        emailText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 4;
        v.add(emailText, gbc1);

        contactNumberText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 5;
        v.add(contactNumberText, gbc1);

        addressText = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 6;
        v.add(addressText, gbc1);

        JButton CreateAccount = new JButton("Create Account");
        CreateAccount.addActionListener(this);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 4;
        gbc1.gridy = 9;
        gbc1.ipadx = 100;
        gbc1.ipady = 0;
        v.add(CreateAccount, gbc1);

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
                createAUserFrame.setVisible(false);
                DashboardFrame.setVisible(true);
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

            String userName = userNameText.getText().toLowerCase();
            String repeatPass = repeatPassText.getText();
            String email = emailText.getText().toLowerCase();
            String userType = userTypeText.getSelectedItem().toString().toLowerCase();
            String contactNumber = contactNumberText.getText();
            String newPass = newPassText.getText();
            String address = addressText.getText();

            Statement pre = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pre.executeQuery("select * from user");
            String dbUserName = null;
            String dbEmail = null;

            while (rs.next()) {
                dbUserName = rs.getString("USERNAME");
                dbEmail = rs.getString("EMAIL");
            }
            if (userName.equals(dbUserName) || email.equals(dbEmail)) {
                JOptionPane.showMessageDialog(null, "User Already Exist!");
            } else {
                rs.moveToInsertRow();
                rs.updateString("USERNAME", userName);
                rs.updateString("RETYPEPASSWORD", repeatPass);
                rs.updateString("EMAIL", email);
                rs.updateString("USERTYPE", userType);
                rs.updateString("PHONE", contactNumber);
                rs.updateString("PASSWORD", newPass);
                rs.updateString("ADDRESS", address);
                rs.insertRow();
                JOptionPane.showMessageDialog(null, "New User Created successfully!");

                st.close();
                rs.close();
                String ToDB = "select * from user";
                st = con.createStatement();
                rs = st.executeQuery(ToDB);

            }
        } catch (Exception ex) {

            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
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

  //  public static void main(String[] args) {
    //  new createANewUser();
    // }
}
