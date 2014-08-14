/*
 * TremorDays: Object that holds a day's Tremor.
 */

package tremornetbeans;

import java.util.Comparator;

/**
 *
 * @author Kirjsten Blodgett
 * @since 8-1-2014
 * @version 1.0
 * catsanddogskb@hotmail.com
 */
public class TremorDays {
    
    String count, date, meanHR, maxHR, minHR, meanVec, maxVec, minVec, meanLength, maxLength, minLength;
    /**
     * constructor for TremorDays
     */
    public TremorDays(String size, String dateParam,String printHR,String printMaxHR,String printMinHR,String printVec,String printMaxVec,String printMinVec,String printLength,String printMaxLength,String printMinLength)
    {
                count = size; 
                date = dateParam;
                meanHR = printHR;
                maxHR = printMaxHR;
                minHR = printMinHR;
                meanVec = printVec;
                maxVec = printMaxVec;
                minVec = printMinVec;
                meanLength = printLength;
                maxLength = printMaxLength;
                minLength = printMinLength;
    }
    
    /**
     * returns how many tremors in the day
     * @return count
     */
    public String count()
    {
        return count;
    }
    
    /**
     * returns date of tremor
     * @return date
     */
    public String date()
    {
        return date;
    }
    
    /**
     * returns the mean HR of tremors that day
     * @return meanHR
     */
    public String meanHR()
    {
        return meanHR;
    }
    
    /**
     * returns the maxHR of the tremors that day
     * @return maxHR
     */
    public String maxHR()
    {
        return maxHR;
    }
    
    /**
     * returns the minHR of the tremors that day
     * @return minHR
     */
    public String minHR()
    {
        return minHR;
    }
    
    /**
     * returns the mean vector magnitude of the tremors from that day
     * @return meanVec
     */
    public String meanVec()
    {
        return meanVec;
    }
    
    /**
     * returns the max vector magnitude of the tremor from that day
     * @return maxVec
     */
    public String maxVec()
    {
        return maxVec;
    }
    
    /**
     * returns the min vector magnitude of the tremors from that day
     * @return minVec
     */
    public String minVec() 
    {
        return minVec;
    }
    
    /**
     * returns the mean length of the tremors from that day
     * @return meanLength
     */
    public String meanLength()
    {
        return meanLength;
    }
    
    /**
     * returns the max length of the tremors from that day
     * @return manLength
     */
    public String maxLength()
    {
        return maxLength;
    }
    
    /**
     * returns the min length of the tremors from that day
     * @return minLength
     */
    public String minLength()
    {
        return minLength;
    }
    
    public static Comparator<TremorDays> OverallDateComparator = new Comparator<TremorDays>() 
    {
        @Override
        public int compare(TremorDays t1, TremorDays t2) 
        {
            String tDay1 = t1.date().toUpperCase();
            String tDay2 = t2.date().toUpperCase();

            //ascending order
            return tDay1.compareTo(tDay2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };
}
