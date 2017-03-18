/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 *
 * @author txsing
 */
public class InOutDegreeDisCal {
    
    /***
     * 
     * @param rawFile
     * @param scalFile
     * @param rawNodeSize
     * @param scalNodeSize
     * @param inOrOut: 0 -> OutDegree; 1 -> InDegree
     * @return 
     */
    public static String computeInOutDegreeDistribution(String rawFile, String scalFile,
            int rawNodeSize, int scalNodeSize, int inOrOut) {
        int[] rawDegreeMap = new int[rawNodeSize];
        int[] scalDegreeMap = new int[scalNodeSize];
        try {
            BufferedReader br_raw = new BufferedReader(new FileReader(rawFile));
            BufferedReader br_scal = new BufferedReader(new FileReader(scalFile));

            HashMap<Integer, Integer> rawDegreeDis = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> scalDegreeDis = new HashMap<Integer, Integer>();

            String rawLine;
            while ((rawLine = br_raw.readLine()) != null) {
                String[] twoV = rawLine.split("\\s+");
                int s = Integer.parseInt(twoV[inOrOut]);
                rawDegreeMap[s]++;
            }
            br_raw.close();

            String scalLine;
            while ((scalLine = br_scal.readLine()) != null) {
                String[] twoV = scalLine.split("\\s+");
                int s = Integer.parseInt(twoV[inOrOut]);
                scalDegreeMap[s]++;
            }
            br_scal.close();
            
            int max_raw = 0, max_scal = 0;
            for (int i = 0; i < rawNodeSize; i++) {
                int t = rawDegreeMap[i];
                max_raw = t > max_raw ? t : max_raw;

                if (rawDegreeDis.containsKey(t)) {
                    rawDegreeDis.put(t, rawDegreeDis.get(t) + 1);
                } else {
                    rawDegreeDis.put(t, 1);
                }
            }

            for (int i = 0; i < scalNodeSize; i++) {               
                int s = scalDegreeMap[i];               
                max_scal = s > max_scal ? s : max_scal;
                
                if (scalDegreeDis.containsKey(s)) {
                    scalDegreeDis.put(s, scalDegreeDis.get(s) + 1);
                } else {
                    scalDegreeDis.put(s, 1);
                }
            }

            int maxDegree = max_raw > max_scal ? max_raw : max_scal;

            List<String> result = new ArrayList<String>();

            for (int i = 0; i <= maxDegree; i++) {
                String dataString = "['" + i + "', ";

                String in_portion = rawDegreeDis.containsKey(i)
                        ? rawDegreeDis.get(i) / (double) rawNodeSize + "" : "0";
                String out_portion = scalDegreeDis.containsKey(i)
                        ? scalDegreeDis.get(i) / (double) scalNodeSize + "" : "0";
                dataString = dataString + in_portion + ", " + out_portion + "]";
                result.add(dataString);
            }

            String resultJson = "";
            for (int i = 0; i <= maxDegree; i++) {
                resultJson = resultJson + ",\n" + result.get(i);
            }
            resultJson = "[" + resultJson.substring(2) + "]";
            return resultJson;
        } catch (Exception e) {
            System.out.println("EXP(InOutDegreeCal): " + e.getStackTrace()[0]);
            return null;
        }
    }
}
