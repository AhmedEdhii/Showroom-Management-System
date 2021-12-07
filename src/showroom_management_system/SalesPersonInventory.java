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
public class SalesPersonInventory extends javax.swing.JFrame {

    /**
     * Creates new form UsedCarRecord
     */
    showroomManagementSystem app = new showroomManagementSystem();
    Connection conn = app.getConnection();
    PreparedStatement ps, ps1, ps2, ps3, ps5;
    ResultSet rs, rs1, rs2, rs3, rs5;
    private int emp_id;

    public SalesPersonInventory() {
        initComponents();
        updatetable();
        txtemployeeid.setEnabled(false);
        txtbuyerclientid.setEnabled(false);
    }

    public SalesPersonInventory(int emp_id) {
        initComponents();
        this.emp_id = emp_id;
        updatetable();
        txtemployeeid.setEnabled(false);
        txtbuyerclientid.setEnabled(false);
    }

    private void updatetable() {
        try {
            ps = conn.prepareStatement("select * from used_cars where employee_id = ?");
            ps.setInt(1, emp_id);
            rs = ps.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            int j = rsd.getColumnCount();
            DefaultTableModel dft = (DefaultTableModel) usedCarsTable.getModel();
            dft.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 0; i <= j; i++) {
                    v2.add(rs.getString("used_car_id"));
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
            Logger.getLogger(SalesPersonInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCarSold(int employeeID, String status) {
        int oldcarsold = 0;
        int carsold;
        String status1 = "Sold";
        try {
            String query = "update employees set CarsSold = ? where employee_id = ?";
            ps1 = conn.prepareStatement(query);
            String query1 = "select count(*) from used_cars where employee_id = ? and statusOfCar = ?";
            ps2 = conn.prepareStatement(query1);
            String query2 = "select CarsSold from employees where employee_id = ?";
            ps3 = conn.prepareStatement(query2);
            ps2.setInt(1, employeeID);
            ps2.setString(2, status1);
            ps3.setInt(1, employeeID);
            rs3 = ps3.executeQuery();
            if (rs3.next()) {
                oldcarsold = rs3.getInt(1);
            }
            //System.out.println(ps1);
            rs2 = ps2.executeQuery();

            if (rs2.next()) {
                //carsold = rs2.getInt(1);
                //System.out.println(carsold);
                if (status.equals(status1)) {
                    carsold = oldcarsold + 1;
                } else {
                    carsold = oldcarsold;
                }
                ps1.setInt(1, carsold);
                ps1.setInt(2, employeeID);
                ps1.executeUpdate();
            }
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
            //System.out.println(ps1);
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String s = rs2.getString(1);
                System.out.println(s);
                carsold = Integer.parseInt(s);
                System.out.println(carsold);
                ps1.setInt(1, carsold * 10000);
                ps1.setInt(2, employeeID);
                ps1.executeUpdate();
            }
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

        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        usedCarsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtchassisno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmodel = new javax.swing.JTextField();
        txtengineno = new javax.swing.JTextField();
        txtyear = new javax.swing.JTextField();
        txtcostprice = new javax.swing.JTextField();
        txtsaleprice = new javax.swing.JTextField();
        txtbuyerclientid = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtchassisno1 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtemployeeid = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));

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

        usedCarsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ChassisNo", "Model", "EngineNo", "Year", "Cost Price", "Sell Price", "EmployeeID", "Buyer Client ID", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add (Custom).png"))); // NOI18N
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel10.setText("Status");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/db project/walls/1/edits/toyota-logo.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Inventory");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UnSold", "Sold" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Chassis No");

        txtchassisno1.setBackground(new java.awt.Color(222, 226, 230));
        txtchassisno1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        txtchassisno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtchassisno1ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(226, 51, 53));
        jButton13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search (Custom).png"))); // NOI18N
        jButton13.setText("Search");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton13)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(194, 194, 194))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtchassisno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addGap(16, 16, 16))
        );

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Employee ID");

        txtemployeeid.setBackground(new java.awt.Color(222, 226, 230));
        txtemployeeid.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtchassisno, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtmodel, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtengineno, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtyear, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtcostprice, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtsaleprice, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtemployeeid))
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbuyerclientid, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jButton5)
                                .addGap(26, 26, 26)
                                .addComponent(jButton6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtchassisno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtengineno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcostprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsaleprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton1)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtemployeeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtbuyerclientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            if ((txtemployeeid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID!");
            }
            if ((txtbuyerclientid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Client ID!");
            }
            if (status.equals("Sold") && !(txtemployeeid.getText().equals("")) && !(txtbuyerclientid.getText().equals(""))) {
                ps.setInt(7, Integer.parseInt(txtemployeeid.getText()));
                ps.setInt(8, Integer.parseInt(txtbuyerclientid.getText()));
            }
            if (status.equals("UnSold") && !(txtemployeeid.getText().equals("")) && !(txtbuyerclientid.getText().equals(""))) {
                ps.setString(7, null);
                ps.setString(8, null);
            }
            ps.setString(9, status);
            System.out.println(status);
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
            Logger.getLogger(UsedCarRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
                    new mainPage().setVisible(true);
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

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        try {
            ps = conn.prepareStatement("select * from used_cars where chassis_no = ?");
            String chassis_no = txtchassisno1.getText();
            ps.setString(1, chassis_no);
            rs = ps.executeQuery();
            if (rs.next() == true) {
                txtchassisno.setEnabled(false);
                txtchassisno.setText(rs.getString(2));
                txtmodel.setEnabled(false);
                txtmodel.setText(rs.getString(3));
                txtengineno.setEnabled(false);
                txtengineno.setText(rs.getString(4));
                txtyear.setEnabled(false);
                txtyear.setText(Integer.toString(rs.getInt(5)));
                txtcostprice.setEnabled(false);
                txtcostprice.setText(Integer.toString(rs.getInt(6)));
                txtsaleprice.setText(Integer.toString(rs.getInt(7)));
                txtemployeeid.setText(Integer.toString(rs.getInt(8)));
                txtbuyerclientid.setText(Integer.toString(rs.getInt(9)));
                statusComboBox.setEnabled(false);
                statusComboBox.setSelectedItem(rs.getString(10));
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
    }//GEN-LAST:event_jButton13ActionPerformed

    private void txtchassisno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtchassisno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtchassisno1ActionPerformed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboBoxActionPerformed

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
            if ((txtemployeeid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID!");
            }
            if ((txtbuyerclientid.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Client ID!");
            }
            if (status.equals("Sold") && !(txtemployeeid.getText().equals("")) && !(txtbuyerclientid.getText().equals(""))) {
                ps.setInt(6, Integer.parseInt(txtemployeeid.getText()));
                ps.setInt(7, Integer.parseInt(txtbuyerclientid.getText()));
            }
            if (status.equals("UnSold") && !(txtemployeeid.getText().equals("")) && !(txtbuyerclientid.getText().equals(""))) {
                ps.setString(6, null);
                ps.setString(7, null);
            }
            ps.setString(8, status);
            int chassis_no = Integer.parseInt(txtchassisno.getText());
            ps.setInt(9, chassis_no);
            int i = ps.executeUpdate();
            ps.close();
            //System.out.println("record updated");
            if (i == 1) {
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
            java.util.logging.Logger.getLogger(SalesPersonInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesPersonInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesPersonInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesPersonInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new SalesPersonInventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
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
