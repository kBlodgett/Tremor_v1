/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tremornetbeans;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JViewport;

/**
 *
 * @author Kirjsten
 */
public class DailyEvents extends javax.swing.JFrame {
    
    
    String tremStr = ("Date\tTremors\tMean HR\tMin HR\tMax HR\tMean Movement\tMin Movement\tMax Movement\tMean Length\tMin Length\tMax Length\n");
    List<TremorDays> tremorDays;
    List<OverallData> overallOriginal;
    List<TremorDays> combined;
    /**
     * Creates new form DailyEvents
     */
    public DailyEvents(List trems, List overallPass) {
        initComponents();
        tremorDays = trems;
        overallOriginal = overallPass;
        dailyEventPrint();

    }

    public boolean indexExists(final List list, final int index) 
    {
    return index >= 0 && index < list.size();
    }
    
    public void dailyEventPrint()
    {
        List<OverallData> overallList = overallOriginal;
        combined= new ArrayList<TremorDays>();
        String date = "";
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i =0; tremorDays.size()>i; i++)
            {
                
                    TremorDays tDays = tremorDays.get(i);
                    
                    //tremStr += (tDays.date()+"\t"+tDays.count()+"\t"+df.format(Double.parseDouble(tDays.meanHR()))+"\t"+df.format(Double.parseDouble(tDays.minHR()))+"\t"+df.format(Double.parseDouble(tDays.maxHR()))+"\t"+df.format(Double.parseDouble(tDays.meanVec()))+"\t\t"+df.format(Double.parseDouble(tDays.minVec()))+"\t"+df.format(Double.parseDouble(tDays.maxVec()))+"\t"+df.format(Double.parseDouble(tDays.meanLength()))+"\t"+df.format(Double.parseDouble(tDays.minLength()))+"\t"+df.format(Double.parseDouble(tDays.maxLength()))+"\n");  
                    TremorDays trem = new TremorDays(tDays.count(), tDays.date(), df.format(Double.parseDouble(tDays.meanHR())), df.format(Double.parseDouble(tDays.maxHR())), df.format(Double.parseDouble(tDays.minHR())), df.format(Double.parseDouble(tDays.meanVec())), df.format(Double.parseDouble(tDays.maxVec())),df.format(Double.parseDouble(tDays.minVec())), df.format(Double.parseDouble(tDays.meanLength())), df.format(Double.parseDouble(tDays.maxLength())), df.format(Double.parseDouble(tDays.minLength())));
                    combined.add(trem);
                    for(int x = 0; overallList.size()>x; x++)
                    {
                        OverallData oDays = overallList.get(x);
                        if(tDays.date().equalsIgnoreCase(oDays.date()))
                        {
                            overallList.remove(x);
                        }
                        
                    }
            }
        for(int x = 0; overallList.size()>x; x++)
        {  
            if(indexExists(overallList, x))
            {
                OverallData oDays = overallList.get(x);
                //String size, String dateParam,String printHR,String printMaxHR,String printMinHR,String printVec,String printMaxVec,String printMinVec,String printLength,String printMaxLength,String printMinLength
                //tremStr += (oDays.date()+"\t0"+"\t"+df.format(oDays.meanHR())+"\t"+df.format(oDays.minHR())+"\t"+df.format(oDays.maxHR())+"\t"+df.format(oDays.meanVec())+"\t\t"+df.format(oDays.minVec())+"\t"+df.format(oDays.maxVec())+"\tNot Applicable\tNot Applicable\tNot Applicable\n");
                TremorDays trem = new TremorDays("0", oDays.date(), df.format(oDays.meanHR()), df.format(oDays.maxHR()), df.format(oDays.minHR()), df.format(oDays.meanVec()), df.format(oDays.maxVec()),df.format(oDays.minVec()), "Not Applicable","Not Applicable", "Not Applicable");
                combined.add(trem);
            }         
        }
        
        //Collections.sort(combined, TremorDays.OverallDateComparator);
        for(int y =0; combined.size()>y; y++)
        {
            TremorDays cDays = combined.get(y);
            tremStr += (cDays.date()+"\t"+cDays.count()+"\t"+cDays.meanHR()+"\t"+cDays.minHR()+"\t"+cDays.maxHR()+"\t"+cDays.meanVec()+"\t\t"+cDays.minVec()+"\t"+cDays.maxVec()+"\t"+cDays.meanLength()+"\t"+cDays.minLength()+"\t"+cDays.maxLength()+"\n");  
        }
        
        dailyEventsTextArea.setText(tremStr);
        dailyEventsTextArea.setCaretPosition(0);
        dailyEventsScrollPane.setViewportView(dailyEventsTextArea);   
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
        dailyEventsScrollPane = new javax.swing.JScrollPane();
        dailyEventsTextArea = new javax.swing.JTextArea();
        dailyEventsBackground = new javax.swing.JLabel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        saveButtonFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Save Button.png"))); // NOI18N
        saveButtonFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonFileMouseClicked(evt);
            }
        });
        getContentPane().add(saveButtonFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 80, 40));

        dailyEventsScrollPane.setBorder(null);

        dailyEventsTextArea.setColumns(20);
        dailyEventsTextArea.setRows(5);
        dailyEventsScrollPane.setViewportView(dailyEventsTextArea);

        getContentPane().add(dailyEventsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 750, 300));

        dailyEventsBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Daily Events Background.png"))); // NOI18N
        getContentPane().add(dailyEventsBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 400));

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
            writer.println("Date, Tremor Count, Mean HR, Min HR, Max HR, Mean Vector Magnitude, Min Vector Magnitude, Max Vector Magnitude, Mean Legnth, Min Length, Max Length");
            for(int i =0; combined.size()>i; i++)
            {
                TremorDays tDays = combined.get(i);
                writer.println(tDays.date()+","+tDays.count()+","+tDays.meanHR()+","+tDays.minHR()+","+tDays.maxHR()+","+tDays.meanVec()+","+tDays.minVec()+","+tDays.maxVec()+","+tDays.meanLength()+","+tDays.minLength()+","+tDays.maxLength());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, "File has been saved.", "File Saved", 1);
        
    }//GEN-LAST:event_saveButtonFileMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dailyEventsBackground;
    private javax.swing.JScrollPane dailyEventsScrollPane;
    private javax.swing.JTextArea dailyEventsTextArea;
    private javax.swing.JLabel saveButtonFile;
    // End of variables declaration//GEN-END:variables
}
