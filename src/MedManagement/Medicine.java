package MedManagement;

import static MedManagement.Dashboard.searchButton1;
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

public class Medicine extends MedManagement implements ActionListener {
    
    ImageIcon image = new ImageIcon(getClass().getResource("LOGO3.png"));
    private Vector<Vector<String>> data;
    private Vector<String> header;
    JTable table;
    JTextField searchBox;
    JLabel loginNameText;
    TableRowSorter<TableModel> sorter;
    JPanel mainpane, centerpane, toppane, sidepane, rightpane, loginpane,
            dashpane = new JPanel(new BorderLayout());
    
    public Medicine() {
        Init();
    }
    
    public Medicine(Connection con, Statement st, ResultSet rs) {
        
    }
    
    public void Init() {
        MedicineFrame = new JFrame();
        MedicineFrame.setSize(1320, 730);
        MedicineFrame.setVisible(true);
        MedicineFrame.setTitle("Medical Store Management");
        MedicineFrame.add(dashpane);
        MedicineFrame.setBackground(Color.BLUE.darker().darker());
        MedicineFrame.setLocationRelativeTo(null);
        MedicineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        // This panel is responsible for the middle button
        JPanel middleSidePane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(10, 50, 10, 10);
        gbc3.weightx = 0.01;
        
        JButton med = new JButton("Insert Medicines");
        med.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        middleSidePane.add(med, gbc3);
        
        JButton updateMed = new JButton("Update Medicines");
        updateMed.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        middleSidePane.add(updateMed, gbc3);
        
        JButton deleteMed = new JButton("Delete Medicines");
        deleteMed.addActionListener(this);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        middleSidePane.add(deleteMed, gbc3);
        
        middleSidePane.setBackground(Color.BLUE.darker().darker());
        rightpane.add(middleSidePane);

        // Panel for the down button (Create a User and Log out Button)
        sidepane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.insets = new Insets(31, 50, 10, 10);
        gbc2.weightx = 0.01;
        
        sidepane.setBackground(Color.BLUE.darker().darker());
        JButton back = new JButton("Back");
        back.addActionListener(this);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        sidepane.add(back, gbc2);

//		Button b1 = new Button("Log Out");
//		gbc2.fill = GridBagConstraints.HORIZONTAL;
//		gbc2.gridx = 0;
//		gbc2.gridy = 3;
//		sidepane.add(b1, gbc2);
        rightpane.add(sidepane);
        dashpane.add(rightpane, BorderLayout.WEST);

        //final JDialog d = new JDialog(MedicineFrame,"kjh",true);
    }
    
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Insert Medicines":
                MedicineFrame.setVisible(false);
                try {
                    InsertMedicine medi = new InsertMedicine();
                    medi.setName(loginNameText.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                break;
            
            case "Update Medicines":
                MedicineFrame.setVisible(false);
                updateMedicine medi1 = new updateMedicine();
                medi1.setName(loginNameText.getText());
                break;
            
            case "Delete Medicines":
                //MedicineFrame.setVisible(false);
                del1();
                break;
            
            case "Back":
                MedicineFrame.setVisible(false);
                //Dashboard d = new Dashboard();
                DashboardFrame.setVisible(true);
                //d.setName(loginNameText.getText());
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
    
    public void setName(String name) {
        loginNameText.setText(name.toUpperCase());
    }
    
    public void setuserType(String name) {
        loginNameText.setText(name.toUpperCase());
    }
    
    public void update() {
        
        try {
            
            ResultSet data = getProductTable();
            table = new JTable(DbUtils.resultSetToTableModel(data));
            sorter = new TableRowSorter<TableModel>(table.getModel());
            table.setRowSorter(sorter);
            table.setDragEnabled(false);
            table.setEnabled(false);
            JScrollPane scroll = new JScrollPane(table);
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
    
    private void del1() {
        
        ButtonGroup bg;
        final JDialog pane = new JDialog(MedicineFrame, "Select a product Name", true);
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
            ResultSet rs = st.executeQuery("select Product_Name from producttable");
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
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBox.setText(text.getSelectedItem().toString());
            }
        });
        
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
        // JLabel match = new JLabel("Match Case");
        final JButton matchcase = new JButton("Refresh Table");
        pane.add(paneCase);
        paneCase.add(matchcase);
        //paneCase.add(match);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String text1 = text.getSelectedItem().toString();
                    if (text1.equals(null)) {
                        JOptionPane.showMessageDialog(null, "Empty Record");
                    } else {
                        Connection con1 = DbConnection();
                        Statement pre = con1.createStatement();
                        String stt = "delete from producttable where Product_Name = '" + text1 + "'";
                        pre.executeUpdate(stt);
                        JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
                        con1.close();
                        pre.close();
                        update();
                    }
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
        matchcase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    ResultSet data = getProductTable();
                    table = new JTable(DbUtils.resultSetToTableModel(data));
                    JScrollPane scroll = new JScrollPane(table);
                    centerpane.add(scroll, BorderLayout.CENTER);
                    
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        pane.pack();
        pane.setVisible(true);
        
    }

   // public static void main(String[] a) {
    //  new Medicine();
    //}
}
