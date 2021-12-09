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

/**
 *
 * @author usamaasif
 */
public class SalesBookingsRecord extends javax.swing.JFrame {

    /**
     * Creates new form BookingsRecord
     */
    showroomManagementSystem app = new showroomManagementSystem();
    Connection conn = app.getConnection();
    PreparedStatement ps, ps1, ps2, ps3, ps5;
    ResultSet rs, rs1, rs2, rs3, rs5;
    private int emp_id;

    public SalesBookingsRecord() {
        initComponents();
        updatetable();
        txtpaymentleft.setEnabled(false);
        txtemployeeid.setText(String.valueOf(emp_id));
        txtemployeeid.setEnabled(false);
    }

    public SalesBookingsRecord(int emp_id) {
        initComponents();
        this.emp_id = emp_id;
        updatetable();
        txtpaymentleft.setEnabled(false);
        txtemployeeid.setText(String.valueOf(emp_id));
        txtemployeeid.setEnabled(false);
    }

    private void updatetable() {
        try {
            //select from particular employee_id from login
            ps = conn.prepareStatement("select * from bookings where employee_id = ?");
            ps.setInt(1, emp_id);
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
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCarSold(int employeeID) {
        int oldcarsold = 0;
        int carsold;
        try {
            String query = "update employees set CarsSold = ? where employee_id = ?";
            ps1 = conn.prepareStatement(query);
            String query1 = "select count(*) from bookings where employee_id = ?";
            ps2 = conn.prepareStatement(query1);
            String query2 = "select CarsSold from employees where employee_id = ?";
            ps3 = conn.prepareStatement(query2);
            ps3.setInt(1, employeeID);
            ps2.setInt(1, employeeID);
            rs3 = ps3.executeQuery();
            if (rs3.next()) {
                oldcarsold = rs3.getInt(1);
            }
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                System.out.println(oldcarsold);
                //System.out.println(rs2.getInt(1));
                //carsold = oldcarsold + rs2.getInt(1);
                System.out.println(rs2.getInt(1));
                carsold = oldcarsold + 1;
                System.out.println(carsold);
                ps1.setInt(1, carsold);
                ps1.setInt(2, employeeID);
                ps1.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
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
            //System.out.println(ps1);
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                carsold = rs2.getInt(1);
                //System.out.println(carsold);
                ps1.setInt(1, carsold * 10000);
                ps1.setInt(2, employeeID);
                ps1.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
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
        txtpaymentreceived = new javax.swing.JTextField();
        txtpaymentleft = new javax.swing.JTextField();
        txtdeliverydate = new javax.swing.JTextField();
        txttotalcost = new javax.swing.JTextField();
        txtclientid = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtchassisno1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtemployeeid = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 251, 252));

        bookingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Chassis No", "Payment Received", "Payment Left", "Delivery Date", "TotalCost", "Client ID", "Employee ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(bookingsTable);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel1.setText("ChassisNo");

        txtchassisno.setBackground(new java.awt.Color(222, 226, 230));
        txtchassisno.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel2.setText("Payment Received");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel3.setText("Payment Left");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel4.setText("Delivery Date");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel5.setText("Total Cost");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel6.setText("Client ID");

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

        txtclientid.setBackground(new java.awt.Color(222, 226, 230));
        txtclientid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

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

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/db project/walls/1/edits/toyota-logo.png"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(226, 51, 53));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img_ext/left (Custom).png"))); // NOI18N
        jButton2.setText("Return");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Booking Records");

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
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jButton8)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Employee ID");

        txtemployeeid.setBackground(new java.awt.Color(222, 226, 230));
        txtemployeeid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(32, 32, 32))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(jButton5)))
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtemployeeid)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdeliverydate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpaymentreceived, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpaymentleft, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtclientid, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttotalcost, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jButton6)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(287, 287, 287)
                            .addComponent(jButton2))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotalcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtpaymentreceived, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtpaymentleft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtdeliverydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtclientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtemployeeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
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
            ps.setInt(7, emp_id);
            int i = ps.executeUpdate();
            ps.close();
            if (i == 1) {
                updateCarSold(emp_id);
                updateCommission(emp_id);
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Added!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText(String.valueOf(emp_id));
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Added!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText(String.valueOf(emp_id));
                txtchassisno.requestFocus();
            }
            //System.out.println("Inserted");
        } catch (SQLException ex) {
            if (ex.getMessage().split(" ")[0].equals("Duplicate")) {
                JOptionPane.showMessageDialog(this, "Chassis No must be unique!");
            }
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
                txtemployeeid.setText(Integer.toString(rs.getInt(7)));
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Not Avalaible");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText(String.valueOf(emp_id));
                txtchassisno.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
                    new mainPage().setVisible(true);
                }
            } else if (dept_id == 3) {
                new HR_dashboard(emp_id).setVisible(true);
                this.setVisible(false);
            } else {
                System.out.println("Error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtpaymentleftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaymentleftActionPerformed
        // TODO add your handling code here:
        txtpaymentleft.setEnabled(false);
    }//GEN-LAST:event_txtpaymentleftActionPerformed

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
            ps.setInt(6, emp_id);
            String chassis_no = txtchassisno.getText();
            ps.setString(7, chassis_no);
            int i = ps.executeUpdate();
            ps.close();
            //System.out.println("record updated");
            if (i == 1) {
                updatetable();
                JOptionPane.showMessageDialog(this, "Record Update!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText(String.valueOf(emp_id));
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Updated!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");
                txtemployeeid.setText(String.valueOf(emp_id));
                txtchassisno.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(SalesBookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesBookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesBookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesBookingsRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesBookingsRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookingsTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
