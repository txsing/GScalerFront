/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Anwesha94
 */
public class calcSize {

    public int nodeNumber(TreeMap<Double, Integer> realCoc){
        int sum=0;
        for (Map.Entry<Double, Integer> entry : realCoc.entrySet()) {
         sum+=entry.getValue();
        }
        System.out.println("sum is "+sum);
        return sum;
    }
    
}
