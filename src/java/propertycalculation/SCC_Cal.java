/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package propertycalculation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author workshop
 */
class SCC_Cal {
    String input = "";
    String output = "";
    double size=0;
    SCC_Cal(String inStr, String ouStr) {
        input = inStr;
        output = ouStr;
    }
 private void readInVertex(String file, DirectedGraph dg) throws FileNotFoundException {
        InputStream input = new FileInputStream(file);
        Scanner scanner = new Scanner(input);
        //   scanner.nextLine();
        HashMap<ArrayList<String>, Integer> te = new HashMap<>();
        int max = 0;
        while (scanner.hasNext()) {
            try{
            String[] temp = scanner.nextLine().split("\\s+");
            String from = temp[0];
            if (max < Integer.parseInt(from)) {
                max = Integer.parseInt(from);
            }
            String too = temp[1];
            if (max < Integer.parseInt(too)) {
                max = Integer.parseInt(too);
            }
            dg.addVertex(from);
            dg.addVertex(too);
            ArrayList<String> arr = new ArrayList<>();
            arr.add(from);
            arr.add(too);
            if (!te.containsKey(arr) && !from.equals(too)) {
                dg.addEdge(from, too);
                te.put(arr, 0);
            }}catch (NumberFormatException e){
            
            }
        }
        System.out.println(dg.edgeSet().size());
        scanner.close();
        //      System.out.println(max);
    }

    void cal() {
        try {
            DirectedGraph<String, DefaultEdge> dg = new DefaultDirectedGraph(DefaultEdge.class);
             readInVertex(input, dg);
              size = dg.vertexSet().size();
           
            TreeMap<Double, Integer>       realScc= outputscc(dg);
            printFile(realScc,output+"SCC");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SCC_Cal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private  TreeMap<Double, Integer> outputscc(DirectedGraph<String, DefaultEdge> results) throws FileNotFoundException {
        //   HashMap<Integer,Integer> countV= new HashMap<Integer,Integer>();
        StrongConnectivityInspector spi = new StrongConnectivityInspector(results);
        List<DirectedSubgraph<String, DefaultEdge>> lists = spi.stronglyConnectedSubgraphs();
        
        HashMap<Double, Integer> countV = new HashMap<Double, Integer>();
        
        for (DirectedSubgraph<String, DefaultEdge> set: lists) {
            double indegree = set.vertexSet().size();
            System.out.println("txt");
                    PrintWriter pw = new PrintWriter(this.output+indegree+"SCC.txt");
                    for (DefaultEdge de:set.edgeSet()){
                        pw.println(set.getEdgeSource(de)+" "+set.getEdgeTarget(de));
                    }
                    pw.close();
            if (!countV.containsKey(indegree)) {
                countV.put(indegree, 1);
            } else {
                countV.put(indegree, countV.get(indegree) + 1);
            }
        }

        TreeMap<Double, Integer> treeMap = new TreeMap<Double, Integer>(countV);
        return treeMap;

    }
    private void printFile(TreeMap<Double, Integer> realCoc,String file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        int p=calcSum(realCoc);
        ArrayList<Double> components = new ArrayList<>();
        
        for (Map.Entry<Double, Integer> entry : realCoc.entrySet()) {
            for (int i =0 ; i< entry.getValue() ; i++){
                components.add(1.0*entry.getKey()/p);
         //   System.out.println("here");
            
            }
            
            //double d1=entry.getValue()/(double)p;
            //pw.println(entry.getKey() + " " + d1);
            //System.out.println(1.0*entry.getKey()/size);
        }
        
        for (int i = components.size()-1 ; i >=0 ;i--){
            pw.println(components.get(i));
        }
        pw.close();

    }
    private int calcSum(TreeMap<Double, Integer> realCoc){
        int sum=0;
         for (Map.Entry<Double, Integer> entry : realCoc.entrySet()) {
            sum+=(entry.getValue()*entry.getKey());   
        }
        
        return sum;
    }
}
