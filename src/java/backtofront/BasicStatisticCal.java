/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtofront;

import java.io.BufferedReader;
import java.io.FileReader;
import propertycalculation.PropertyCalculation;

/**
 *
 * @author v-xinti
 */
public class BasicStatisticCal {

    public static String[] getEffectiveDiaAndAvgShortestPathLen(String file1, String file2) {
        try {
            String avpl = "";
            String dia = "";
            String[] args1 = new String[8];
            args1[0] = "-i";
            args1[1] = file1;
            args1[2] = "-m";
            args1[3] = "hop";
            args1[4] = "-o";
            args1[5] = file2;
            args1[6] = "-s";
            args1[7] = "0";
            PropertyCalculation pc1 = new PropertyCalculation();
            pc1.doMain(args1);

            BufferedReader br = new BufferedReader(new FileReader(file2 + "avpl.txt"));
            String oc1 = br.readLine();
            br.close();
            int c1 = 1;
            char ch11;
            for (int i = 0; i < oc1.length(); i++) {
                if (c1 > 3) {
                    break;
                }
                ch11 = oc1.charAt(i);
                if (ch11 == '0') {
                    avpl += ch11;
                } else {
                    c1++;
                    avpl += ch11;
                }
            }

            br = new BufferedReader(new FileReader(file2 + "effective-diameter.txt"));
            dia = br.readLine();
            br.close();
            return new String[]{avpl, dia};
        } catch (Exception e) {
            return null;
        }
    }

    public static String getAvgClusteringCof(String file1, String file2, String uploadDir) {
        try {
            String coc = "";
            String[] args2 = new String[6];
            args2[0] = "-i";
            args2[1] = file1;
            args2[2] = "-o";
            args2[3] = file2;
            args2[4] = "-m";
            args2[5] = "coc";
            PropertyCalculation pc1 = new PropertyCalculation();
            pc1.doMain(args2);
            BufferedReader br = new BufferedReader(new FileReader(uploadDir.concat("ori.txt2COC.txt")));
            String oc = br.readLine();
            br.close();
            int c = 1;
            char ch1;
            for (int i = 0; i < oc.length(); i++) {
                if (c > 3) {
                    break;
                }
                ch1 = oc.charAt(i);
                if (ch1 == '0') {
                    coc += ch1;
                } else {
                    c++;
                    coc += ch1;
                }
            }
            return coc;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getKSD() {
        return null;
    }

    public static String[] getGraphVizJson(String filePath, int nodeSize) {
        try {
            String nodeJson = "[";
            for (int i = 0; i < nodeSize - 1; i++) {
                nodeJson = nodeJson + "{data: {id: \'" + i + "\'}},\n";
            }
            nodeJson = nodeJson.concat("{data: {id: \'" + (nodeSize - 1) + "\'}}]");
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String edgeJson = "";
            String line;
            while ((line = br.readLine()) != null) {
                String[] twoV = line.split("\\s+");
                edgeJson = edgeJson + ",\n{data: {source: \'" + twoV[0] + "\', target: \'"
                        + twoV[1] + "\'}}";
            }
            edgeJson = "[" + edgeJson.substring(2) + "]";
            return new String[]{nodeJson, edgeJson};
        } catch (Exception e) {
            return null;
        }

    }
}
