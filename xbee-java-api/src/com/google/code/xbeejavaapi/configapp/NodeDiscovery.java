/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NodeDiscovery.java
 *
 * Created on Aug 18, 2010, 11:46:19 PM
 */
package com.google.code.xbeejavaapi.configapp;

import com.google.code.xbeejavaapi.api.LocalXBee;
import com.google.code.xbeejavaapi.api.DiscoveredNode;
import com.google.code.xbeejavaapi.api.exception.XBeeOperationFailedException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class NodeDiscovery extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(NodeDiscovery.class);
    private LocalXBee xbee;
    private ArrayList<DiscoveredNode> discoveredNodes = new ArrayList<DiscoveredNode>();
    private JTabbedPane jTabbedPane;
    private JFrame frame;

    /** Creates new form NodeDiscovery */
    public NodeDiscovery(LocalXBee xbee, JFrame frame, JTabbedPane jTabbedPane) {
        this.xbee = xbee;
        this.frame = frame;
        this.jTabbedPane = jTabbedPane;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nodesList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        nodeDetails = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        nodesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        nodesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                nodesListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(nodesList);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nodes");

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Node Details");

        nodeDetails.setColumns(20);
        nodeDetails.setEditable(false);
        nodeDetails.setRows(5);
        jScrollPane2.setViewportView(nodeDetails);

        jButton1.setText("Open");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Thread() {

            @Override
            public void run() {
                try {
                    jButton2.setEnabled(false);
                    jButton2.setText("Searching...");
                    discoveredNodes = new ArrayList<DiscoveredNode>(xbee.searchNodes());
                    ArrayList<String> nodes = new ArrayList<String>();
                    for (DiscoveredNode discoveredNode : discoveredNodes) {
                        nodes.add(discoveredNode.getNodeIdentifier());
                    }
                    nodesList.setListData(nodes.toArray());
                } catch (XBeeOperationFailedException ex) {
                    logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                } finally {
                    jButton2.setEnabled(true);
                    jButton2.setText("Search");
                }
            }
        }.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void nodesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_nodesListValueChanged
        if (nodesList.getSelectedIndex() != -1) {
            DiscoveredNode discoveredNode = discoveredNodes.get(nodesList.getSelectedIndex());
            nodeDetails.setText(discoveredNode.toString());
        } else {
            nodeDetails.setText("");
        }
    }//GEN-LAST:event_nodesListValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (nodesList.getSelectedIndex() != -1) {
            DiscoveredNode discoveredNode = discoveredNodes.get(nodesList.getSelectedIndex());
            ParametersConfig parametersConfig = new ParametersConfig(discoveredNode.getXbee(), frame);
            Operations operations = new Operations(discoveredNode.getXbee());
            JTabbedPane remoteNodeTab = new JTabbedPane();
            remoteNodeTab.addTab("Parameters", parametersConfig);
            remoteNodeTab.addTab("Operations", operations);
            IO io = new IO(discoveredNode.getXbee());
            JScrollPane ioPane = new JScrollPane(io);
            remoteNodeTab.addTab("IO", ioPane);
            jTabbedPane.addTab(discoveredNode.getNodeIdentifier() + " (remote)", remoteNodeTab);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea nodeDetails;
    private javax.swing.JList nodesList;
    // End of variables declaration//GEN-END:variables
}
