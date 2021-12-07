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
import net.proteanit.sql.DbUtils;

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
    PreparedStatement ps, ps1, ps2, ps3;
    ResultSet rs, rs1, rs2, rs3;
    private int emp_id;

    public SalesBookingsRecord() {
        initComponents();
        updatetable();
    }

    public SalesBookingsRecord(int emp_id) {
        initComponents();
        updatetable();
        this.emp_id = emp_id;
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
            Logger.getLogger(BookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
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
        jButton8 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));

        bookingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ChassisNo", "PaymentReceived", "PaymentLeft", "DeliveryDate", "TotalCostOfCar", "ClientID", "Employee ID"
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

        txtdeliverydate.setBackground(new java.awt.Color(222, 226, 230));
        txtdeliverydate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        txttotalcost.setBackground(new java.awt.Color(222, 226, 230));
        txttotalcost.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        txtclientid.setBackground(new java.awt.Color(222, 226, 230));
        txtclientid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

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

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/db project/walls/1/edits/toyota-logo.png"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(226, 51, 53));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back (Custom).jpg"))); // NOI18N
        jButton2.setText("Return");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Booking Records");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel2))
                                                .addGap(18, 18, 18))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(21, 21, 21)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtpaymentreceived, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtpaymentleft, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttotalcost, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtdeliverydate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtclientid, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtpaymentreceived, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtpaymentleft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtdeliverydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txttotalcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtclientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton8)
                    .addComponent(jButton2))
                .addGap(93, 93, 93))
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
        try {
            ps = null;
            String query = "insert into bookings (chassis_no, Payment_Received, payment_left, delivery_date, Cost_of_car, client_id, Employee_id) values (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, txtchassisno.getText());
            ps.setInt(2, Integer.parseInt(txtpaymentreceived.getText()));
            ps.setInt(3, Integer.parseInt(txtpaymentleft.getText()));
            ps.setString(4, txtdeliverydate.getText());
            ps.setInt(5, Integer.parseInt(txttotalcost.getText()));
            ps.setInt(6, Integer.parseInt(txtclientid.getText()));
            //employee_id from login of sales person
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

                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Record not Added!");
                txtchassisno.setText("");
                txtpaymentreceived.setText("");
                txtpaymentleft.setText("");
                txtdeliverydate.setText("");
                txttotalcost.setText("");
                txtclientid.setText("");

                txtchassisno.requestFocus();
            }
            //System.out.println("Inserted");

        } catch (SQLException ex) {
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            ps = conn.prepareStatement("select * from bookings where chassis_no = ?");
            int chassis_no = Integer.parseInt(txtchassisno.getText());
            ps.setInt(1, chassis_no);
            rs = ps.executeQuery();
            if (rs.next() == true) {
                txtpaymentreceived.setText(rs.getString(2));
                txtpaymentleft.setText(rs.getString(3));
                txtdeliverydate.setText(Integer.toString(rs.getInt(4)));
                txttotalcost.setText(rs.getString(5));
                txtclientid.setText(Integer.toString(rs.getInt(6)));
                txtchassisno.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Not Avalaible");
                txtchassisno.setText("");
                txtchassisno.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesBookingsRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

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
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtchassisno;
    private javax.swing.JTextField txtclientid;
    private javax.swing.JTextField txtdeliverydate;
    private javax.swing.JTextField txtpaymentleft;
    private javax.swing.JTextField txtpaymentreceived;
    private javax.swing.JTextField txttotalcost;
    // End of variables declaration//GEN-END:variables
}
