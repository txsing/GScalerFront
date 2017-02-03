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
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeDebug.map;

/**
 *
 * @author Anwesha94
 */
public class trial {
    public static void main(String[] args) throws FileNotFoundException, IOException,NullPointerException {
        trial r1=new trial();
       //r1.fill();
        
    }
    public  void fill(String p1,String p2,String p3,String p4)throws FileNotFoundException, IOException{
        InputStream input = new FileInputStream(p1);
        BufferedReader bi = new BufferedReader(new InputStreamReader(input));
         BufferedWriter out;
        
        InputStream input1 = new FileInputStream(p2);
        BufferedReader br = new BufferedReader(new InputStreamReader(input1));
        HashMap<Double,Double> list1=new HashMap<>();
        HashMap<Double,Double> list2=new HashMap<>();
        String l1=bi.readLine();
        String l2=br.readLine();
        String[] writeLaterA1 = new String[1000];int t=0;
        String[] writeLaterA2 = new String[1000];
        while(l1!=null){
              String []temp1=l1.split("\\s+");
             
              
              double a1=Double.parseDouble(temp1[0]);
               double b1=Double.parseDouble(temp1[1]);
               
                list1.put(a1,b1);
             l1=bi.readLine();       
        }
       
    
    
    while(l2!=null){
             
              String []temp2=l2.split("\\s+");
              
              double a2=Double.parseDouble(temp2[0]);
               double b2=Double.parseDouble(temp2[1]);
               
                list2.put(a2,b2);
             l2=br.readLine();       
        }
       
    
    //adding elements to list 1 which are there in list2
    for (double name: list1.keySet()){
        if(list2.containsKey(name)){
            
        }
        else{
         list2.put(name,list1.get(name));
    }
    }
    
    for (double name: list2.keySet()){
        if(list1.containsKey(name)){
            
        }
        else{
         list1.put(name,list2.get(name));
    }
    }
    
//    for (double name: list2.keySet()){
//
//            String key =name+"";
//            String value = list2.get(name).toString();  
//           System.out.println(key+" "+value);   
//        }
    
   Map<Double,Double> treeMap = new TreeMap<Double,Double>(list1);
   Map<Double,Double> treeMap1 = new TreeMap<Double,Double>(list2);

   
   
   PrintWriter pw = new PrintWriter(p3);
  for (double name: treeMap.keySet()){

            String key =name+"";
            String value = treeMap.get(name).toString();  
           String full=key+" "+value;
           pw.println(full);
    }
  pw.close();

      
    
    PrintWriter pw1 = new PrintWriter(p4);
  for (double name: treeMap1.keySet()){

            String key =name+"";
            String value = treeMap1.get(name).toString();  
           String full=key+" "+value;
           pw1.println(full);
    }
  pw1.close();

    }  
}

