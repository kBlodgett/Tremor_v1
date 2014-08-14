/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tremornetbeans;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
public class Overall extends javax.swing.JFrame {

    List<OverallData> overallOriginal;
    OverallData oDays;
    DecimalFormat df = new DecimalFormat("#.##");
    /**
     * Creates new form Overall
     */
    public Overall(List overallPass) {
        initComponents();
        overallOriginal = overallPass;
        String overallStr = ("Date\tMean HR\tMin HR\tMax HR\tMean Movement\tMin Movement\tMax Movement\n");
        for(int y =0; overallOriginal.size()>y; y++)
        {
            OverallData oDays = overallOriginal.get(y);
            overallStr += (oDays.date()+"\t"+df.format(oDays.meanHR())+"\t"+df.format(oDays.minHR())+"\t"+df.format(oDays.maxHR())+"\t"+df.format(oDays.meanVec())+"\t\t"+df.format(oDays.minVec())+"\t"+df.format(oDays.maxVec())+"\n");
        }
        
        overallTextArea = new JTextArea();
        overallTextArea.setText(overallStr);
        overallTextArea.setCaretPosition(0);
        overallScrollPane.setViewportView(overallTextArea);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        saveButtonFile = new javax.swing.JLabel();
        overallScrollPane = new javax.swing.JScrollPane();
        overallTextArea = new javax.swing.JTextArea();
        overallBackground = new javax.swing.JLabel();

        jFrame1.setResizable(false);
        jFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        saveButtonFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Save Button.png"))); // NOI18N
        saveButtonFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonFileMouseClicked(evt);
            }
        });
        getContentPane().add(saveButtonFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 80, 40));

        overallScrollPane.setBorder(null);

        overallTextArea.setColumns(20);
        overallTextArea.setRows(5);
        overallScrollPane.setViewportView(overallTextArea);

        getContentPane().add(overallScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 750, 300));

        overallBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Overall - Background.png"))); // NOI18N
        getContentPane().add(overallBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 400));

        setSize(new java.awt.Dimension(820, 454));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonFileMouseClicked
        PrintWriter writer;
        List<OverallData> overall = overallOriginal;
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showSaveDialog(null);
            java.io.File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
            writer = new PrintWriter(new FileWriter(filename+".csv"));
            writer.println("Date, Mean HR, Min HR, Max HR, Mean Vector Magnitude, Min Vector Magnitude, Max Vector Magnitude");
            for(int i =0; overall.size()>i; i++)
            {
                OverallData oDays = overall.get(i);
                writer.println(oDays.date()+","+oDays.meanHR()+","+oDays.minHR()+","+oDays.maxHR()+","+oDays.meanVec()+","+oDays.minVec()+","+oDays.maxVec());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, "File has been saved.", "File Saved", 1);

    }//GEN-LAST:event_saveButtonFileMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel overallBackground;
    private javax.swing.JScrollPane overallScrollPane;
    private javax.swing.JTextArea overallTextArea;
    private javax.swing.JLabel saveButtonFile;
    // End of variables declaration//GEN-END:variables
}
