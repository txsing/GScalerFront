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
 * @author v-xinti
 */
public class NodeEdgeSizeCal {

    /***
     * Get the node and edge size of a graph by analysis the graph file.
     * @param graphFilePath
     * @return int[0]: node size; int[1]:edge size
     */
    public static int[] getNodeAndEdgeSize(String graphFilePath) {
        int[] nodeAndEdgeSize = new int[2];
        if(graphFilePath == null || graphFilePath.equals("")){
            return null;
        }
        
        try {         
            BufferedReader br = new BufferedReader(new FileReader(graphFilePath));
            int nodesize;
            int edgesize = 0;
            List<String> nodesWithDuplicates = new ArrayList<String>();
            String line;
            while ((line=br.readLine())!=null) {
                edgesize++;
                String[] twoV = line.split("\\s+");
                nodesWithDuplicates.add(twoV[0]);
                nodesWithDuplicates.add(twoV[1]);
            }
            br.close();
                                   
            Set<String> allNodes = new HashSet<String>();
            for(String i : nodesWithDuplicates){
                allNodes.add(i);
            }
            
            nodesize = allNodes.size();
            nodeAndEdgeSize[0] = nodesize;
            nodeAndEdgeSize[1] = edgesize;   
            return nodeAndEdgeSize;
        } catch (Exception e) {
            System.err.println("(EXP)N&ESizeCal: "+e.getStackTrace()[0]);
            return null;
        }   
    }
}
