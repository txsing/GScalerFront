/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class copying {
    public static void main(String args[]) throws FileNotFoundException, IOException{
    }
    public static void copy(String p1,String p2)throws FileNotFoundException, IOException{
         BufferedWriter out;
        FileReader fileReader = new FileReader(p1);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    List<String> lines = new ArrayList<String>();
    String line = null;
    while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
    }
    bufferedReader.close();

    Collections.sort(lines, Collator.getInstance());
    for(String a1:lines){
        
    }

    out = new BufferedWriter(new FileWriter(p2));
    for(String str: lines) {
        System.out.println(str);
      out.write(str+System.lineSeparator());
      out.flush();
    }
    out.close();
    }
    
}
