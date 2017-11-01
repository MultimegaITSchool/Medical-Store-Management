package MedManagement;

import static MedManagement.MedManagement.LoginPageFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class LoginPage extends MedManagement implements ActionListener {

    //Dashboard d = new Dashboard();
    JOptionPane joptionpane = new JOptionPane();

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));

    JPanel centerpane, toppane, sidepane, rightpane, loginpane;
    JPanel mainpane = new JPanel(new GridBagLayout());
    GridBagConstraints gbc1 = new GridBagConstraints();
    GridBagConstraints gbc;
    CardLayout cardlayout = new CardLayout();

    JTextField UsernameField;
    JTextField passfield;
    String name;
    String userType = null;

    public LoginPage() {
        Init();

    }

    public void Init() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        GraphicsDevice[] gs = ge.getScreenDevices();
        for (int j = 0; j < gs.length; j++) {
            //System.out.println(gs.toString());
            GraphicsDevice gd = gs[j];

            GraphicsConfiguration[] gc = gd.getConfigurations();
            for (int i = 0; i < gc.length; i++) {

                LoginPageFrame = new JFrame();
                LoginPageFrame.setUndecorated(true);
                gd.setFullScreenWindow(LoginPageFrame);

                //LoginPageFrame.setSize(1320, 730);
                //LoginPageFrame.setVisible(true);
                LoginPageFrame.enableInputMethods(false);
                LoginPageFrame.setTitle("Medical Store Management");
                LoginPageFrame.setAlwaysOnTop(true);
                LoginPageFrame.add(mainpane);

                LoginPageFrame.setBackground(Color.BLUE.darker().darker());
                LoginPageFrame.setLocationRelativeTo(null);
                LoginPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        }
        run();
        mainpane.setBackground(Color.BLUE.darker().darker());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 50, 30);
        gbc.weightx = 0.001;

        centerpane = new JPanel(new BorderLayout());
        centerpane.setVisible(true);
        centerpane.setBackground(Color.BLUE.darker().darker());
        loginpane = new JPanel(new GridBagLayout());
        loginpane.setBackground(Color.BLUE.darker().darker());

        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(5, 10, 10, 10);
        //gbc1.weightx = 0.01;

        JPanel d = new JPanel(new GridLayout());
        d.setBackground(Color.BLUE.darker().darker());
        JLabel logo = new JLabel();
        d.setBorder(new MatteBorder(0, 0, 0, 2, Color.WHITE));
        logo.setIcon(image);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        d.add(logo);
        loginpane.add(d, gbc1);

        JPanel v = new JPanel(new GridBagLayout());
        v.setBackground(Color.BLUE.darker().darker());
        JLabel Username = new JLabel("UserName:");
        Username.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        v.add(Username, gbc1);

        UsernameField = new JTextField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        v.add(UsernameField, gbc1);

        JLabel pass = new JLabel("Password:");
        pass.setForeground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 2;
        v.add(pass, gbc1);

        passfield = new JPasswordField(12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 3;
        v.add(passfield, gbc1);

        JButton signin = new JButton("Sign In");
        signin.addActionListener(this);

        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(50, 10, 10, 10);
        gbc1.gridheight = 30;
        gbc1.gridx = 1;
        gbc1.gridy = 6;

        v.add(signin, gbc1);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 1;
        gbc1.gridy = 0;

        loginpane.add(v, gbc1);

        centerpane.add(loginpane);

        mainpane.add(centerpane, gbc);

        sidepane = new JPanel(new BorderLayout());

        sidepane.setBackground(Color.BLUE.darker().darker());
        gbc.gridx = 0;
        gbc.gridy = 7;
        JButton b = new JButton("Exit");

        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (JOptionPane.showConfirmDialog(null, "Are you sure you want exit") == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        sidepane.add(b, BorderLayout.WEST);
        mainpane.add(sidepane, gbc);

    }

    public void button() {

        try {
            name = UsernameField.getText().trim().toLowerCase();
            String pass = String.valueOf(passfield.getText().trim());

            String stt = "select USERNAME,PASSWORD,USERTYPE from user where USERNAME = ? and PASSWORD = ?";

            PreparedStatement pre = DbConnection().prepareStatement(stt);
            pre.setString(1, name);
            pre.setString(2, pass);
            ResultSet rs1 = pre.executeQuery();
            int count = 0;

            while (rs1.next()) {
                count++;
                userType = rs1.getString("USERTYPE");
            }

            if (count == 1 && userType.equals("user")) {
                LoginPageFrame.setVisible(false);
                Dashboard d = new Dashboard();
                d.setName(name);
                d.createUser.setEnabled(false);
            } else if (count == 1 && userType.equals("admin")) {
                LoginPageFrame.setVisible(false);
                Dashboard d = new Dashboard();
                d.setName(name);
            } else if (count == 0) {
                JOptionPane.showMessageDialog(null, "Wrong Password");
            }

        } catch (Exception ex) {

            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {
        String[] im = {"taskkill.exe", "/im", "explorer.exe"};
        Runtime run = Runtime.getRuntime();
        try {
            run.exec(im);
        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Sign In":
                button();
                break;

        }

    }

}
