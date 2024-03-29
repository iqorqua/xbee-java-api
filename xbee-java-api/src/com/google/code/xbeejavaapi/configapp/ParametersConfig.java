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

import com.google.code.xbeejavaapi.api.XBee;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
//        setterPanel.setMinimumSize(new Dimension(100, 100));
        setterPanel.setPreferredSize(new Dimension(100, 50));
        jTable1.setModel(tableModel);
        update();

        jSplitPane1.setDividerLocation(0.1);
        jSplitPane1.resetToPreferredSizes();

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
                                                long value;
                                                {
                                                    String text = jTextField.getText();
                                                    text = text.replaceAll(" ", "");
                                                    if (text.startsWith("0x") || text.startsWith("0X")) {
                                                        value = Long.parseLong(text.substring(2), 16);
                                                    } else {
                                                        value = Long.parseLong(text);
                                                    }
                                                }
                                                method.invoke(xbee, new Object[]{value});
                                                update(method);
                                            } catch (Exception ex) {
                                                logger.error(ex + " at " + ex.getStackTrace()[0].toString());
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
                                        logger.error(ex + " at " + ex.getStackTrace()[0].toString());
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
                                                logger.error(ex + " at " + ex.getStackTrace()[0].toString());
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
                                                logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                                ex.printStackTrace();
                                            }
                                        }
                                    });
                                } else if (parameter.isAssignableFrom(Set.class)) {
                                    try {
                                        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                                        Class collectionType = null;
                                        Type[] genericParameterTypes = method.getGenericParameterTypes();
                                        for (Type genericParameterType : genericParameterTypes) {
                                            if (genericParameterType instanceof ParameterizedType) {
                                                ParameterizedType aType = (ParameterizedType) genericParameterType;
                                                Type[] parameterArgTypes = aType.getActualTypeArguments();
                                                for (Type parameterArgType : parameterArgTypes) {
                                                    Class parameterArgClass = (Class) parameterArgType;
                                                    collectionType = parameterArgClass;
                                                }
                                            }
                                        }

                                        if (collectionType.getSuperclass() != null && collectionType.getSuperclass().equals(Enum.class)) {
                                            Object[] values = (Object[]) collectionType.getMethod("values", new Class[]{}).invoke(null, new Object[]{});

                                            final Map<JCheckBox, Object> jCheckBoxes = new HashMap<JCheckBox, Object>();

                                            Collection selected = new ArrayList();

                                            try {
                                                selected.addAll((Collection) XBee.class.getMethod(method.getName().replaceFirst("set", "get"), new Class[0]).invoke(xbee, new Object[0]));
                                            } catch (Exception ex) {
                                                logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                            }

                                            for (Object object : values) {
                                                JCheckBox checkBox = new JCheckBox(object.toString());
                                                jCheckBoxes.put(checkBox, object);
                                                checkBox.setAlignmentX(LEFT_ALIGNMENT);
                                                panel.add(checkBox);

                                                if (selected.contains(object)) {
                                                    checkBox.setSelected(true);
                                                }
                                            }

                                            JButton button = new JButton("Set");
                                            button.setAlignmentX(Component.LEFT_ALIGNMENT);
                                            panel.add(button);
                                            button.addActionListener(new ActionListener() {

                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        Collection c = new HashSet();

                                                        for (Map.Entry<JCheckBox, Object> entry : jCheckBoxes.entrySet()) {
                                                            if (entry.getKey().isSelected()) {
                                                                c.add(entry.getValue());
                                                            }
                                                        }

                                                        method.invoke(xbee, new Object[]{c});
                                                        update(method);
                                                    } catch (Exception ex) {
                                                        logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                                        ex.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    } catch (IllegalAccessException ex) {
                                        logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                    } catch (IllegalArgumentException ex) {
                                        logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                    } catch (InvocationTargetException ex) {
                                        logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                    } catch (NoSuchMethodException ex) {
                                        logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                    } catch (SecurityException ex) {
                                        logger.error(ex + " at " + ex.getStackTrace()[0].toString());
                                    }
                                }
                            }
                        }
                    }
                }
                JScrollPane jScrollPane = new JScrollPane(panel);
                jScrollPane.setAlignmentX(CENTER_ALIGNMENT);
                setterPanel.add(jScrollPane);
                jSplitPane1.resetToPreferredSizes();
                panel.validate();
                panel.repaint();
                frame.validate();
            }
        });
    }

    private void update() {
        tableModel.setNumRows(0);
        Method[] methods = XBee.class.getMethods();
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
                    logger.error(ex + " at " + ex.getStackTrace()[0].toString());
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
                            logger.error(ex + " at " + ex.getStackTrace()[0].toString());
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

        jButton1 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        setterPanel = new javax.swing.JPanel();

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setDoubleBuffered(true);
        jSplitPane1.setLastDividerLocation(400);

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

        jSplitPane1.setTopComponent(jScrollPane1);

        setterPanel.setPreferredSize(new java.awt.Dimension(76, 10));
        setterPanel.setLayout(new javax.swing.BoxLayout(setterPanel, javax.swing.BoxLayout.LINE_AXIS));
        jSplitPane1.setRightComponent(setterPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
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
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel setterPanel;
    // End of variables declaration//GEN-END:variables
}
