package MedManagement;

import static MedManagement.MedManagement.st;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class changePassword extends MedManagement implements ActionListener {

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));
    JPanel centerpane, toppane, sidepane, rightpane, loginpane;
    JPanel mainpane = new JPanel(new BorderLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc;
    JTextField currentPassText, newPassText, retypePassText;
    JComboBox userNameText;

    public changePassword() {

        Init();
        updateUserName();

    }

    public void Init() {
        changePasswordFrame = new JFrame();
        changePasswordFrame.setSize(1320, 730);
        changePasswordFrame.setVisible(true);
        changePasswordFrame.setTitle("Medical Store Management");
        changePasswordFrame.add(mainpane);
        changePasswordFrame.setBackground(Color.BLUE.darker().darker());
        changePasswordFrame.setLocationRelativeTo(null);
        changePasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JPanel v = new JPanel(new GridBagLayout());
        v.setBackground(Color.BLUE.darker().darker());

        JLabel userName = new JLabel("User Name");
        userName.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        v.add(userName, gbc1);

        JLabel currentPass = new JLabel("Current Password:");
        currentPass.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        v.add(currentPass, gbc1);

        JLabel newPass = new JLabel("New Password:");
        newPass.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        v.add(newPass, gbc1);

        JLabel retypePass = new JLabel("Retype Password:");
        retypePass.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        v.add(retypePass, gbc1);

        JLabel space = new JLabel("              ");
        space.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        v.add(space, gbc1);

        userNameText = new JComboBox();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 0;
        v.add(userNameText, gbc1);

        currentPassText = new JPasswordField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 1;
        v.add(currentPassText, gbc1);

        newPassText = new JPasswordField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 2;
        v.add(newPassText, gbc1);

        retypePassText = new JPasswordField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 2;
        gbc1.gridy = 3;
        v.add(retypePassText, gbc1);

        JButton ChangePassword = new JButton("Change Password");
        ChangePassword.addActionListener(this);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 4;
        gbc1.gridy = 15;
        gbc1.ipadx = 50;
        gbc1.ipady = 0;
        v.add(ChangePassword, gbc1);

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
                changePasswordFrame.setVisible(false);
                DashboardFrame.setVisible(true);
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

    }

    public void actionPerformed(ActionEvent e) {

        String surName = userNameText.getSelectedItem().toString();
        String currentPass = currentPassText.getText();
        String newPass = newPassText.getText();
        String retypePass = retypePassText.getText();
        try {
            if (newPass.equals(retypePass) && !(currentPass.equals("")) && !(currentPass.startsWith(" "))) {

                Connection con = DbConnection();
                Statement st = con.createStatement();
                String stt = "update user set RETYPEPASSWORD = '" + retypePass + "', "
                        + "PASSWORD = '" + newPass + "' where USERNAME = '" + surName + "' and PASSWORD = '"
                        + currentPass + "'";

                st.executeUpdate(stt);
                JOptionPane.showMessageDialog(null, "Password Change Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Password do no match");
            }

        } catch (Exception ex) {

            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public ImageIcon resizeImage(ImageIcon Path) {
        Image image1 = Path.getImage();
        Image newImage = image1.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        return icon;
    }

    public void updateUserName() {
        if (createUser.isEnabled()) {
            try {
                ResultSet rs = st.executeQuery("select USERNAME from user");
                while (rs.next()) {
                    userNameText.addItem(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertMedicine.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            userNameText.addItem(loginNameText.getText());
        }

    }

}
