/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ParametersConfig.java
 *
 * Created on Aug 17, 2010, 11:10:59 AM
 */
package com.google.code.xbeejavaapi.configapp;

import com.google.code.xbeejavaapi.XBee;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class ParametersConfig extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(ParametersConfig.class);
    private DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Parameter", "Value"}, 0);
    private XBee xbee;
    private ArrayList<String> propertyNames;
    private JFrame frame;

    /** Creates new form ParametersConfig */
    public ParametersConfig(final XBee xbee, final JFrame frame) {
        initComponents();
        this.frame = frame;
        this.xbee = xbee;
        setterPanel.setLayout(new BoxLayout(setterPanel, BoxLayout.LINE_AXIS));
        setterPanel.setMinimumSize(new Dimension(100, 100));
        jTable1.setModel(tableModel);
        update();
        jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                int idx = jTable1.getSelectedRow();
                setterPanel.removeAll();
                JPanel panel = new JPanel();
                if (idx != -1) {
                    final Method[] methods = XBee.class.getMethods();
                    String propertyName = propertyNames.get(idx);
                    for (int i = 0; i < methods.length; i++) {
                        if (methods[i].getName().replace("set", "").equals(propertyName)) {
                            Class[] parameters = methods[i].getParameterTypes();
                            final Method method = methods[i];
                            if (parameters.length == 1) {
                                final Class parameter = parameters[0];
                                if (parameter.equals(long.class)) {
                                    panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
                                    JLabel label = new JLabel("New value: ");
                                    final JTextField jTextField = new JTextField();
                                    JButton button = new JButton("Set");
                                    panel.add(label);
                                    panel.add(jTextField);
                                    jTextField.setColumns(20);
                                    panel.add(button);
                                    button.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                Long l = new Long(jTextField.getText());
                                                method.invoke(xbee, new Object[]{l});
                                                update(method);
                                            } catch (Exception ex) {
                                                logger.error(ex);
                                            }
                                        }
                                    });
                                } else if (parameter.getSuperclass() != null && parameter.getSuperclass().equals(Enum.class)) {
                                    panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
                                    JLabel label = new JLabel("New value: ");
                                    final JComboBox jComboBox = new JComboBox();
                                    DefaultComboBoxModel defaultComboBoxModel;
                                    JButton button = new JButton("Set");
                                    panel.add(label);
                                    panel.add(jComboBox);
                                    panel.add(button);
                                    try {
                                        defaultComboBoxModel = new DefaultComboBoxModel((Object[]) parameter.getMethod("values", new Class[]{}).invoke(null, new Object[]{}));
                                        jComboBox.setModel(defaultComboBoxModel);
                                    } catch (Exception ex) {
                                        logger.error(ex);
                                        panel = new JPanel();
                                    }
                                    button.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                Object selectedName = "" + jComboBox.getSelectedItem();
                                                Object enumElement = parameter.getMethod("valueOf", new Class[]{String.class}).invoke(null, new Object[]{selectedName});
                                                method.invoke(xbee, new Object[]{enumElement});
                                                update(method);
                                            } catch (Exception ex) {
                                                logger.error(ex);
                                                ex.printStackTrace();
                                            }
                                        }
                                    });
                                } else if (parameter.equals(String.class)) {
                                    panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
                                    JLabel label = new JLabel("New value: ");
                                    final JTextField jTextField = new JTextField();
                                    JButton button = new JButton("Set");
                                    panel.add(label);
                                    panel.add(jTextField);
                                    jTextField.setColumns(20);
                                    panel.add(button);
                                    button.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                method.invoke(xbee, new Object[]{jTextField.getText()});
                                                update(method);
                                            } catch (Exception ex) {
                                                logger.error(ex);
                                                ex.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
                setterPanel.add(panel);
                panel.validate();
                panel.repaint();
                frame.validate();
            }
        });
    }

    private void update() {
        tableModel.setNumRows(0);
        Method[] methods = xbee.getClass().getMethods();
        propertyNames = new ArrayList<String>();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("get")
                    && method.getParameterTypes().length == 0
                    && !method.getName().equals("getClass")) {
                try {
                    Object value;
                    try {
                        value = method.invoke(xbee, new Object[]{});
                    } catch (InvocationTargetException e) {
                        value = "<Exception " + e.getCause().getMessage() + ">";
                    }
                    value = valueToString(value);
                    tableModel.addRow(new Object[]{method.getName().replace("get", ""), value});
                    propertyNames.add(method.getName().replace("get", ""));
                } catch (Exception ex) {
                    logger.error(ex);
                    ex.printStackTrace();
                }
            }
        }
    }

    private void update(Method m) {

        Method[] methods = xbee.getClass().getMethods();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = (String) tableModel.getValueAt(i, 0);
            if (("set" + name).equals(m.getName())) {
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    if (method.getName().equals(("get" + name))) {
                        try {
                            Object value;
                            try {
                                value = method.invoke(xbee, new Object[]{});
                            } catch (InvocationTargetException e) {
                                value = "<Exception " + e.getCause().getMessage() + ">";
                            }
                            value = valueToString(value);
                            tableModel.setValueAt(value, i, 1);
                        } catch (Exception ex) {
                            logger.error(ex);
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private Object valueToString(Object value) {
        if (value instanceof Long) {
            value = "0x" + Long.toHexString((Long) value).toUpperCase();
        } else if (value instanceof Collection) {
            Collection c = (Collection) value;
            String s = "[ ";
            Iterator iter = c.iterator();
            if (iter.hasNext()) {
                s += "" + iter.next().toString();
            }
            while (iter.hasNext()) {
                s += ", " + iter.next().toString();
            }
            s += " ]";
            value = s;
        }
        return value;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        setterPanel = new javax.swing.JPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout setterPanelLayout = new javax.swing.GroupLayout(setterPanel);
        setterPanel.setLayout(setterPanelLayout);
        setterPanelLayout.setHorizontalGroup(
            setterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        setterPanelLayout.setVerticalGroup(
            setterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(setterPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        update();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel setterPanel;
    // End of variables declaration//GEN-END:variables
}
