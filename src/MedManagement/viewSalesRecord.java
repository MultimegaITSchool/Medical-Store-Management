package MedManagement;

import static MedManagement.MedManagement.MedicineFrame;
import static MedManagement.MedManagement.viewSalesRecordFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class viewSalesRecord extends MedManagement implements ActionListener {

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));

    JPanel mainpane, centerpane, toppane, sidepane, rightpane, loginpane,
            dashpane = new JPanel(new BorderLayout());
    
    TableRowSorter<TableModel> sorter;
    JLabel loginNameText = new JLabel();
    JTextField searchBox1;
    JTextField loginText;

    public viewSalesRecord() {
        Init();
    }

    public viewSalesRecord(Connection con, Statement st, ResultSet rs) {

    }

    public void Init() {
        viewSalesRecordFrame = new JFrame();
        viewSalesRecordFrame.setSize(1320, 730);
        viewSalesRecordFrame.setVisible(true);
        viewSalesRecordFrame.setTitle("Medical Store Management");
        viewSalesRecordFrame.add(dashpane);
        viewSalesRecordFrame.setBackground(Color.BLUE.darker().darker());
        viewSalesRecordFrame.setLocationRelativeTo(null);
        viewSalesRecordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // this is the top panel for search
        toppane = new JPanel(new GridBagLayout());
        toppane.setBackground(Color.BLUE.darker().darker());
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.insets = new Insets(15, 10, 10, 30);
        gbc0.weightx = 0.01;

        JPanel y = new JPanel(new BorderLayout());
        y.setBackground(Color.BLUE.darker().darker());
        searchBox1 = new JTextField(60);
        y.add(searchBox1, BorderLayout.EAST);
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.gridx = 8;
        gbc0.gridy = 1;
        toppane.add(y, gbc0);

        JPanel y1 = new JPanel(new BorderLayout());
        y1.setBackground(Color.BLUE.darker().darker());
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        y1.add(searchButton, BorderLayout.WEST);
        gbc0.gridx = 9;
        gbc0.gridy = 1;
        toppane.add(y1, gbc0);

        JPanel pa = new JPanel(new BorderLayout());
        pa.setBackground(Color.BLUE.darker().darker());
        loginText = new JTextField(40);
        // gbc0.insets = new Insets(5, 90, 0, 40);
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.gridx = 8;
        gbc0.gridy = 3;
        gbc0.ipady = 5;
        pa.add(loginText, BorderLayout.EAST);
        toppane.add(pa, gbc0);

        JButton loginNameText = new JButton("Search By Date");
        loginNameText.addActionListener(this);
        gbc0.gridx = 9;
        gbc0.gridy = 3;
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

        try {
            ResultSet data = getSalesTable();
            table1 = new JTable(DbUtils.resultSetToTableModel(data));
            sorter = new TableRowSorter<TableModel>(table1.getModel());
            table1.setRowSorter(sorter);
            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(table1);
            centerpane.add(scroll, BorderLayout.CENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //dashpane.add(Box.createGlue());
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

        // This panel is responsible for the middle button
        JPanel middleSidePane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(10, 50, 10, 10);
        gbc3.weightx = 0.01;

        JButton deleteRecords = new JButton("Delete Records");
        deleteRecords.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        middleSidePane.add(deleteRecords, gbc3);

        middleSidePane.setBackground(Color.BLUE.darker().darker());
        rightpane.add(middleSidePane);

        // Panel for the down button (Create a User and Log out JButton)
        sidepane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.insets = new Insets(31, 50, 10, 10);
        gbc2.weightx = 0.01;

        sidepane.setBackground(Color.BLUE.darker().darker());
        JButton b = new JButton("Back");
        b.addActionListener(this);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        sidepane.add(b, gbc2);

//		JButton b1 = new JButton("Log Out");
//		gbc2.fill = GridBagConstraints.HORIZONTAL;
//		gbc2.gridx = 0;
//		gbc2.gridy = 3;
//		sidepane.add(b1, gbc2);
        rightpane.add(sidepane);
        dashpane.add(rightpane, BorderLayout.WEST);

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Delete Records":
                // viewSalesRecordFrame.setVisible(false);
                del1();
                break;

            case "Back":
                viewSalesRecordFrame.setVisible(false);
                DashboardFrame.setVisible(true);
                //d.setName(loginNameText.getText());
                break;
            case "Search":
                searchMethod();
                break;
            case "Search By Date":
                searchBy();
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

    private void del1() {

        ButtonGroup bg;
        final JDialog pane = new JDialog(viewSalesRecordFrame, "Select a product Name", true);
        final JComboBox text;
        pane.setResizable(false);
        //pane.setTitle("Select a product Name");
        pane.setSize(400, 150);
        pane.setLayout(new FlowLayout());
        pane.setLocation(getX() + 100, getY() + 200);
        pane.setLocationRelativeTo(null);
        pane.setModal(false);

        JPanel paneLabel = new JPanel();
        pane.add(paneLabel);
        paneLabel.setVisible(true);

        JLabel deleteWhat = new JLabel("Delete What");
        deleteWhat.setLocation(2, 2);
        text = new JComboBox();
        try {
            Statement st = DbConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select Product_Name from salestable");
            while (rs.next()) {
                text.addItem(rs.getString(1));

            }
        } catch (Exception e) {

        }
        text.setPreferredSize(new Dimension(200, 25));

        paneLabel.add(deleteWhat);
        paneLabel.add(text);

        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        paneButton.setVisible(true);
        paneButton.setLocation(getX() - 20, getY() - 20);
        paneButton.setBorder(new TitledBorder("Buttons"));
        pane.add(paneButton);

        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        paneButton.add(deleteButton);
        paneButton.add(cancelButton);

        JPanel paneRadio = new JPanel();
        pane.add(paneRadio);
        paneRadio.setLocation(50, 30);
        paneRadio.setBorder(new TitledBorder("Search By:"));
        bg = new ButtonGroup();
        JRadioButton ID = new JRadioButton("ID", false);
        JRadioButton name = new JRadioButton("Name", true);
        paneRadio.add(ID);
        paneRadio.add(name);
        bg.add(ID);
        bg.add(name);

        JPanel paneCase = new JPanel();
        //JLabel match = new JLabel("Match Case");
        final JButton matchcase = new JButton("Refresh Table");
        pane.add(paneCase);
        paneCase.add(matchcase);
        // paneCase.add(match);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String text1 = text.getSelectedItem().toString();

                    Connection con1 = DbConnection();
                    Statement pre = con1.createStatement();
                    String stt = "delete from salestable where Product_Name = '" + text1 + "'";
                    pre.executeUpdate(stt);
                    JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
                    con1.close();
                    pre.close();
                    update();
                    //Statement st = DbConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // ResultSet rs = st.executeQuery("select Product_Name from salestable");
                    //while (rs.next()) {
                    //text.addItem(rs.getString(1));

                    // }
                } catch (Exception ex) {
                    Logger.getLogger(Medicine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        pane.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                pane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }
        }
        );

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }
        });
        matchcase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });

        pane.pack();
        pane.setVisible(true);

    }

    public void update() {
        try {

            con = DbConnection();
            PreparedStatement pre = con.prepareStatement("select * from salestable");
            ResultSet rs = pre.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void searchMethod() {

        String text = searchBox1.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(text));
        }
    }

    private void searchBy() {
        String text = loginText.getText();
        //text.
        long te = Long.valueOf(text);
        Date d = new Date(te);
        
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL, d, null));
        }
    }
}
