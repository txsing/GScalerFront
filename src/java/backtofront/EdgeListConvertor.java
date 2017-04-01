/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtofront;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author v-xinti
 */
public class EdgeListConvertor {

    public static void getEdgeListFile(File file) {
        if (!isEdgeListFile(file)) {
            String line;
            String result = "";
            int i = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    String[] nodes = line.split("\\s+");
                    for (int j = 0; j < nodes.length; j++) {
                        if (nodes[j].equals("1")) {
                            String edge = String.valueOf(i)
                                    .concat(" ")
                                    .concat(String.valueOf(j));
                            System.out.println(edge);
                            result = result.concat(edge.concat("\r\n"));
                        }
                    }
                    i++;
                }

                try (FileWriter fw = new FileWriter(file, false)) {
                    fw.write(result);
                }

            } catch (Exception e) {
                System.err.println("LOG(EXP) GetEdgeFile: ".concat(e.getMessage()));
            }
        }

    }

    static boolean isEdgeListFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            String[] firstTwoLines = new String[2];

            line = br.readLine();
            firstTwoLines[count++] = line;

            //empty file
            if (line == null) {
                return true;
            }

            if (line.split("\\s+").length > 2) {
                return false;
            }

            while ((line = br.readLine()) != null) {
                if (count >= 2) {
                    return true;
                }
                firstTwoLines[count++] = line;
            }

            //only one line
            if (count == 1) {
                return true;
            }

            String[] line1Nodes = firstTwoLines[0].split("\\s+");
            String[] line2Nodes = firstTwoLines[1].split("\\s+");

            return !(line1Nodes[0].equals("0")
                    && line2Nodes[1].equals("0")
                    && Integer.parseInt(line1Nodes[1]) <= 1
                    && Integer.parseInt(line2Nodes[0]) <= 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
