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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author usamaasif
 */
public class BookingsRecord extends javax.swing.JFrame {

    /**
     * Creates new form BookingsRecord
     */
    showroomManagementSystem app = new showroomManagementSystem();
    Connection conn = app.getConnection();
    PreparedStatement ps, ps1, ps2, ps3, ps4, ps5;
    ResultSet rs, rs1, rs2, rs3, rs4, rs5;
    int oldemployeeid;
    private int emp_id;

    public BookingsRecord() {
        initComponents();
        updatetable();
        txtpaymentleft.setEnabled(false);
    }

    public BookingsRecord(int emp_id) {
        initComponents();
        this.emp_id = emp_id;
        updatetable();
        txtpaymentleft.setEnabled(false);
    }

    private void updatetable() {
        try {
            ps = conn.prepareStatement("select * from bookings");
            rs = ps.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            int j = rsd.getColumnCount();
            DefaultTableModel dft = (DefaultTableModel) bookingsTable.getModel();
            dft.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 0; i <= j; i++) {
                    v2.add(rs.getString("chassis_no"));
                    v2.add(rs.getString("Payment_Received"));
                    v2.add(rs.getString("payment_left"));
                    v2.add(rs.getString("delivery_date"));
                    v2.add(rs.getString("Cost_of_car"));
                    v2.add(rs.getString("client_id"));
                    v2.add(rs.getString("Employee_id"));
                }
                dft.addRow(v2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCarSold(int employeeID) {
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
                newcarsold = oldcarsold + 1;
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

    public void onupdateCarSold(int newemployeeID) {
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
            if (!(oldemployeeid == newemployeeID)) {
                oldcarsold = oldcarsold - 1;
                System.out.println("first");
                //System.out.println(oldcarsold);
                newcarsold = newcarsold + 1;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtchassisno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtpaymentreceived = new javax.swing.JTextField();
        txtpaymentleft = new javax.swing.JTextField();
        txtdeliverydate = new javax.swing.JTextField();
        txttotalcost = new javax.swing.JTextField();
        txtclientid = new javax.swing.JTextField();
        txtemployeeid = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtchassisno1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));

        bookingsTable.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        bookingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Chassis No", "Payment Received", "Payment Left", "Delivery Date", "Total Cost", "Client ID", "Employee ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        bookingsTable.setToolTipText("");
        jScrollPane1.setViewportView(bookingsTable);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Chassis No");

        txtchassisno.setBackground(new java.awt.Color(222, 226, 230));
        txtchassisno.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Payment Received");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Payment Left");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Delivery Date");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Total Cost");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Client ID");

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Employee ID");

        txtpaymentreceived.setBackground(new java.awt.Color(222, 226, 230));
        txtpaymentreceived.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        txtpaymentleft.setBackground(new java.awt.Color(222, 226, 230));
        txtpaymentleft.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtpaymentleft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaymentleftActionPerformed(evt);
            }
        });

        txtdeliverydate.setBackground(new java.awt.Color(222, 226, 230));
        txtdeliverydate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        txttotalcost.setBackground(new java.awt.Color(222, 226, 230));
        txttotalcost.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txttotalcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalcostActionPerformed(evt);
            }
        });

        txtclientid.setBackground(new java.awt.Color(222, 226, 230));
        txtclientid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        txtemployeeid.setBackground(new java.awt.Color(222, 226, 230));
        txtemployeeid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        jButton5.setBackground(new java.awt.Color(226, 51, 53));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add (Custom).png"))); // NOI18N
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(226, 51, 53));
        jButton6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update black (Custom).png"))); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(226, 51, 53));
        jButton7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngtree-delete-vector-icon-png-image_963323 (Custom).jpg"))); // NOI18N
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/db project/walls/1/edits/toyota-logo.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setForeground(new java.awt.Color(240, 240, 240));

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Chassis No");

        txtchassisno1.setBackground(new java.awt.Color(222, 226, 230));
        txtchassisno1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        jButton8.setBackground(new java.awt.Color(226, 51, 53));
        jButton8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search (Custom).png"))); // NOI18N
        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(208, 208, 208))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(226, 51, 53));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back (Custom).jpg"))); // NOI18N
        jButton1.setText("Return");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Booking Records ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtpaymentleft, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                            .addComponent(txtdeliverydate, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                            .addComponent(txtclientid, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                            .addComponent(txtemployeeid, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                            .addComponent(txtpaymentreceived)
                                            .addComponent(txtchassisno)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txttotalcost, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton6)
                                .addGap(31, 31, 31)
                                .addComponent(jButton7)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(228, 228, 228)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txttotalcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtpaymentreceived, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpaymentleft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtdeliverydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtclientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtemployeeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)
                            .addComponent(jButton7))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(16, 16, 16))
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int totalcost = 0;
        int paymentreceived = 0;
        int paymentleft = 0;
        try {
            ps = null;
            String query = "insert into bookings (chassis_no, Payment_Received, payment_left, delivery_date, Cost_of_car, client_id, Employee_id) values (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            if ((txtchassisno.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Chassis No!");
            } else {
                ps.setString(1, txtchassisno.getText());
            }

            if ((txttotalcost.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Total Cost!");
            } else {
                totalcost = Integer.parseInt(txttotalcost.getText());
                ps.setInt(5, totalcost);
            }

            if ((txtpaymentreceived.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Payment Received!");
            } else {
                paymentreceived = Integer.parseInt(txtpaymentreceived.getText());
                ps.setInt(2, paymentreceived);
            }

            paymentleft = totalcost - paymentreceived;
            ps.setInt(3, paymentleft);

            if ((txtdeliverydate.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Delivery Date!");
            } else {
                ps.setString(4, txtdeliverydate.getText());
            }
            if ((txtclientid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Client ID!");
            } else {
                ps.setInt(6, Integer.parseInt(txtclientid.getText()));
            }
            if ((txtemployeeid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID!");
            } else {
                ps.setInt(7, Integer.parseInt(txtemployeeid.getText()));
            }
            int i = ps.executeUpdate();
            ps.close();
            if (i == 1) {
                if (!(txtemployeeid.getText().equals(""))) {
                    updateCarSold(Integer.parseInt(txtemployeeid.getText()));
                    updateCommission(Integer.parseInt(txtemployeeid.getText()));
                }
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Added!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText("");
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Added!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText("");
                txtchassisno.requestFocus();
            }
            //System.out.println("Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(BookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            ps = null;
            String query = "delete from bookings where chassis_no = ?";
            ps = conn.prepareStatement(query);
            int chassis_no = Integer.parseInt(txtchassisno.getText());
            ps.setInt(1, chassis_no);
            int i = ps.executeUpdate();
            ps.close();
            //System.out.println("record deleted");
            if (i == 1) {
                ondeleteRecord(Integer.parseInt(txtemployeeid.getText()));
                updateCommission(Integer.parseInt(txtemployeeid.getText()));
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Deleted!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText("");
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Deleted!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText("");
                txtchassisno.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            int totalcost = 0;
            int paymentreceived = 0;
            int paymentleft = 0;
            ps = null;
            String query = "update bookings set Payment_Received = ?, payment_left = ?, delivery_date = ?, Cost_of_car = ?, client_id = ?, Employee_id = ? where chassis_no = ?";
            ps = conn.prepareStatement(query);
            if ((txttotalcost.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Total Cost!");
            } else {
                totalcost = Integer.parseInt(txttotalcost.getText());
                ps.setInt(4, totalcost);
            }
            if ((txtpaymentreceived.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Payment Received!");
            } else {
                paymentreceived = Integer.parseInt(txtpaymentreceived.getText());
                ps.setInt(1, paymentreceived);
            }
            paymentleft = totalcost - paymentreceived;
            ps.setInt(2, paymentleft);

            if ((txtdeliverydate.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Delivery Date!");
            } else {
                ps.setString(3, txtdeliverydate.getText());
            }
            if ((txtclientid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Client ID!");
            } else {
                ps.setInt(5, Integer.parseInt(txtclientid.getText()));
            }
            if ((txtemployeeid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID!");
            } else {
                ps.setInt(6, Integer.parseInt(txtemployeeid.getText()));
            }
            int chassis_no = Integer.parseInt(txtchassisno.getText());
            ps.setInt(7, chassis_no);
            int i = ps.executeUpdate();
            ps.close();
            //System.out.println("record updated");
            if (i == 1) {
                onupdateCarSold(Integer.parseInt(txtemployeeid.getText()));
                onupdateCommission(Integer.parseInt(txtemployeeid.getText()));
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Update!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText("");
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Updated!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText("");
                txtchassisno.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ps = conn.prepareStatement("select * from bookings where chassis_no = ?");
            String chassis_no = txtchassisno1.getText();
            ps.setString(1, chassis_no);
            rs = ps.executeQuery();
            if (rs.next() == true) {
                txtchassisno.setEnabled(false);
                txtchassisno.setText(rs.getString(1));
                txtpaymentreceived.setText(rs.getString(2));
                txtpaymentleft.setText(rs.getString(3));
                String strDate = dateFormat.format(rs.getDate(4));
                txtdeliverydate.setText(strDate);
                txttotalcost.setText(rs.getString(5));
                txttotalcost.setEnabled(false);
                txtclientid.setText(Integer.toString(rs.getInt(6)));
                oldemployeeid = rs.getInt(7);
                txtemployeeid.setText(Integer.toString(oldemployeeid));
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Not Avalaible");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText("");
                txtchassisno.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        int dept_id = -1;
        try {
            ps5 = conn.prepareStatement("select dept_id from employees where employee_id = ?");
            ps5.setInt(1, emp_id);
            rs5 = ps5.executeQuery();
            if (rs5.next()) {
                System.out.println(rs5.getString(1));
                dept_id = rs5.getInt("dept_id");

            }

            ps5.close();
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
                }
            } else if (dept_id == 3) {
                new HR_dashboard(emp_id).setVisible(true);
                this.setVisible(false);
            } else {
                System.out.println("Error");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientRecord.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtpaymentleftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaymentleftActionPerformed
        // TODO add your handling code here:
        txtpaymentleft.setEnabled(false);
    }//GEN-LAST:event_txtpaymentleftActionPerformed

    private void txttotalcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalcostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalcostActionPerformed

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
            java.util.logging.Logger.getLogger(BookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingsRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookingsTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTextField txtchassisno;
    private javax.swing.JTextField txtchassisno1;
    private javax.swing.JTextField txtclientid;
    private javax.swing.JTextField txtdeliverydate;
    private javax.swing.JTextField txtemployeeid;
    private javax.swing.JTextField txtpaymentleft;
    private javax.swing.JTextField txtpaymentreceived;
    private javax.swing.JTextField txttotalcost;
    // End of variables declaration//GEN-END:variables
}
