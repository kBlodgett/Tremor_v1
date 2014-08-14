/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tremornetbeans;

import java.text.DecimalFormat;

/**
 *
 * @author Kirjsten
 */
public class OverallData {
    private String date;
    private double maxHR, minHR, meanHR, maxVec, minVec, meanVec;
    private DecimalFormat df = new DecimalFormat("#.##");
    public OverallData(String datePass,double maxHRPass,double minHRPass,double meanHRPass,double maxVecPass,double minVecPass,double meanVecPass)
    {
        date = datePass;
        maxHR = maxHRPass;
        minHR = minHRPass;
        meanHR = meanHRPass;
        maxVec = maxVecPass;
        minVec = minVecPass;
        meanVec = meanVecPass;
            
        
    }
    
    public String date()
    {
        return date;
    }
    
    public double maxHR()
    {
        return maxHR;
    }
    
    public double minHR()
    {
        return minHR;
    }
    
    public double meanHR()
    {
        return meanHR;
    }
    
    public double maxVec()
    {
        return maxVec;
    }
    
    public double minVec()
    {
        return minVec;
    }
    
    public double meanVec()
    {
        return meanVec;
    }
    

}
