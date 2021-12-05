/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showroom_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usamaasif
 */
public class InvoiceGenerator extends javax.swing.JFrame {

    /**
     * Creates new form InvoiceGenerator
     */
    showroomManagementSystem app = new showroomManagementSystem();
    Connection conn = app.getConnection();
    PreparedStatement ps;
    PreparedStatement ps1, ps2, ps3, ps4;
    ResultSet rs;

    public InvoiceGenerator() {
        initComponents();
    }

    int client_id;
    int invoiceno;
    int total;

    public static float getBOTTOM_ALIGNMENT() {
        return BOTTOM_ALIGNMENT;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        oilFilterComboBox = new javax.swing.JComboBox<>();
        ACFilterComboBox = new javax.swing.JComboBox<>();
        AirFilterComboBox = new javax.swing.JComboBox<>();
        oilQuantityComboBox = new javax.swing.JComboBox<>();
        BrakeDiskComboBox = new javax.swing.JComboBox<>();
        WipersComboBox = new javax.swing.JComboBox<>();
        CompleteDetailingComboBox = new javax.swing.JComboBox<>();
        CarWashComboBox = new javax.swing.JComboBox<>();
        WheelComboBox = new javax.swing.JComboBox<>();
        BrakePadsComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtclientid = new javax.swing.JTextField();
        txtmechanicid = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtchassisno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Oil Filter");

        jLabel2.setText("AC Filter");

        jLabel3.setText("Air Filter");

        jLabel4.setText("Oil Quantity");

        jLabel5.setText("Brake Pads");

        jLabel6.setText("Brake Disk");

        jLabel7.setText("Car Wash");

        jLabel8.setText("Complete Detailing");

        jLabel9.setText("Wheel");

        jLabel10.setText("Wipers");

        oilFilterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));

        ACFilterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        ACFilterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACFilterComboBoxActionPerformed(evt);
            }
        });

        AirFilterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));

        oilQuantityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0L", "1L", "2L", "3L", "4L", "5L", "6L", "7L", "8L", "9L" }));
        oilQuantityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oilQuantityComboBoxActionPerformed(evt);
            }
        });

        BrakeDiskComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", " " }));

        WipersComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));

        CompleteDetailingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));

        CarWashComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));

        WheelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4" }));

        BrakePadsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4" }));

        jButton1.setText("Generate Invoice");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel11.setText("Parts List To Be Used");

        jLabel12.setText("Client ID");

        jLabel13.setText("Mechanic ID");

        jLabel14.setText("Chassis No");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtchassisno, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtclientid)
                        .addComponent(txtmechanicid, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtclientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtmechanicid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(355, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(WipersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(WheelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CompleteDetailingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CarWashComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BrakeDiskComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BrakePadsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AirFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(198, 198, 198)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(oilFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ACFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(176, 176, 176)
                        .addComponent(oilQuantityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(oilFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(ACFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(AirFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(oilQuantityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(BrakePadsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(BrakeDiskComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(CarWashComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel8))
                                    .addComponent(CompleteDetailingComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(jLabel9))
                            .addComponent(WheelComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(WipersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(25, 25, 25))))
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

    private void oilQuantityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oilQuantityComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oilQuantityComboBoxActionPerformed

    private void ACFilterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACFilterComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ACFilterComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            client_id = Integer.parseInt(txtclientid.getText());
            int qty;
            int unit_cost;
            int line_total;
            int total_cost = 0;
            int invoice_no = 0;
            ps = null;
            String query1 = "insert into service (client_id, mechanic_id, DOService, chassis_no, Total_Cost) values (?,?,?,?,?)";
            ps = conn.prepareStatement(query1);
            String query2 = "insert into service_details (Invoice_No, part_desc, Quantity, Line_Total) values (?,?,?,?)";
            ps1 = conn.prepareStatement(query2);
            String query3 = "select unit_cost from parts where part_desc = ? ";
            ps2 = conn.prepareStatement(query3);
            String query4 = "select * from service ORDER BY Invoice_No desc limit 1; ";
            ps3 = conn.prepareStatement(query4);
            String query5 = "update service set Total_Cost = ? where Invoice_No = ?";
            ps4 = conn.prepareStatement(query5);

            ps.setInt(1, Integer.parseInt(txtclientid.getText()));
            ps.setInt(2, Integer.parseInt(txtmechanicid.getText()));
            ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(4, txtchassisno.getText());
            ps.setInt(5, 0);
            ps.executeUpdate();
            rs = ps3.executeQuery();
            if (rs.next() == true) {
                invoice_no = rs.getInt(1);
                this.invoiceno = invoice_no;
            }
            if (jLabel1.getText().equals("Oil Filter")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Oil Filter");
                qty = Integer.parseInt(oilFilterComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Oil Filter";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel2.getText().equals("AC Filter")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "AC Filter");
                qty = Integer.parseInt(ACFilterComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "AC Filter";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel3.getText().equals("Air Filter")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Air Filter");
                qty = Integer.parseInt(AirFilterComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Air Filter";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel4.getText().equals("Oil Quantity")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Oil Quantity");
                String qty1 = oilQuantityComboBox.getSelectedItem().toString();
                qty = Integer.parseInt(String.valueOf(qty1.charAt(0)));
                ps1.setInt(3, qty);
                String part_desc = "Oil Quantity";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel5.getText().equals("Brake Pads")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Brake Pads");
                qty = Integer.parseInt(BrakePadsComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Brake Pads";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel6.getText().equals("Brake Disk")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Brake Disk");
                qty = Integer.parseInt(BrakeDiskComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Brake Disk";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel7.getText().equals("Car Wash")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Car Wash");
                qty = Integer.parseInt(CarWashComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Car Wash";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel8.getText().equals("Complete Detailing")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Complete Detailing");
                qty = Integer.parseInt(CompleteDetailingComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Complete Detailing";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel9.getText().equals("Wheel")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Wheel");
                qty = Integer.parseInt(WheelComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Wheel";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (jLabel10.getText().equals("Wipers")) {
                ps1.setInt(1, invoice_no);
                ps1.setString(2, "Wipers");
                qty = Integer.parseInt(WipersComboBox.getSelectedItem().toString());
                ps1.setInt(3, qty);
                String part_desc = "Wipers";
                try {
                    if (qty != 0) {
                        ps2.setString(1, part_desc);
                        rs = ps2.executeQuery();
                        if (rs.next() == true) {
                            unit_cost = rs.getInt(1);
                            line_total = qty * unit_cost;
                            ps1.setInt(4, line_total);
                            total_cost = total_cost + line_total;
                        }
                        ps1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            total = total_cost;
            ps4.setInt(1, total_cost);
            ps4.setInt(2, invoice_no);
            int i = ps4.executeUpdate();
            ps.close();
            if (i == 1) {
                //JOptionPane.showMessageDialog(this, "Invoice Generated!");
                txtclientid.setText("");
                txtmechanicid.setText("");
                txtchassisno.setText("");
                txtclientid.requestFocus();
                new Invoice(client_id, new java.sql.Date(new java.util.Date().getTime()), this.invoiceno, total).setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invoice not Generated!");
                txtclientid.setText("");
                txtmechanicid.setText("");
                txtchassisno.setText("");
                txtclientid.requestFocus();
            }
            //System.out.println("Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(employeeRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(InvoiceGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoiceGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoiceGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InvoiceGenerator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ACFilterComboBox;
    private javax.swing.JComboBox<String> AirFilterComboBox;
    private javax.swing.JComboBox<String> BrakeDiskComboBox;
    private javax.swing.JComboBox<String> BrakePadsComboBox;
    private javax.swing.JComboBox<String> CarWashComboBox;
    private javax.swing.JComboBox<String> CompleteDetailingComboBox;
    private javax.swing.JComboBox<String> WheelComboBox;
    private javax.swing.JComboBox<String> WipersComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JComboBox<String> oilFilterComboBox;
    private javax.swing.JComboBox<String> oilQuantityComboBox;
    private javax.swing.JTextField txtchassisno;
    private javax.swing.JTextField txtclientid;
    private javax.swing.JTextField txtmechanicid;
    // End of variables declaration//GEN-END:variables
}
