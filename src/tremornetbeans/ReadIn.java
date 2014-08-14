/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tremornetbeans;

/**
 * Read in the CSV file from device, prints out file in nice labeled format, and creates CSV file with only HR, time, and vector magnitude.
 * 
 * @author Kirjsten Blodgett 
 * @version 1.0
 * email: catsanddogskb@hotmail.com
 */
import java.awt.TextField;
import java.io.*;
import java.lang.Math;
import java.text.ParseException;
import java.util.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class ReadIn   
{
    private int format = 0;
    private Scanner reader;
    private Scanner keyboard = new Scanner(System.in);
    private PrintWriter writer = new PrintWriter( new FileWriter("tremorData.csv"));
    private List<DataPerMin>  dataPerMinArray = new ArrayList<DataPerMin>();
    private List<OverallData> overallDataArray = new ArrayList<OverallData>();
    
   /**
     * Constructor for original
     */
    public ReadIn() throws IOException
    {
        String input="";
            try{
                    System.out.println("Enter file location and name");
                    System.out.println("OR drag file in tremor program folder and enter (NAME).(FILETYPE)");
                    input = keyboard.nextLine();
                    reader = new Scanner(new FileReader(input));
                }catch(IOException e)
                {
                    System.out.println("File was not found, Ending Program");
                    JOptionPane.showMessageDialog(null,"File Not Found","404",3);
                    //String delay = keyboard.next();
                }
    }
    
    /*
    * constructor for interface
    * @param field
    */
    public ReadIn(JTextField field, JTextField formatField) throws IOException
    {
        String input;
        String temp = formatField.getText();
        if(temp.equalsIgnoreCase("1") ||temp.equalsIgnoreCase("2")||
                temp.equalsIgnoreCase("3")||temp.equalsIgnoreCase("4"))
        {
            format = Integer.parseInt(temp);
        }else
        {
            JOptionPane.showMessageDialog(null, "Format entered does not exist, enter numbers 1-4","Format Error", 0);
        }
        
            try{
                    //System.out.println("Enter file location and name");
                    //System.out.println("OR drag file in tremor program folder and enter (NAME).(FILETYPE)");
                    //input = keyboard.nextLine();
                    reader = new Scanner(new FileReader(field.getText()));
                }catch(IOException e)
                {
                    //System.out.println("File was not found, Ending Program");
                    JOptionPane.showMessageDialog(null, "File Not Found", "404", 0);
                    //String delay = keyboard.next();
                }
            //System.out.println("Variables have been initialized, format"+format);
    }

    /**
     * reads in and makes DataPerMin object
     */
    public void read()
    {
        String ans = "yes";
        if(format == 1)
        {      //printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");        
            readInMinutes(ans, format);
        }
        if(format == 2)
        {
                //printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");
            readInSeconds(ans, format);
        }
        if(format == 3)
        {
                //printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");   
            altReadInMinutes(ans);
        }
        if(format == 4)
        {
                //printHeader();
                writer.println("Date, - , Time, HR, - , Time, Vector-Mag");
            altReadInSeconds(ans);
        }

        
        
    }
    
    /**
     * reads in seconds and sets per minute data (NOTE: data is changed to work like minute data)
     */
    public void altReadInMinutes(String ans)
    {
        for(int i = 0; i < 3; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        int HR, sec = 00, min = 00, hour = 00, month = 00, day =00, year =00;
        double vec;
        String dateStr, timeStr;
        //reverse back before using again (NOTE TO PROGRAMMER!!!!!!!) (time and date str to be reversed)
        reader.next();reader.next();dateStr = reader.next();timeStr = reader.next();reader.nextLine(); //reader.nextLine();
        reader.next();reader.next();timeStr = reader.next();
        String[] dateArray = dateStr.split("[/]+");
        month = Integer.parseInt(dateArray[0]);
        day = Integer.parseInt(dateArray[1]);
        year = Integer.parseInt(dateArray[2]);
        String[] timeArray = timeStr.split("[:]+");
        hour = Integer.parseInt(timeArray[0]);
        min = Integer.parseInt(timeArray[1]);
        sec = Integer.parseInt(timeArray[2]);
        
        double maxHR=0, minHR=999, meanHR=0, maxVec=0, minVec=999999, meanVec=0;
        int count = 0, starterCount = 0;
        String dateComp = "";
        
        for(int i = 0; i <= 4; i++)
        {
            reader.nextLine();
        }
        while(reader.hasNextLine())
        {
            ++min;
            if(min == 60){++hour; min=00;}
            if(hour == 24){++day; hour=00;}
            if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){if(day==31){++month;day=01;}}
            if(month==2){if(day==28){++month;day=01;}}
            if(month==4||month==6||month==9||month==11){if(day==30){++month;day=01;}}
            if(month==12){++year;month=01;}
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            date = (Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year));
            time = (Integer.toString(hour)+":"+Integer.toString(min)+":"+Integer.toString(sec));
            axis1 = textArray[0];
            axis2 = textArray[1];
            axis3 = textArray[2];
            steps = textArray[3];
            HR = Integer.parseInt(textArray[4]);
            lux = textArray[5];
            iOff = textArray[6];
            iStand = textArray[7];
            iSit= textArray[8];
            iLay = textArray[9];
            
            int y = Integer.parseInt(axis1), x = Integer.parseInt(axis2), z = Integer.parseInt(axis3);
            vec = Math.sqrt((x*x)+(y*y)+(z*z));
            
            if(starterCount == 0){dateComp = date;}
            ++starterCount;
            if(dateComp.equalsIgnoreCase(date))
            {
                if(HR != 0 && vec != 0)
                {   ++count;
                    if(minHR>HR){minHR = HR;}
                    if(maxHR<HR){maxHR = HR;}
                    if(minVec>vec){minVec = vec;}
                    if(maxVec<vec){maxVec = vec;}
                    meanHR+=HR; meanVec+=vec;      
                }
            }else 
            {
                if(HR != 0 || vec != 0) 
                {   meanHR = meanHR/count; meanVec = meanVec/count;
                    count = 0;
                    OverallData overallData = new OverallData(date, maxHR, minHR, meanHR, maxVec, minVec, meanVec);
                    overallDataArray.add(overallData);
                    maxHR=0; minHR=999; meanHR=0; maxVec=0; minVec=999999; meanVec=0;
                    dateComp = date;
                }
            }
            
            if(HR !=0 && vec !=0)
            {   writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
                DataPerMin dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, 1);
                dataPerMinArray.add(dataPerMin);
            }
        }
        reader.close();
        writer.close();
        
    }
    
    /**
     * reads in minutes and sets per minute data
     */
    public void readInMinutes(String ans, int format)
    {
        System.out.println("Reading in file");
        for(int i = 0; i < 13; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        double maxHR=0, minHR=999, meanHR=0, maxVec=0, minVec=999999, meanVec=0;
        int HR, count = 0, starterCount = 0;
        double vec;
        String dateComp = "";
        while(reader.hasNextLine())
        {
            
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            
            
            date = textArray[0];
            time = textArray[1];
            axis1 = textArray[2];
            axis2 = textArray[3];
            axis3 = textArray[4];
            steps = textArray[5];
            HR = Integer.parseInt(textArray[6]);
            lux = textArray[7];
            iOff = textArray[8];
            iStand = textArray[9];
            if(textArray.length>10)
            {iSit =  textArray[10];}else iSit= "N/A";
            if(textArray.length>11)
            {iLay = textArray[11];}else iLay = "N/A";
            if(textArray.length>12)
            {vec = Double.parseDouble(textArray[12]);} else vec=0;
            
            if(starterCount == 0){dateComp = date;}
            ++starterCount;
            if(dateComp.equalsIgnoreCase(date))
            {
                if(HR != 0 || vec != 0)
                {   ++count;
                    if(minHR>HR){minHR = HR;}
                    if(maxHR<HR){maxHR = HR;}
                    if(minVec>vec){minVec = vec;}
                    if(maxVec<vec){maxVec = vec;}
                    meanHR+=HR; meanVec+=vec;      
                }
            }else 
            {
                if(HR != 0 || vec != 0)
                {   meanHR = meanHR/count; meanVec = meanVec/count;
                    count = 0;
                    OverallData overallData = new OverallData(date, maxHR, minHR, meanHR, maxVec, minVec, meanVec);
                    overallDataArray.add(overallData);
                    maxHR=0; minHR=999; meanHR=0; maxVec=0; minVec=999999; meanVec=0;
                    dateComp = date;
                }
            }
            

                writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
                DataPerMin dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, format);
                dataPerMinArray.add(dataPerMin);
            
            //if(ans.equalsIgnoreCase("yes"))
            //{print(dataPerMin);}
        }
        reader.close();
        writer.close();
        
    }
    
    /**
     * reads in minutes and sets per minute data
     */
    public void altReadInSeconds(String ans)
    {
        for(int i = 0; i < 14; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        double maxHR=0, minHR=999, meanHR=0, maxVec=0, minVec=999999, meanVec=0;
        int HR, count =0, starterCount = 0;
        double vec;
        String dateComp="";
        while(reader.hasNextLine())
        {
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            
            date = textArray[0];
            time = textArray[1];
            axis1 = textArray[2];
            axis2 = textArray[3];
            axis3 = textArray[4];
            steps = textArray[5];
            HR = Integer.parseInt(textArray[6]);
            lux = textArray[7];
            iOff = textArray[8];
            iStand = textArray[9];
            if(textArray.length>10)
            {iSit =  textArray[10];}else iSit= "N/A";
            if(textArray.length>11)
            {iLay = textArray[11];}else iLay = "N/A";
            
            int y = Integer.parseInt(axis1)*60, x = Integer.parseInt(axis2)*60, z = Integer.parseInt(axis3)*60;
            vec = Math.sqrt((x*x)+(y*y)+(z*z));
            
            
            if(starterCount == 0){dateComp = date;}
            ++starterCount;
            if(dateComp.equalsIgnoreCase(date))
            {
                if(HR != 0 || vec != 0)
                {   ++count;
                    if(minHR>HR){minHR = HR;}
                    if(maxHR<HR){maxHR = HR;}
                    if(minVec>vec){minVec = vec;}
                    if(maxVec<vec){maxVec = vec;}
                    meanHR+=HR; meanVec+=vec;      
                }
            }else 
            {
                if(HR != 0 || vec != 0)
                {   meanHR = meanHR/count; meanVec = meanVec/count;
                    count = 0;
                    OverallData overallData = new OverallData(date, maxHR, minHR, meanHR, maxVec, minVec, meanVec);
                    overallDataArray.add(overallData);
                    maxHR=0; minHR=999; meanHR=0; maxVec=0; minVec=999999; meanVec=0;
                    dateComp = date;
                }
            }
            
            
            DataPerMin dataPerMin = null;
            if(HR !=0 && vec !=0)
            {   writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
                dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, 2);
                dataPerMinArray.add(dataPerMin);
            }
            //if(ans.equalsIgnoreCase("yes"))
            //{print(dataPerMin);}
        }
        reader.close();
        writer.close();
        
    }
    
    /**
     * reads in seconds and sets per minute data (NOTE: data is changed to work like minute data)
     */
    public void readInSeconds(String ans, int format)
    {
        for(int i = 0; i < 3; i++)
        {
            reader.nextLine();
        }
        String date, time, axis1, axis2, axis3, steps, lux, iOff, iStand, iSit, iLay;
        int HR, sec = 00, min = 00, hour = 00, month = 00, day =00, year =00;
        double maxHR=0, minHR=999, meanHR=0, maxVec=0, minVec=999999, meanVec=0;
        double vec;
        int count =0, starterCount = 0;
        String dateStr, timeStr, dateComp="";
        //reverse back before using again (NOTE TO PROGRAMMER!!!!!!!) (time and date str to be reversed)
        reader.next();reader.next();dateStr = reader.next();timeStr = reader.next();reader.nextLine(); //reader.nextLine();
        reader.next();reader.next();timeStr = reader.next();
        String[] dateArray = dateStr.split("[/]+");
        month = Integer.parseInt(dateArray[0]);
        day = Integer.parseInt(dateArray[1]);
        year = Integer.parseInt(dateArray[2]);
        String[] timeArray = timeStr.split("[:]+");
        hour = Integer.parseInt(timeArray[0]);
        min = Integer.parseInt(timeArray[1]);
        sec = Integer.parseInt(timeArray[2]);

        for(int i = 0; i <= 4; i++)
        {
            reader.nextLine();
        }
        while(reader.hasNextLine())
        {
            ++sec;
            if(sec == 60){++min; sec=00;}
            if(min == 60){++hour; min=00;}
            if(hour == 24){++day; hour=00;}
            if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){if(day==31){++month;day=01;}}
            if(month==2){if(day==28){++month;day=01;}}
            if(month==4||month==6||month==9||month==11){if(day==30){++month;day=01;}}
            if(month==12){++year;month=01;}
            String input = reader.nextLine();
            String[] textArray = input.split("[, ]+");
            date = (Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year));
            time = (Integer.toString(hour)+":"+Integer.toString(min)+":"+Integer.toString(sec));
            axis1 = Integer.toString(Integer.parseInt(textArray[0])*60);
            axis2 = Integer.toString(Integer.parseInt(textArray[1])*60);
            axis3 = Integer.toString(Integer.parseInt(textArray[2])*60);
            steps = textArray[3];
            HR = Integer.parseInt(textArray[4]);
            lux = textArray[5];
            iOff = textArray[6];
            iStand = textArray[7];
            iSit= textArray[8];
            iLay = textArray[9];
            
            int y = Integer.parseInt(axis1), x = Integer.parseInt(axis2), z = Integer.parseInt(axis3);
            vec = Math.sqrt((x*x)+(y*y)+(z*z));
            
            
            
            if(starterCount == 0){dateComp = date;}
            ++starterCount;
            if(dateComp.equalsIgnoreCase(date))
            {
                if(HR != 0 || vec != 0)
                {   ++count;
                    if(minHR>HR){minHR = HR;}
                    if(maxHR<HR){maxHR = HR;}
                    if(minVec>vec){minVec = vec;}
                    if(maxVec<vec){maxVec = vec;}
                    meanHR+=HR; meanVec+=vec;      
                }
            }else 
            {
                if(HR != 0 || vec != 0 )
                {   meanHR = meanHR/count; meanVec = meanVec/count;
                    count = 0;
                    OverallData overallData = new OverallData(date, maxHR, minHR, meanHR, maxVec, minVec, meanVec);
                    overallDataArray.add(overallData);
                    System.out.println(date);
                    maxHR=0; minHR=999; meanHR=0; maxVec=0; minVec=999999; meanVec=0;
                    dateComp = date; 
                }
            }
            //optional if statement (HR !=0 || vec != 0)
            writer.println(date + ", - ," + time + "," +  HR + ", - ,"+ time + "," + vec);
            DataPerMin dataPerMin = new DataPerMin(date, time, axis1, axis2, axis3, steps, HR, lux, iOff, iStand, iSit, iLay, vec, format);
            dataPerMinArray.add(dataPerMin);
        }
        reader.close();
        writer.close();
        
    }
    

    
   public List overallDataArray() 
   {
       return this.overallDataArray;
   }
   
    /**
     * Returns the filtered array to the driver (filtered to take out data that are outliers or not applicable)
     */
    public List dataPerMinArray()
    {
        return dataPerMinArray;
    }
}