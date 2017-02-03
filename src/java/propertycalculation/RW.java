/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Anwesha94
 */
public class RW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
    }
    public static void fill(String p1,String p2)throws FileNotFoundException, IOException{
        
        InputStream input = new FileInputStream(p1);
        BufferedReader bi = new BufferedReader(new InputStreamReader(input));
         BufferedWriter out;
        
        InputStream input1 = new FileInputStream(p2);
        BufferedReader br = new BufferedReader(new InputStreamReader(input1));
        
        String l1=bi.readLine();
        String l2=br.readLine();
        String[] writeLater = new String[1000];int t=0;
        
        while(l1!=null&&l2!=null){
            System.out.println(l1+"        "+l2);
             
            String []temp1=l1.split("\\s+");
            String []temp2=l2.split("\\s+");
            
            double a1=Double.parseDouble(temp1[0]);
            double a2=Double.parseDouble(temp2[0]);
            System.out.println("a1->"+a1+"   a2->"+a2);
            if(a1==a2){
             System.out.println("In if");
            }
            else{
                writeLater[t]=l2;
                System.out.println(writeLater[t]);
                 out = new BufferedWriter(new FileWriter(p1,true));
                 out.write(l2);
                 out.close();
                 l1=bi.readLine();
               
            }
            
            l1 = bi.readLine();
            l2=br.readLine();
        }
        
               
                bi.close();
                br.close();
                
                
                
                
    }
}
    
    

