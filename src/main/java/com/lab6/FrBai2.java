/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author acer
 */
public class FrBai2 extends javax.swing.JFrame {

    public FrBai2() throws SQLException {
        initComponents();
        ConnectDB();
        loadCombobox();
        loadData();
    }

    boolean addNew = false;
    boolean fill = false;
    Vector data = new Vector();
    Vector header = new Vector();
    Vector col = new Vector();
    Connection con = null;
    PreparedStatement pstDetails = null;
    PreparedStatement pstInsert = null;
    PreparedStatement pstDelete = null;
    PreparedStatement pstUpdate = null;
    String sqlInsert = "Insert into Students ([Name],Address,ParentName,Phone,standard) values(?,?,?,?,?)";
    String sqlDelete = "Delete from Students where Name=?";
    String sqlUpdate = "Update Students set Address=?, ParentName=?,Phone=? ,standard=? where Name=?";
    ResultSet rts;

    public void ConnectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;database=KidszoneSchool;userName=sa;password=123456");
            pstInsert = con.prepareStatement(this.sqlInsert);
            pstUpdate = con.prepareStatement(this.sqlUpdate);
            pstDelete = con.prepareStatement(this.sqlDelete);
            pstDetails = con.prepareStatement("select * from Students", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rts = pstDetails.executeQuery();
            JOptionPane.showMessageDialog(this, "Connection Database Successful!");
            this.loadCombobox();
            this.loadData();
            fill = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void loadCombobox() {
        String sql1 = "select * from dbo.Standard";
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql1);
            Vector<String> standards = new Vector<String>();
            Vector<Integer> fees = new Vector<Integer>();
            while (rs.next()) {
                cbbStandard.addItem(rs.getString(1));
                cbbFees.addItem(String.valueOf(rs.getInt(2)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.exit(0);
        }

    }

    public void loadData() throws SQLException {
        String sql = "Select [Name], standard from Students";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            data.removeAllElements();
            if (!fill) {
                //get header
                ResultSetMetaData rsmd = rs.getMetaData();
                int n = rsmd.getColumnCount();

                col.add(rsmd.getColumnName(1));
                col.add(rsmd.getColumnName(2));
            }
            //get data
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                data.add(v);
            }
            TableModel tbl = new DefaultTableModel(data, col);
            this.tbStudent.setModel(tbl);
            rs.close();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }

    boolean validates() {
        if (txtName.getText().matches("")) {
            JOptionPane.showMessageDialog(this, "Name ko dc trong ", "Chu y", 1);
            txtName.requestFocus();
            return false;
        }
        String pName = this.txtName.getText().trim();
        Iterator it = data.iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            String name = ((String) v.get(0)).trim();
            if (pName.equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(this, "Ten Sinh Vien nay da ton tai!");
                this.txtName.grabFocus();
                return false;
            }
        }
        if (txtAddress.getText().matches("")) {
            JOptionPane.showMessageDialog(this, "Address khong duoc de trong ", "Chu y", 1);
            txtAddress.requestFocus();
            return false;
        }
        if (txtParentName.getText().matches("")) {
            JOptionPane.showMessageDialog(this, "ParentsName khong duoc de trong ", "Chu y", 1);
            txtParentName.requestFocus();
            return false;
        }

        if (txtContactNo.getText().matches("^\\d{7,11}$") == false) {
            JOptionPane.showMessageDialog(this, "Contact(Phone) khong duoc de trong va phai la 7-12 so ", "Chu y", 1);
            txtContactNo.requestFocus();
            return false;
        }
        if ((cbbStandard.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(this, cbbStandard.getSelectedItem());
        }
        return true;
    }

    boolean duplicate(String s) {
        if (addNew == false) {
            return false;
        }
        for (int i = 0; i < data.size(); i++) {
            Vector v = (Vector) data.get(i);
            if (s.equalsIgnoreCase((String) v.get(0))) {
                return true;
            }
        }
        return false;
    }

    private void clearForm() {
        txtName.setText("");
        txtAddress.setText("");
        txtParentName.setText("");
        txtContactNo.setText("");
        cbbStandard.setSelectedIndex(0);
        cbbFees.setSelectedIndex(0);
        txtName.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbStudent = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtParentName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtContactNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbbFees = new javax.swing.JComboBox<>();
        cbbStandard = new javax.swing.JComboBox<>();
        bntNew = new javax.swing.JButton();
        bntNext = new javax.swing.JButton();
        bntInsert = new javax.swing.JButton();
        bntPrev = new javax.swing.JButton();
        bntEdit = new javax.swing.JButton();
        bntDelete = new javax.swing.JButton();
        bntUpdate = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Standard"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStudentMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbStudentMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbStudent);

        jLabel1.setText("Name:");

        jLabel2.setText("Address:");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane2.setViewportView(txtAddress);

        jLabel3.setText("ParentName:");

        jLabel4.setText("ContactNo:");

        jLabel5.setText("Standard:");

        jLabel6.setText("Fees:");

        cbbFees.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "******************", " " }));
        cbbFees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFeesActionPerformed(evt);
            }
        });

        cbbStandard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "***********" }));
        cbbStandard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbStandardActionPerformed(evt);
            }
        });

        bntNew.setText("New");
        bntNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNewActionPerformed(evt);
            }
        });

        bntNext.setText("Next");
        bntNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNextActionPerformed(evt);
            }
        });

        bntInsert.setText("Insert");
        bntInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntInsertActionPerformed(evt);
            }
        });

        bntPrev.setText("Pre...");
        bntPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrevActionPerformed(evt);
            }
        });

        bntEdit.setText("Edit");
        bntEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditActionPerformed(evt);
            }
        });

        bntDelete.setText("Delete");
        bntDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDeleteActionPerformed(evt);
            }
        });

        bntUpdate.setText("Update");
        bntUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntUpdateActionPerformed(evt);
            }
        });

        bntExit.setText("Exit");
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtParentName)
                            .addComponent(txtContactNo)
                            .addComponent(cbbFees, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbStandard, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bntNew, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                            .addComponent(bntPrev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bntNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bntEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(bntDelete)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bntUpdate))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtParentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtContactNo)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbStandard))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(cbbFees))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntNew)
                            .addComponent(bntInsert)
                            .addComponent(bntEdit)
                            .addComponent(bntUpdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntNext)
                            .addComponent(bntDelete)
                            .addComponent(bntExit)
                            .addComponent(bntPrev))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        bntUpdate.setEnabled(true);
        bntDelete.setEnabled(true);
        bntEdit.setEnabled(true);
        txtName.setEnabled(true);
        txtAddress.setEnabled(true);
        txtParentName.setEnabled(true);
        txtContactNo.setEnabled(true);
        cbbStandard.setEnabled(true);
    }//GEN-LAST:event_bntNewActionPerformed

    private void cbbStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbStandardActionPerformed
        // TODO add your handling code here:
        cbbFees.setSelectedIndex(cbbStandard.getSelectedIndex());
    }//GEN-LAST:event_cbbStandardActionPerformed

    private void cbbFeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFeesActionPerformed
        // TODO add your handling code here:
        cbbStandard.setSelectedIndex(cbbFees.getSelectedIndex());
    }//GEN-LAST:event_cbbFeesActionPerformed

    private void tbStudentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStudentMouseReleased
        // TODO add your handling code here:
        if (this.tbStudent.getCellEditor() != null) {
            this.tbStudent.getCellEditor().cancelCellEditing();
        }
    }//GEN-LAST:event_tbStudentMouseReleased

    private void tbStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStudentMouseClicked
        // TODO add your handling code here:
        try {
            rts.beforeFirst();
            this.clearForm();
            int row = tbStudent.getSelectedRow();
            String name = (String) tbStudent.getValueAt(row, 0);
            while (rts.next()) {
                String str = rts.getString(2); //JOptionPane.showMessageDialog(null,""+jrs.getString(2)+" "+name);
                if (str.equalsIgnoreCase(name)) {
                    txtName.setText(rts.getString(2));
                    txtAddress.setText(rts.getString(3));
                    txtContactNo.setText(rts.getInt(5) + "");
                    //String parent=dencry(rts.getString(5));
                    txtParentName.setText(rts.getString(4));
                    cbbStandard.setSelectedItem(rts.getString(6));
                    //com_fees.setSelectedItem(rts.getInt(7));
                    break;
                }
            }
        } catch (Exception e) {
        }
        txtName.setEnabled(false);
        txtAddress.setEnabled(false);
        txtParentName.setEnabled(false);
        txtContactNo.setEnabled(false);
        cbbStandard.setEnabled(false);
        cbbFees.setEnabled(false);
    }//GEN-LAST:event_tbStudentMouseClicked

    private void bntUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntUpdateActionPerformed
        // TODO add your handling code here:
        try {
            int n;
            if (addNew) {
                //them moi
                //set cac tham so
                pstInsert.setString(1, this.txtName.getText().trim());
                pstInsert.setString(2, txtAddress.getText().trim());
                pstInsert.setString(3, txtParentName.getText().trim());
                pstInsert.setString(4, txtContactNo.getText().trim());
                pstInsert.setString(5, (String) cbbStandard.getSelectedItem());
                n = pstInsert.executeUpdate();//thuc thi
                this.loadData();
            } else {
                //update
                //set cac tham so
                pstUpdate.setString(5, this.txtName.getText().trim());
                pstUpdate.setString(2, this.txtAddress.getText().trim());
                pstUpdate.setString(1, this.txtParentName.getText().trim());
                pstUpdate.setString(3, this.txtContactNo.getText().trim());
                pstUpdate.setString(4, (String) cbbStandard.getSelectedItem());
                n = pstUpdate.executeUpdate();//thuc thi
                this.loadData();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }//GEN-LAST:event_bntUpdateActionPerformed

    private void bntEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditActionPerformed
        // TODO add your handling code here:
        bntUpdate.setEnabled(true);
        bntDelete.setEnabled(true);
        bntEdit.setEnabled(true);
        txtName.setEnabled(true);
        txtAddress.setEnabled(true);
        txtParentName.setEnabled(true);
        txtContactNo.setEnabled(true);
        cbbStandard.setEnabled(true);
    }//GEN-LAST:event_bntEditActionPerformed

    private void bntPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrevActionPerformed
        // TODO add your handling code here:
        try {
            //JOptionPane.showMessageDialog(this, "Co loi xay ra");
            rts.previous();
            // JOptionPane.showMessageDialog(this, "Co loi xay ra");
            bntNext.setEnabled(true);
            if (rts.isBeforeFirst()) {
                // System.out.println("co loi xay ra");
                bntPrev.setEnabled(false);
                bntNext.setEnabled(true);
                JOptionPane.showMessageDialog(null, "You have reached the first record "
                        + "of the ResultSet!!!!");
            } else {
                txtName.setText(rts.getString(2));
                txtAddress.setText(rts.getString(3));
                txtParentName.setText(rts.getString(4));
                txtContactNo.setText(rts.getString(5));
                cbbStandard.setSelectedItem(rts.getString(6));
            }
        } catch (Exception e) {
            //JOptionPane.showConfirmDialog(this,"co loi xay ra");
        }
    }//GEN-LAST:event_bntPrevActionPerformed

    private void bntDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int n = this.tbStudent.getSelectedRow();
            if (n >= 0)//nguoi dung co chon
            {
                //this.pnlDetails.setVisible(false);
                Vector v = (Vector) data.get(n);
                int ans = JOptionPane.showConfirmDialog(this, "Ban co thuc su muon xoa Sinh Vien "
                        + ((String) v.get(0)).trim() + " khong?");
                if (ans == JOptionPane.YES_OPTION) {
                    pstDelete.setString(1, (String) v.get(0));
                    pstDelete.executeUpdate();
                    this.loadData();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }


    }//GEN-LAST:event_bntDeleteActionPerformed

    private void bntNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNextActionPerformed
        // TODO add your handling code here:
        //loadCombobox();
        try {
            rts.next();
            bntPrev.setEnabled(true);
            if (rts.isAfterLast() || rts.isBeforeFirst()) {
                bntNext.setEnabled(false);
                bntPrev.setEnabled(true);
                JOptionPane.showMessageDialog(null, "You have reached the last record"
                        + " of the ResultSet!!!!");
            } else {
                txtName.setText(rts.getString(2));
                txtAddress.setText(rts.getString(3));
                txtParentName.setText(rts.getString(4));
                txtContactNo.setText(rts.getString(5));
                cbbStandard.setSelectedItem(rts.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_bntNextActionPerformed

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        // TODO add your handling code here:
        fill = false;
        System.exit(0);

    }//GEN-LAST:event_bntExitActionPerformed

    private void bntInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInsertActionPerformed
        // TODO add your handling code here:
        if (!validates()) {
            return;
        }
        String name = txtName.getText();
        String addr = txtAddress.getText();
        String parentName = txtParentName.getText();
        String phone = txtContactNo.getText();
        String standard = (String) cbbStandard.getSelectedItem();
        try {
            pstInsert.setString(1, name);
            pstInsert.setString(2, addr);
            pstInsert.setString(3, parentName);
            pstInsert.setString(4, phone);
            pstInsert.setString(5, standard);
            int addRows = pstInsert.executeUpdate();
            this.loadData();
            clearForm();
            if (addRows > 0) {
                JOptionPane.showMessageDialog(this, "Students Details Have Been Save", "Successfull", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to save data in database", "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bntInsertActionPerformed

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
            java.util.logging.Logger.getLogger(FrBai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrBai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrBai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrBai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrBai2().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FrBai2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntDelete;
    private javax.swing.JButton bntEdit;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntInsert;
    private javax.swing.JButton bntNew;
    private javax.swing.JButton bntNext;
    private javax.swing.JButton bntPrev;
    private javax.swing.JButton bntUpdate;
    private javax.swing.JComboBox<String> cbbFees;
    private javax.swing.JComboBox<String> cbbStandard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbStudent;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtParentName;
    // End of variables declaration//GEN-END:variables
}
