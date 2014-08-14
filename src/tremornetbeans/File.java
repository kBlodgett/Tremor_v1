/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tremornetbeans;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Kirjsten
 */
public class File extends javax.swing.JFrame {

    String dataPerMinString = ("Date\tTime\tAxis1\tAxis2\tAxis3\tSteps\tHR\tLux\tInclinometer Off\t\tInclinometer Stand\tInclinometer Sit\t\tInclinometer Lay\tVector Magnitude\n");
    
    List<DataPerMin> dataPerMinArray;
    /**
     * Creates new form File
     * @param read
     */
    public File(List dataPerMin, String file) {
        initComponents();
        dataPerMinArray = dataPerMin;
        dataPerMinString += file; 
        
        fileTextArea = new JTextArea();
        fileTextArea.setText(dataPerMinString);
        fileTextArea.setCaretPosition(0);
        fileScrollPane.setViewportView(fileTextArea);
    }

    File(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saveButtonFile = new javax.swing.JLabel();
        fileScrollPane = new javax.swing.JScrollPane();
        fileTextArea = new javax.swing.JTextArea();
        fileBackground = new javax.swing.JLabel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        saveButtonFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Save Button.png"))); // NOI18N
        saveButtonFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonFileMouseClicked(evt);
            }
        });
        getContentPane().add(saveButtonFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 80, 40));

        fileScrollPane.setBorder(null);

        fileTextArea.setColumns(20);
        fileTextArea.setRows(5);
        fileScrollPane.setViewportView(fileTextArea);

        getContentPane().add(fileScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 750, 300));

        fileBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/File - Background.png"))); // NOI18N
        getContentPane().add(fileBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonFileMouseClicked
        PrintWriter writer;
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showSaveDialog(null);
            java.io.File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
            writer = new PrintWriter(new FileWriter(filename+".csv"));
            writer.println("Date, Time, Axis1, Axis2, Axis3, Steps, HR, Lux, Inclinometer Off, Inclinometer Stand, Inclinometer Sit, Inclinometer Lay, Vector Magnitude");
            for(int i =0; dataPerMinArray.size()>i; i++)
            {
                DataPerMin dPM = dataPerMinArray.get(i);
                writer.println(dPM.date()+","+dPM.time()+","+dPM.axis1()+","+dPM.axis2()+","+dPM.axis3()+","+dPM.steps()+","+dPM.HR()+","+dPM.lux()+","+dPM.iOff()+","+dPM.iStand()+","+dPM.iSit()+","+dPM.iLay()+","+dPM.vec());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, "File has been saved.", "File Saved", 1);
        
        
    }//GEN-LAST:event_saveButtonFileMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fileBackground;
    private javax.swing.JScrollPane fileScrollPane;
    private javax.swing.JTextArea fileTextArea;
    private javax.swing.JLabel saveButtonFile;
    // End of variables declaration//GEN-END:variables
}
