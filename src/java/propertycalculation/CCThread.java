/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
//import static fakedcomparison.FakedComparison.file;
//import static fakedcomparison.FakedComparison.realHop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     //   Set<String> ss = neighbourNode.keySet();
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
