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
public class ProcessThread implements Runnable {

    int i = 0;

    ProcessThread(int i) {
        this.i = i;
    }
    ArrayList<Integer> inte = new ArrayList<>();
    int max = 0;
    String file = "Slashdot0811.txt";
    TreeMap<Double, Integer> realHop = new TreeMap<>();
    int p, q;

    ProcessThread(int p, int q, int i, ArrayList<Integer> get) {
        this.i = i;
        this.inte = get;
        this.p = p;
        this.q = q;
        // this.dg1 =dg;
        //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    Graph<String, String> dg1;
    HashMap<String,HashSet<String>> outgoing;
    String dir = "";
    ProcessThread(int i, ArrayList<Integer> get, Graph<String, String> dg1) {
        this.i = i;
        this.inte = get;
        this.dg1 = dg1;
        
//  this.p=p;
//  this.q=q;
        // this.dg1 =dg;
        //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /// DirectedGraph<String, DefaultEdge> dg1 = new DefaultDirectedGraph(DefaultEdge.class);

    ProcessThread(int i, ArrayList<Integer> get, HashMap<String, HashSet<String>> outGoing) {
          this.i = i;
        this.inte = get;
        this.outgoing =outGoing ;
       
    }

    @Override
    public void run() {
        try {
           realHop = newHotPlot(dg1);
           printFile(realHop);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void printFile(TreeMap<Double, Integer> realCoc) throws FileNotFoundException {
       PrintWriter pw = new PrintWriter( file+""+i + "_" + inte.get(0) + "_" + inte.get(1));
          TreeMap<Double,Double> results = new TreeMap<>();
      
        for (Map.Entry<Double, Integer> entry : realCoc.entrySet()) {
       //     pw.println(entry.getKey() + " " + entry.getValue());
                 if (!results.containsKey(entry.getKey())){
                    results.put(entry.getKey(), entry.getValue()*1.0);
                } else {
                    results.put(entry.getKey(), entry.getValue()+results.get(entry.getKey()));
                }
        //    System.out.println(entry.getKey() + " " + entry.getValue());
        }
        pw.close();
    }

   
   HashMap<String, Double> closeness = new HashMap<>();

    private TreeMap<Double, Integer> newHotPlot(Graph<String, String> dg1) {
        TreeMap<Double, Integer> result = new TreeMap<Double, Integer>();
   int i = 0;
        for (String s : dg1.getVertices()) {
     int k = Integer.parseInt(s);
           if (k <= inte.get(1) && k >= inte.get(0)) {
                BFSDistance bfs = new BFSDistance();
                   HashMap<String, Double> maps = new HashMap<>();
                maps = bfs.getdistanceMap(dg1, s);

                i++;
               System.out.println(i + "_" + k);
                double vl = 0.0;
                for (Map.Entry<String, Double> en : maps.entrySet()) {
                    if (en.getValue() > 0) {
                        vl +=  en.getValue();
                    }
                    double size = 0.0 + en.getValue();
                    if (en.getValue() >= 0) {
                        if (result.containsKey(size)) {
                            result.put(size, result.get(size) + 1);
                        } else {
                            result.put(size, 1);
                        }
                    }
                }
                  maps.clear();
            }

        }
        return result;

    }

  
}
