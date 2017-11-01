package MedManagement;

import static MedManagement.Dashboard.searchButton1;
import MedManagement.MedManagement;
import static MedManagement.MedManagement.MedicineFrame;
import static MedManagement.MedManagement.con;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class Companies extends MedManagement implements ActionListener {

    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));
    private Vector<Vector<String>> data;
    private Vector<String> header;
    JTable table;
    TableRowSorter<TableModel> sorter;
    JTextField searchBox;
    JPanel mainpane, centerpane, toppane, sidepane, rightpane, loginpane,
            dashpane = new JPanel(new BorderLayout());
    JLabel loginNameText;

    public Companies() {
        Init();
    }

    public Companies(Connection con, Statement st, ResultSet rs) {

    }

    public void Init() {
        Companiesframe = new JFrame();
        Companiesframe.setSize(1330, 740);
        Companiesframe.setVisible(true);
        Companiesframe.setTitle("Medical Store Management");
        Companiesframe.add(dashpane);
        //Companiesframe.setAlwaysOnTop(true);
        //Companiesframe.setExtendedState(Frame.MAXIMIZED_BOTH);
        Companiesframe.setBackground(Color.BLUE.darker().darker());
        Companiesframe.setLocationRelativeTo(null);
        Companiesframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // this is the top panel for search
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
        y1.setBackground(Color.BLUE.darker().darker());
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        y1.add(searchButton, BorderLayout.WEST);
        gbc0.gridx = 9;
        gbc0.gridy = 1;
        toppane.add(y1, gbc0);

        searchButton1 = new JButton("Show Table");
        searchButton1.addActionListener(this);
        searchButton1.setEnabled(true);
        y1.add(searchButton1, BorderLayout.EAST);

        JPanel pa = new JPanel(new BorderLayout());
        pa.setBackground(Color.BLUE.darker().darker());
        JLabel loginText = new JLabel("You as Logged in as: ");
        // gbc0.insets = new Insets(5, 90, 0, 40);
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        gbc0.gridx = 8;
        gbc0.gridy = 3;
        gbc0.ipady = 5;
        pa.add(loginText, BorderLayout.EAST);
        toppane.add(pa, gbc0);

        loginNameText = new JLabel("Solomon");
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

        //JTextArea t = new JTextArea();
        //JScrollPane scroll = new JScrollPane(table);
        // centerpane.add(scroll, BorderLayout.CENTER);
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

        // This panel is responsible for the middle button
        JPanel middleSidePane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(10, 50, 10, 10);
        gbc3.weightx = 0.01;

        JButton company = new JButton("Insert Company");
        company.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        middleSidePane.add(company, gbc3);

        JButton updateCompany = new JButton("Update Companies");
        updateCompany.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        middleSidePane.add(updateCompany, gbc3);

        JButton deleteCompany = new JButton("Delete Companies");
        deleteCompany.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        middleSidePane.add(deleteCompany, gbc3);

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

        rightpane.add(sidepane);
        dashpane.add(rightpane, BorderLayout.WEST);

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Insert Company":
                Companiesframe.setVisible(false);
                InsertCompany medi = new InsertCompany();
                medi.setName(loginNameText.getText());
                break;
            case "Update Companies":
                Companiesframe.setVisible(false);
                updateCompany medi1 = new updateCompany();
                medi1.setName(loginNameText.getText());
                break;

            case "Delete Companies":
                del1();
                break;

            case "Back":
                Companiesframe.setVisible(false);
                DashboardFrame.setVisible(true);
                //d.setName(loginNameText.getText());
                break;

            case "Search":
                searchMethod();
                break;

            case "Show Table":
                try {

                    con = DbConnection();
                    PreparedStatement pre = con.prepareStatement("select * from companytable");
                    // pre.setString(1, searchBox.getText());
                    ResultSet rs = pre.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    if (con != null) {
                        con.close();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);

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

    public void update() {

        try {
            data = getCompanyTable();

            header = new Vector<String>();
            header.add("Company_ID");
            header.add("Company_Names");
            header.add("Company_Country");
            header.add("Company_Email");
            header.add("Company_Contact_Number");
            header.add("Company_Address");

            table = new JTable(data, header);
            sorter = new TableRowSorter<TableModel>(table.getModel());
            table.setRowSorter(sorter);
            table.setDragEnabled(false);
            table.setEnabled(false);
            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(table);
            centerpane.add(scroll, BorderLayout.CENTER);

            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchMethod() {

        String text = searchBox.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(text));
        }

    }

    public void setName(String name) {
        loginNameText.setText(name.toUpperCase());
    }

    private void del1() {

        ButtonGroup bg;
        final JDialog pane = new JDialog(Companiesframe, "Select a product Name", false);
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
            ResultSet rs = st.executeQuery("select Company_Name from companytable");
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
        JLabel match = new JLabel("Match Case");
        final JCheckBox matchcase = new JCheckBox();
        pane.add(paneCase);
        paneCase.add(matchcase);
        paneCase.add(match);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String text1 = text.getSelectedItem().toString();

                    Connection con1 = DbConnection();
                    Statement pre = con1.createStatement();
                    String stt = "delete from companytable where Company_Name = '" + text1 + "'";
                    pre.executeUpdate(stt);
                    JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
                    con1.close();
                    pre.close();
                    update();
                } catch (Exception ex) {
                    Logger.getLogger(Medicine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        pane.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                update();
                centerpane.repaint(1000);
            }
        }
        );

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            }
        });

        pane.pack();
        pane.setVisible(true);

    }

}
