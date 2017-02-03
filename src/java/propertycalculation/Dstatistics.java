/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package propertycalculation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author workshop
 */
public class Dstatistics {
    String input1, input2;
    String outStr;
    Dstatistics(String inStr, String outStr, String inStr2) {
        input1 = inStr;
        this.input2 = inStr2;
        this.outStr = outStr;
    }
     private double getDStatistics(TreeMap<Double, Double> sample, TreeMap<Double, Double> real) {
        double results = 0;
        TreeMap<Double, Double> pmf1 = new TreeMap<>();
        TreeMap<Double, Double> pmf2 = new TreeMap<>();

        //   sample.remove(0.0);
        //  real.remove(0.0);
        double sum1 = 0;
        for (Map.Entry<Double, Double> entry : sample.entrySet()) {
            sum1 += entry.getValue();
        }
        double sum2 = 0;
        for (Map.Entry<Double, Double> entry : real.entrySet()) {
            sum2 += entry.getValue();
        }

        for (Map.Entry<Double, Double> entry : real.entrySet()) {
            //  Pair pair = new Pair(entry.getKey(),1.0*entry.getValue()/sum2);
            pmf2.put(entry.getKey(), 1.0 * entry.getValue() / sum2);
        }

        for (Map.Entry<Double, Double> entry : sample.entrySet()) {
            //      Pair pair = new Pair(entry.getKey(),1.0*entry.getValue()/sum1);
            pmf1.put(entry.getKey(), 1.0 * entry.getValue() / sum1);
        }
//pmf1.
        for (Map.Entry<Double, Double> entry : pmf1.entrySet()) {
            if (!pmf2.containsKey(entry.getKey())) {
                pmf2.put(entry.getKey(), 0.0);
            }
        }

        for (Map.Entry<Double, Double> entry : pmf2.entrySet()) {
            // System.out.println(entry.getKey()+"ssss");
            if (!pmf1.containsKey(entry.getKey())) {
                pmf1.put(entry.getKey(), 0.0);
            }
        }

        ArrayList<Double> cmf1 = new ArrayList<>();
        ArrayList<Double> cmf2 = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Double, Double> entry : pmf2.entrySet()) {
            if (i != 0) {
                cmf1.add(pmf1.get(entry.getKey()) + cmf1.get(i - 1));
                cmf2.add(entry.getValue() + cmf2.get(i - 1));

            } else {
                cmf1.add(pmf1.get(entry.getKey()));
                cmf2.add(entry.getValue());

            }
            i++;
        }
        double max = 0;
        // System.out.println(sample);
        //   System.out.println(cmf1);
        // System.out.println(cmf1.get(1)-cmf2.get(1)+ " === "+pmf2.firstKey());
        for (int t = 0; t < cmf1.size(); t++) {
            if (max < Math.abs(cmf1.get(t) - cmf2.get(t))) {
                max = Math.abs(cmf1.get(t) - cmf2.get(t));
            }
        }
        return limitPrecision(max);
    }
public double limitPrecision(double number) {
		int maxDigitsAfterDecimal = 4;
    int multiplier = (int) Math.pow(10, maxDigitsAfterDecimal);
    return number* multiplier / multiplier;

}
    void cal() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(this.outStr);
        pw.println(this.getDStatistics(readIn(input1), readIn(input2)));
        pw.close();
    }
    
    TreeMap<Double,Double> readIn(String file){
    InputStream input = null;
    TreeMap<Double,Double> result = new TreeMap<>();    
    try {
            input = new FileInputStream(file);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNext()){
            String line  = scanner.nextLine();
            double key = Double.parseDouble(line.trim().split("\\s+")[0]);
            double value = Double.parseDouble(line.trim().split("\\s+")[1]);
            //System.out.println(key+"-> "+value);
            result.put(key, value);
            
            }
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dstatistics.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(Dstatistics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      return result;
    }
}