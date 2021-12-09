package showroom_management_system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usamaasif
 */
public class UsedCarRecord extends javax.swing.JFrame {

    /**
     * Creates new form UsedCarRecord
     */
    showroomManagementSystem app = new showroomManagementSystem();
    Connection conn = app.getConnection();
    PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;
    ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6;
    private int oldemployeeid;
    private String oldstatus;
    private int emp_id;

    public UsedCarRecord() {
        initComponents();
        updatetable();
        txtemployeeid.setEnabled(false);
        txtbuyerclientid.setEnabled(false);
    }

    public UsedCarRecord(int emp_id) {
        initComponents();
        txtemployeeid.setEnabled(false);
        txtbuyerclientid.setEnabled(false);
        this.emp_id = emp_id;
        updatetable();
    }

    private void updatetable() {
        try {
            ps = conn.prepareStatement("select * from used_cars");
            rs = ps.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            int j = rsd.getColumnCount();
            DefaultTableModel dft = (DefaultTableModel) usedCarsTable.getModel();
            dft.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 0; i <= j; i++) {
                    v2.add(rs.getString("chassis_no"));
                    v2.add(rs.getString("model"));
                    v2.add(rs.getString("engine_no"));
                    v2.add(rs.getString("manufactured_year"));
                    v2.add(rs.getString("cost_price"));
                    v2.add(rs.getString("sale_price"));
                    v2.add(rs.getString("Employee_id"));
                    v2.add(rs.getString("buyer_client_id"));
                    v2.add(rs.getString("statusOfCar"));
                }
                dft.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCarSold(int employeeID, String newstatus) {
        int oldcarsold = 0;
        int newcarsold = 0;
        try {
            String query = "update employees set CarsSold = ? where employee_id = ?";
            ps1 = conn.prepareStatement(query);
            String query2 = "select CarsSold from employees where employee_id = ?";
            ps2 = conn.prepareStatement(query2);
            ps2.setInt(1, employeeID);
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                oldcarsold = rs2.getInt(1);
                // on insert
                if (newstatus.equals("Sold")) {
                    newcarsold = oldcarsold + 1;
                } else {
                    newcarsold = oldcarsold;
                }
            }
            ps1.setInt(1, newcarsold);
            ps1.setInt(2, employeeID);
            ps1.executeUpdate();
            ps1.close();
            ps2.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onupdateCarSold(int newemployeeID, String newstatus) {
        int oldcarsold = 0;
        int newcarsold = 0;
        try {
            String query = "update employees set CarsSold = ? where employee_id = ?";
            ps1 = conn.prepareStatement(query);
            String query5 = "update employees set CarsSold = ? where employee_id = ?";
            ps2 = conn.prepareStatement(query5);
            String query2 = "select CarsSold from employees where employee_id = ?";
            ps3 = conn.prepareStatement(query2);
            String query3 = "select CarsSold from employees where employee_id = ?";
            ps4 = conn.prepareStatement(query3);
            ps3.setInt(1, newemployeeID);
            ps4.setInt(1, oldemployeeid);
            rs3 = ps3.executeQuery();
            rs4 = ps4.executeQuery();
            if (rs3.next()) {
                newcarsold = rs3.getInt(1);
            }
            if (rs4.next()) {
                oldcarsold = rs4.getInt(1);
            }
            // on update
            if (!(oldemployeeid == newemployeeID) && (newstatus.equals("Sold"))) {
                oldcarsold = oldcarsold - 1;
                //System.out.println("first");
                //System.out.println(oldcarsold);
                newcarsold = newcarsold + 1;
                //System.out.println(newcarsold);
            }
            if ((oldemployeeid == newemployeeID) && (newstatus.equals("UnSold"))) {
                oldcarsold = oldcarsold - 1;
                //System.out.println("second");
                //System.out.println(oldcarsold);
                newcarsold = newcarsold - 1;
                //System.out.println(newcarsold);
            }
            if (!(oldemployeeid == newemployeeID) && (newstatus.equals("UnSold"))) {
                //System.out.println("third");
                oldcarsold = oldcarsold - 1;
                //System.out.println(oldcarsold);
                //System.out.println(newcarsold);
            }
            ps1.setInt(1, newcarsold);
            ps1.setInt(2, newemployeeID);
            ps1.executeUpdate();
            ps2.setInt(1, oldcarsold);
            ps2.setInt(2, oldemployeeid);
            ps2.executeUpdate();
            ps1.close();
            ps2.close();
            ps3.close();
            ps4.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCommission(int employeeID) {
        int carsold;
        try {
            String query = "update employees set Commission = ? where employee_id = ?";
            ps1 = conn.prepareStatement(query);
            String query1 = "select CarsSold from employees where employee_id = ?";
            ps2 = conn.prepareStatement(query1);
            ps2.setInt(1, employeeID);
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                carsold = rs2.getInt(1);
                //System.out.println(carsold);
                ps1.setInt(1, carsold * 10000);
                ps1.setInt(2, employeeID);
                ps1.executeUpdate();
            }
            ps1.close();
            ps2.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onupdateCommission(int employeeID) {
        int oldcarsold = 0;
        int newcarsold = 0;
        try {
            String query = "update employees set Commission = ? where employee_id = ?";
            ps1 = conn.prepareStatement(query);
            String query1 = "update employees set Commission = ? where employee_id = ?";
            ps2 = conn.prepareStatement(query1);
            String query2 = "select CarsSold from employees where employee_id = ?";
            ps3 = conn.prepareStatement(query2);
            String query3 = "select CarsSold from employees where employee_id = ?";
            ps4 = conn.prepareStatement(query3);
            ps3.setInt(1, employeeID);
            ps4.setInt(1, oldemployeeid);
            rs3 = ps3.executeQuery();
            rs4 = ps4.executeQuery();
            if (rs3.next()) {
                newcarsold = rs3.getInt(1);
                //System.out.println(carsold);
                ps1.setInt(1, newcarsold * 10000);
                ps1.setInt(2, employeeID);
                ps1.executeUpdate();
            }
            if (rs4.next()) {
                oldcarsold = rs4.getInt(1);
                ps2.setInt(1, oldcarsold * 10000);
                ps2.setInt(2, oldemployeeid);
                ps2.executeUpdate();
            }
            ps1.close();
            ps2.close();
            ps3.close();
            ps4.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ondeleteRecord(int employeeID) {
        int oldcarsold = 0;
        int newcarsold = 0;
        //String status = "Sold";
        try {
            String query = "update employees set CarsSold = ? where employee_id = ?";
            ps1 = conn.prepareStatement(query);
            String query2 = "select CarsSold from employees where employee_id = ?";
            ps2 = conn.prepareStatement(query2);
            ps2.setInt(1, employeeID);
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                oldcarsold = rs2.getInt(1);
                // on delete record
                newcarsold = oldcarsold - 1;
            }
            ps1.setInt(1, newcarsold);
            ps1.setInt(2, employeeID);
            ps1.executeUpdate();
            ps1.close();
            ps2.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean salesEmployeeID(int employee_id) {
        int dept_id = -1;
        try {
            String query = "select dept_id from employees where employee_id =?";
            ps6 = conn.prepareStatement(query);
            ps6.setInt(1, employee_id);
            rs6 = ps6.executeQuery();
            if (rs6.next()) {
                dept_id = rs6.getInt(1);
            }
            if (dept_id != 1) {
                JOptionPane.showMessageDialog(this, "Employee must belong to Sales Department!");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usedCarsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtchassisno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmodel = new javax.swing.JTextField();
        txtengineno = new javax.swing.JTextField();
        txtyear = new javax.swing.JTextField();
        txtcostprice = new javax.swing.JTextField();
        txtsaleprice = new javax.swing.JTextField();
        txtemployeeid = new javax.swing.JTextField();
        txtbuyerclientid = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtchassisno1 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        statusComboBox = new javax.swing.JComboBox<>();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 251, 252));

        usedCarsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ChassisNo", "Model", "EngineNo", "Year", "Cost Price", "Sale Price", "EmployeeID", "Buyer Client ID", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(usedCarsTable);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel1.setText("Chassis No");

        txtchassisno.setBackground(new java.awt.Color(222, 226, 230));
        txtchassisno.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel2.setText("Model");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel3.setText("Engine No");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel4.setText("Year");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel5.setText("Cost Price");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel6.setText("Sell Price");

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel7.setText("Employee ID");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel9.setText("Buyer Client ID");

        txtmodel.setBackground(new java.awt.Color(222, 226, 230));
        txtmodel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtmodel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmodelActionPerformed(evt);
            }
        });

        txtengineno.setBackground(new java.awt.Color(222, 226, 230));
        txtengineno.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtengineno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtenginenoActionPerformed(evt);
            }
        });

        txtyear.setBackground(new java.awt.Color(222, 226, 230));
        txtyear.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtyearActionPerformed(evt);
            }
        });

