/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IO.java
 *
 * Created on Aug 23, 2010, 5:12:48 PM
 */
package com.google.code.xbeejavaapi.configapp;

import com.google.code.xbeejavaapi.XBee;
import com.google.code.xbeejavaapi.XBee.ReceivedIOSamplesListener;
import com.google.code.xbeejavaapi.api.Constants.Analog_IO_Pin;
import com.google.code.xbeejavaapi.api.Constants.Digital_IO_Pin;
import com.google.code.xbeejavaapi.api.IOState;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class IO extends javax.swing.JPanel implements ReceivedIOSamplesListener {

    private static final Logger logger = Logger.getLogger(IO.class);
    private XBee xbee;
    private Map<Digital_IO_Pin, JLabel> digitalLabels = new HashMap<Digital_IO_Pin, JLabel>();
    private Map<Analog_IO_Pin, JLabel> analogLabels = new HashMap<Analog_IO_Pin, JLabel>();

    /** Creates new form IO */
    public IO(final XBee xbee) {
        this.xbee = xbee;
        initComponents();

        for (Digital_IO_Pin digital_IO_Pin : Digital_IO_Pin.values()) {
            JLabel jLabel1 = new JLabel("Digital IO Line " + digital_IO_Pin.getValue() + " state: ");
            JLabel jLabel2 = new JLabel("--");
            jLabel2.setForeground(Color.BLUE);
            digitalLabels.put(digital_IO_Pin, jLabel2);

            JPanel panel = new JPanel();

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
            panel.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2).addContainerGap()));
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jLabel2)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

//            panel.add(label1);
//            panel.add(label2);

            this.add(panel);
        }

        for (Analog_IO_Pin analog_IO_Pin : Analog_IO_Pin.values()) {
            JLabel jLabel1 = new JLabel("Analog IO Line " + analog_IO_Pin.getValue() + " value: ");
            JLabel jLabel2 = new JLabel("--");
            jLabel2.setForeground(Color.BLUE);
            analogLabels.put(analog_IO_Pin, jLabel2);

            JPanel panel = new JPanel();

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
            panel.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2).addContainerGap()));
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jLabel2)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

//            panel.add(label1);
//            panel.add(label2);

            this.add(panel);
        }

        {
            JPanel panel = new JPanel();
            JButton jButton1 = new javax.swing.JButton();

            jButton1.setText("Refresh");

            jButton1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        xbee.forceSample();
                    } catch (XBeeOperationFailedException ex) {
                        logger.error(ex);
                    }
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
            panel.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE).addContainerGap()));
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jButton1).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

            add(panel);
        }

        xbee.addDigitalChangeDetectionListener(this);
    }

    private void update(IOState state) {
        for (Digital_IO_Pin digital_IO_Pin : Digital_IO_Pin.values()) {
            if (state.getDigitalIOState().keySet().contains(digital_IO_Pin)) {
                digitalLabels.get(digital_IO_Pin).setText((((boolean) state.getDigitalIOState().get(digital_IO_Pin)) ? "HIGH" : "LOW"));
                digitalLabels.get(digital_IO_Pin).setForeground(Color.BLACK);
            } else {
                digitalLabels.get(digital_IO_Pin).setText("DISABLED");
                digitalLabels.get(digital_IO_Pin).setForeground(Color.BLUE);
            }
        }

        for (Analog_IO_Pin analog_IO_Pin : Analog_IO_Pin.values()) {
            if (state.getAnalogIOState().containsKey(analog_IO_Pin)) {
                analogLabels.get(analog_IO_Pin).setText("" + state.getAnalogIOState().get(analog_IO_Pin));
                analogLabels.get(analog_IO_Pin).setForeground(Color.BLACK);
            } else {
                analogLabels.get(analog_IO_Pin).setText("DISABLED");
                analogLabels.get(analog_IO_Pin).setForeground(Color.BLUE);
            }
        }
    }

    public void ioSamplesReceived(IOState state) {
        update(state);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        IO io = new IO(null);
        JScrollPane jScrollPane = new JScrollPane(io);
        frame.add(jScrollPane);
        frame.pack();
        frame.setVisible(true);

    }
}
