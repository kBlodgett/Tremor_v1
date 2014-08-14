/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tremornetbeans;

import java.awt.Cursor;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Kirjsten
 */
public class Threshold extends javax.swing.JFrame {

    List<DataPerMin>  dataPerMinArray;
    List<Tremor> tremList;
    List<TremorDays> tremDays;
    TremorEvaluation tremor;
    TremorCount tremCount;
    LowHigh lowHigh;
    String tremListStr= "";
    /**
     * Creates new form Threshold
     */
    public Threshold() {
        initComponents();
    }
    
    public Threshold(List dataPerMin) {
        initComponents();
        dataPerMinArray = dataPerMin;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startHRText = new javax.swing.JTextField();
        endHRText = new javax.swing.JTextField();
        vecText = new javax.swing.JTextField();
        enterButtonThreshold = new javax.swing.JLabel();
        thresholdBackground = new javax.swing.JLabel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        startHRText.setForeground(new java.awt.Color(153, 153, 153));
        startHRText.setText("120");
        startHRText.setBorder(null);
        startHRText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startHRTextMouseClicked(evt);
            }
        });
        startHRText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startHRTextActionPerformed(evt);
            }
        });
        getContentPane().add(startHRText, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 120, 30));

        endHRText.setForeground(new java.awt.Color(153, 153, 153));
        endHRText.setText("100");
        endHRText.setBorder(null);
        endHRText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endHRTextMouseClicked(evt);
            }
        });
        getContentPane().add(endHRText, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 120, 30));

        vecText.setForeground(new java.awt.Color(153, 153, 153));
        vecText.setText("5000.00");
        vecText.setBorder(null);
        vecText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vecTextMouseClicked(evt);
            }
        });
        getContentPane().add(vecText, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 120, 30));

        enterButtonThreshold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Enter Button.png"))); // NOI18N
        enterButtonThreshold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterButtonThresholdMouseClicked(evt);
            }
        });
        getContentPane().add(enterButtonThreshold, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 80, 40));

        thresholdBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Background(Thresholds).png"))); // NOI18N
        getContentPane().add(thresholdBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 410));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void enterButtonThresholdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterButtonThresholdMouseClicked
        
        tremor = new TremorEvaluation(dataPerMinArray, 
        startHRText.getText(), endHRText.getText(), vecText.getText());
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tremor.mins();
        tremList = tremor.tremList();
        lowHigh = new LowHigh(tremList);
        lowHigh.lowest();
        lowHigh.highest();
        tremor.VecHRMean();
        tremCount = new TremorCount(tremor.tremList());
        tremCount.tremPerDay();
        tremDays = tremCount.tremDays();
        dispose();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        //JOptionPane.showMessageDialog(null, "Loading has finished.","Complete",1);
    }//GEN-LAST:event_enterButtonThresholdMouseClicked


    
    /*
    * returns the mean statistics
    */
    public String mean()
    {
        String meanStr = (lowHigh.printStr()+"\n"+tremor.meanStr()+"\n"+tremCount.mean());
        return meanStr;
    }
    
    /*
    * returns all trmeor 
    * @return tremList
    */
    public List tremList()
    {
        return tremList;
    }
    
    private void tremListCycle()
    {
        for(int i =0; tremList.size()>i; i++)
            {
                Tremor trem = tremList.get(i);
                tremListStr += (trem.date()+"\t"+trem.startTime()+"\t"+trem.maxHR()+"\t"+trem.maxVec()+"\t"+trem.length()+"\n");
            }
    }
    
    
    public String tremListStr()
    {
        tremListCycle();
        return tremListStr;
    }
    
  
    
    private void startHRTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startHRTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startHRTextActionPerformed

    private void startHRTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startHRTextMouseClicked
        startHRText.setText("");
    }//GEN-LAST:event_startHRTextMouseClicked

    private void endHRTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endHRTextMouseClicked
        endHRText.setText("");
    }//GEN-LAST:event_endHRTextMouseClicked

    private void vecTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vecTextMouseClicked
        vecText.setText("");
    }//GEN-LAST:event_vecTextMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField endHRText;
    private javax.swing.JLabel enterButtonThreshold;
    private javax.swing.JTextField startHRText;
    private javax.swing.JLabel thresholdBackground;
    private javax.swing.JTextField vecText;
    // End of variables declaration//GEN-END:variables

    private void JOptionPane(Object object, String run_TremorEvaluation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * returns trem days
     * @return tremDays
     */
    public List tremDays() {
        return tremDays;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
    private Threshold() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
}