        txtcostprice.setBackground(new java.awt.Color(222, 226, 230));
        txtcostprice.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtcostprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcostpriceActionPerformed(evt);
            }
        });

        txtsaleprice.setBackground(new java.awt.Color(222, 226, 230));
        txtsaleprice.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtsaleprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsalepriceActionPerformed(evt);
            }
        });

        txtemployeeid.setBackground(new java.awt.Color(222, 226, 230));
        txtemployeeid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtemployeeid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemployeeidActionPerformed(evt);
            }
        });

        txtbuyerclientid.setBackground(new java.awt.Color(222, 226, 230));
        txtbuyerclientid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtbuyerclientid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuyerclientidActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(226, 51, 53));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img_ext/add (Custom).png"))); // NOI18N
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(226, 51, 53));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img_ext/refresh (1) (Custom).png"))); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(226, 51, 53));
        jButton7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img_ext/bin (Custom).png"))); // NOI18N
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel10.setText("Status");

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Chassis No");

        txtchassisno1.setBackground(new java.awt.Color(222, 226, 230));
        txtchassisno1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        jButton9.setBackground(new java.awt.Color(226, 51, 53));
        jButton9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search (Custom).png"))); // NOI18N
        jButton9.setText("Search");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(194, 194, 194))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(16, 16, 16))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/db project/walls/1/edits/toyota-logo.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Used Car Records");

        jButton1.setBackground(new java.awt.Color(226, 51, 53));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img_ext/left (Custom).png"))); // NOI18N
        jButton1.setText("Return");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UnSold", "Sold" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtengineno, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcostprice, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsaleprice, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtemployeeid, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbuyerclientid, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtengineno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtcostprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtsaleprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtemployeeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtbuyerclientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5)
                                    .addComponent(jButton6)
                                    .addComponent(jButton7))
                                .addGap(20, 20, 20)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtmodelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmodelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmodelActionPerformed

    private void txtenginenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtenginenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtenginenoActionPerformed

    private void txtyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtyearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtyearActionPerformed

    private void txtcostpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcostpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcostpriceActionPerformed

    private void txtsalepriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsalepriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsalepriceActionPerformed

    private void txtemployeeidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemployeeidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemployeeidActionPerformed

    private void txtbuyerclientidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuyerclientidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuyerclientidActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            ps = null;
            String query = "insert into used_cars (chassis_no, model, engine_no, manufactured_year, cost_price, sale_price, Employee_id, buyer_client_id, statusOfCar) values (?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            if ((txtchassisno.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Chassis No!");
            } else {
                ps.setString(1, txtchassisno.getText());
            }
            if ((txtmodel.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Model No!");
            } else {
                ps.setString(2, txtmodel.getText());
            }
            if ((txtengineno.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Engine No!");
            } else {
                ps.setString(3, txtengineno.getText());
            }
            if ((txtyear.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Year of Model!");
            } else {
                ps.setInt(4, Integer.parseInt(txtyear.getText()));
            }
            if ((txtcostprice.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Cost Price!");
            } else {
                ps.setInt(5, Integer.parseInt(txtcostprice.getText()));
            }
            if ((txtsaleprice.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Sell Price!");
            } else {
                ps.setInt(6, Integer.parseInt(txtsaleprice.getText()));
            }
            String status = statusComboBox.getSelectedItem().toString();
            if (status.equals("Sold") && !(txtemployeeid.getText().equals(""))) {
                if (salesEmployeeID(Integer.parseInt(txtemployeeid.getText()))) {
                    ps.setInt(7, Integer.parseInt(txtemployeeid.getText()));
                }
            }
            if (status.equals("Sold") && (txtemployeeid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID!");
            }
            if (status.equals("UnSold") && (!txtemployeeid.getText().equals(""))) {
                ps.setString(7, null);
            }
            if (status.equals("UnSold") && (txtemployeeid.getText().equals(""))) {
                ps.setString(7, null);
            }
            if (status.equals("Sold") && !(txtbuyerclientid.getText().equals(""))) {
                ps.setInt(8, Integer.parseInt(txtbuyerclientid.getText()));
            }
            if (status.equals("Sold") && (txtbuyerclientid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Client ID!");
            }
            if (status.equals("UnSold") && (!txtbuyerclientid.getText().equals(""))) {
                ps.setString(8, null);
            }
            if (status.equals("UnSold") && (txtbuyerclientid.getText().equals(""))) {
                ps.setString(8, null);
            }
            ps.setString(9, status);
            //System.out.println(status);
            int i = ps.executeUpdate();
            ps.close();
            if (i == 1) {
                if (!(txtemployeeid.getText().equals(""))) {
                    updateCarSold(Integer.parseInt(txtemployeeid.getText()), statusComboBox.getSelectedItem().toString());
                    updateCommission(Integer.parseInt(txtemployeeid.getText()));
                }
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Added!");
                txtchassisno.setText("");
                txtmodel.setText("");
                txtengineno.setText("");
                txtyear.setText("");
                txtcostprice.setText("");
                txtsaleprice.setText("");
                txtemployeeid.setText("");
                txtbuyerclientid.setText("");
                statusComboBox.setSelectedIndex(0);
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Added!");
                txtchassisno.setText("");
                txtmodel.setText("");
                txtengineno.setText("");
                txtyear.setText("");
                txtcostprice.setText("");
                txtsaleprice.setText("");
                txtemployeeid.setText("");
                txtbuyerclientid.setText("");
                statusComboBox.setSelectedIndex(0);
                txtchassisno.requestFocus();
            }
            //System.out.println("Inserted");
        } catch (SQLException ex) {
            if (ex.getMessage().split(" ")[0].equals("Duplicate")) {
                JOptionPane.showMessageDialog(this, "Chassis No must be unique!");
            }
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            ps = null;
            String query = "delete from used_cars where chassis_no = ?";
            ps = conn.prepareStatement(query);
            String chassis_no = txtchassisno.getText();
            ps.setString(1, chassis_no);
            int i = ps.executeUpdate();
            ps.close();
            //System.out.println("record deleted");
            if (i == 1) {
                ondeleteRecord(Integer.parseInt(txtemployeeid.getText()));
                updateCommission(Integer.parseInt(txtemployeeid.getText()));
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Deleted!");
                txtchassisno.setText("");
                txtmodel.setText("");
                txtengineno.setText("");
                txtyear.setText("");
                txtcostprice.setText("");
                txtsaleprice.setText("");
                txtemployeeid.setText("");
                txtbuyerclientid.setText("");
                statusComboBox.setSelectedIndex(0);
                txtchassisno.requestFocus();
            } else {
                txtchassisno.setText("");
                txtmodel.setText("");
                txtengineno.setText("");
                txtyear.setText("");
                txtcostprice.setText("");
                txtsaleprice.setText("");
                txtemployeeid.setText("");
                txtbuyerclientid.setText("");
                statusComboBox.setSelectedIndex(0);
                txtchassisno.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            ps = null;
            String query = "update used_cars set model = ?, engine_no = ?, manufactured_year = ?, cost_price = ?, sale_price = ?, Employee_id = ?, buyer_client_id = ?, statusOfCar = ? where chassis_no = ?";
            ps = conn.prepareStatement(query);
            if ((txtmodel.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Model No!");
            } else {
                ps.setString(1, txtmodel.getText());
            }
            if ((txtengineno.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Engine No!");
            } else {
                ps.setString(2, txtengineno.getText());
            }
            if ((txtyear.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Year of Model!");
            } else {
                ps.setInt(3, Integer.parseInt(txtyear.getText()));
            }
            if ((txtcostprice.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Cost Price!");
            } else {
                ps.setInt(4, Integer.parseInt(txtcostprice.getText()));
            }
            if ((txtsaleprice.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Sell Price!");
            } else {
                ps.setInt(5, Integer.parseInt(txtsaleprice.getText()));
            }
            String status = statusComboBox.getSelectedItem().toString();
            if (status.equals("Sold") && !(txtemployeeid.getText().equals(""))) {
                if (salesEmployeeID(Integer.parseInt(txtemployeeid.getText()))) {
                    ps.setInt(6, Integer.parseInt(txtemployeeid.getText()));
                }
            }
            if (status.equals("Sold") && (txtemployeeid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID!");
            }
            if (status.equals("UnSold") && (!txtemployeeid.getText().equals(""))) {
                ps.setString(6, null);
            }
            if (status.equals("Sold") && !(txtbuyerclientid.getText().equals(""))) {
                ps.setInt(7, Integer.parseInt(txtbuyerclientid.getText()));
            }
            if (status.equals("Sold") && (txtbuyerclientid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Client ID!");
            }
            if (status.equals("UnSold") && (!txtbuyerclientid.getText().equals(""))) {
                ps.setString(7, null);
            }
            ps.setString(8, status);
            int chassis_no = Integer.parseInt(txtchassisno.getText());
            ps.setInt(9, chassis_no);
            int i = ps.executeUpdate();
            ps.close();
            //System.out.println("record updated");
            if (i == 1) {
                onupdateCarSold(Integer.parseInt(txtemployeeid.getText()), statusComboBox.getSelectedItem().toString());
                onupdateCommission(Integer.parseInt(txtemployeeid.getText()));
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Update!");
                txtchassisno.setText("");
                txtmodel.setText("");
                txtengineno.setText("");
                txtyear.setText("");
                txtcostprice.setText("");
                txtsaleprice.setText("");
                txtemployeeid.setText("");
                txtbuyerclientid.setText("");
                statusComboBox.setSelectedIndex(0);
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Updated!");
                txtchassisno.setText("");
                txtmodel.setText("");
                txtengineno.setText("");
                txtyear.setText("");
                txtcostprice.setText("");
                txtsaleprice.setText("");
                txtemployeeid.setText("");
                txtbuyerclientid.setText("");
                statusComboBox.setSelectedIndex(0);
                txtchassisno.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try {
            ps = conn.prepareStatement("select * from used_cars where chassis_no = ?");
            String chassis_no = txtchassisno1.getText();
            ps.setString(1, chassis_no);
            rs = ps.executeQuery();
            if (rs.next() == true) {
                txtchassisno.setEnabled(false);
                txtchassisno.setText(rs.getString(1));
                txtmodel.setEnabled(false);
                txtmodel.setText(rs.getString(2));
                txtengineno.setEnabled(false);
                txtengineno.setText(rs.getString(3));
                txtyear.setEnabled(false);
                txtyear.setText(Integer.toString(rs.getInt(4)));
                txtcostprice.setEnabled(false);
                txtcostprice.setText(Integer.toString(rs.getInt(5)));
                txtsaleprice.setText(Integer.toString(rs.getInt(6)));
                oldemployeeid = rs.getInt(7);
                txtemployeeid.setText(Integer.toString(oldemployeeid));
                txtbuyerclientid.setText(Integer.toString(rs.getInt(8)));
                oldstatus = rs.getString(9);
                statusComboBox.setSelectedItem(oldstatus);
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Not Avalaible");
                txtchassisno.setText("");
                txtmodel.setText("");
                txtengineno.setText("");
                txtyear.setText("");
                txtcostprice.setText("");
                txtsaleprice.setText("");
                txtemployeeid.setText("");
                txtbuyerclientid.setText("");
                statusComboBox.setSelectedIndex(0);
                txtchassisno.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
        String status = statusComboBox.getSelectedItem().toString();
        if (status.equals("Sold")) {
            txtemployeeid.setEnabled(true);
            txtbuyerclientid.setEnabled(true);
        }
        if (status.equals("UnSold")) {
            txtemployeeid.setEnabled(false);
            txtbuyerclientid.setEnabled(false);
        }
    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int dept_id = -1;
        try {
            ps6 = conn.prepareStatement("select dept_id from employees where employee_id = ?");
            ps6.setInt(1, emp_id);
            rs6 = ps6.executeQuery();
            if (rs6.next()) {
                System.out.println(rs6.getString(1));
                dept_id = rs6.getInt("dept_id");

            }
            System.out.println(dept_id);
            ps6.close();
            if (dept_id == 0) {
                new adminDashboard(emp_id).setVisible(true);
                this.setVisible(false);
                //System.out.println(emp_id);
            } else if (dept_id == 1) {
                new SalePersonDashboard(emp_id).setVisible(true);
                this.setVisible(false);
                //System.out.println(emp_id);
            } else if (dept_id == 2) {
                int response = JOptionPane.showConfirmDialog(this, "Do you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    this.setVisible(false);
                    new mainPage().setVisible(true);
                }
            } else if (dept_id == 3) {
                new HR_dashboard(emp_id).setVisible(true);
                this.setVisible(false);
            } else {
                System.out.println("Error");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsedCarRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsedCarRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsedCarRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsedCarRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsedCarRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JTextField txtbuyerclientid;
    private javax.swing.JTextField txtchassisno;
    private javax.swing.JTextField txtchassisno1;
    private javax.swing.JTextField txtcostprice;
    private javax.swing.JTextField txtemployeeid;
    private javax.swing.JTextField txtengineno;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JTextField txtsaleprice;
    private javax.swing.JTextField txtyear;
    private javax.swing.JTable usedCarsTable;
    // End of variables declaration//GEN-END:variables
}
