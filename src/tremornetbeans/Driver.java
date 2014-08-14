/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tremornetbeans;

/**
 * Driver: Make a program to record and differentiate between tremors and everyday life using data from a movement device. 
 * 
 * @author Kirjsten Blodgett
 * @version 2.0
 * @since 7-9-2014 8-7-2014
 * email: catsanddogskb@hotmail.com
 * 
 */
import java.io.*;
public class Driver
{
    public static void main(String[] args) throws IOException
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               new TremorGUI().setVisible(true);   
            }
        });
    }
}