/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author workshop
 */
public class CCThread implements Runnable {

    int i = 0;

    CCThread(int i) {
        this.i = i;
    }
    HashSet<String> edgeMap = new HashSet<>();
     HashMap<String, HashSet<String>> neighbourNode = new HashMap<>();
     ArrayList<String> temp = new ArrayList<>();
    CCThread(HashSet<String> edgeMap, HashMap<String, HashSet<String>> neighbourNode, ArrayList<String> temp) {
        this.edgeMap = edgeMap;
        this.neighbourNode = neighbourNode;
        this.temp = temp;
    }
  

    @Override
    public void run() {
        globalAverage( edgeMap,neighbourNode, temp);
    }
private void globalAverage(HashSet<String> edgeMap, HashMap<String, HashSet<String>> neighbourNode, ArrayList<String> temp ) {
        double sum = 0;
        //Method 2
        double result = 0;
        for (String s : temp) {
            int count3 = 0;
            int total = 0;

            total +=  neighbourNode.get(s).size() * ( neighbourNode.get(s).size() - 1);
            for (String from :  neighbourNode.get(s)) {
                for (String to :  neighbourNode.get(s)) {
                    String ts = from + "_" + to;
                     if (edgeMap.contains(ts)) {
                        count3++;
                    }
                }
            }
            
            if (total != 0) {
                result += 1.0 * count3 / total;
                sum += 1.0 * count3 / total;
                System.out.println(1.0 * count3 / total);
            } else{
                System.out.println(0);
            }

        }
    
    }
    
}
