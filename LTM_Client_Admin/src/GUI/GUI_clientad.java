/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Main.Admin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HAI HUONG
 */
public class GUI_clientad extends javax.swing.JFrame {

    public SystemInfo systemInfo;
    public Admin admin;
    public String message = null;
    Tracking tracking;
    public String content = "";
    public GUI_clientad() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public void setLablePCName(String name){
        lbName.setText(name);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        lbName = new javax.swing.JLabel();
        btnInfo = new javax.swing.JButton();
        btnTrack = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        lbName.setBackground(new java.awt.Color(255, 204, 204));
        lbName.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbName.setText("PC");

        btnInfo.setBackground(new java.awt.Color(255, 204, 204));
        btnInfo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnInfo.setText("SYSTEM INFORMATION");
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });

        btnTrack.setBackground(new java.awt.Color(255, 204, 204));
        btnTrack.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnTrack.setText("SYSTEM TRACKING");
        btnTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrackActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setText("USER MANAGE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 204, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton4.setText("BACK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(216, 216, 216))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTrack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
        message = "option 1";
        try {
            //System.out.println(message);
            Thread.sleep(2000);
            systemInfo.setVisible(true);
            systemInfo.addInfo(this.content);
            this.content = "";
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI_clientad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //message = null;
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrackActionPerformed
        // TODO add your handling code here:
        /*message = "option 2";
        tracking = new Tracking();
        try {
            Thread.sleep(1500);
            tracking.setVisible(true);
            tracking.loadRAM(content);
            tracking.setContent(content);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI_clientad.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        message = "option 3";
        tracking = new Tracking();
        try {
            Thread.sleep(1200);
            tracking.setVisible(true);
            tracking.loadAllProcess(content);
            tracking.setContent(content);
            tracking.gui_clientad = this;
            this.content = "";
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI_clientad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //
    }//GEN-LAST:event_btnTrackActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        UserManage userManage = new UserManage();
        userManage.setVisible(true);
        userManage.gui_clientad = this;
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnTrack;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}