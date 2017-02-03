/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author workshop
 */
public class DegreeCal {

    String input = "";
    String output = "";

    DegreeCal(String inStr, String ouStr) {
        input = inStr;
        output = ouStr;
    }
long starttime = 0;
long endtime = 0;
    void cal() throws FileNotFoundException {
      //  this.starttime = System.currentTimeMillis();
        DirectedGraph<String, DefaultEdge> dg = new DefaultDirectedGraph(DefaultEdge.class);
        readInVertex(input, dg);
        
      this.printFile(this.outputInDegree(dg), output + "_inDegree.txt");
       this.printFile(this.outputOutDegree(dg), output + "_outDegree.txt");
       this.printFile(this.outputBiDegree(dg), output + "_biDegree.txt");
    }

    private void printFile(TreeMap<Double, Integer> realCoc, String outFile) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(outFile);
        calcSize cs=new calcSize();
        int val= cs.nodeNumber( realCoc);
        for (Map.Entry<Double, Integer> entry : realCoc.entrySet()) {
            double d1=((entry.getValue())/(double)val);
            pw.println(entry.getKey() + " " + d1);
        }
        pw.close();

    }

    private void printFile(HashMap<ArrayList<Integer>, Integer> bidegree, String outFile) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(outFile);
        for (Map.Entry<ArrayList<Integer>, Integer> entry : bidegree.entrySet()) {
            pw.println(entry.getKey().get(0) + " " + entry.getKey().get(1) + " " + entry.getValue());
        }
        pw.close();

    }

    private TreeMap<Double, Integer> outputInDegree(DirectedGraph graph) throws FileNotFoundException {
        //   PrintWriter pw = new PrintWriter("indegree.txt");
        HashMap<Double, Integer> countV = new HashMap<Double, Integer>();
        for (Object v : graph.vertexSet()) {
            double indegree = graph.inDegreeOf(v);
            if (!countV.containsKey(indegree)) {
                countV.put(indegree, 1);
            } else {
                countV.put(indegree, countV.get(indegree) + 1);
            }
        }
        //countV.keySet()
        // Collections.sort(null, pw);
        TreeMap<Double, Integer> treeMap = new TreeMap<Double, Integer>(countV);
        return treeMap;
    }

    private HashMap<ArrayList<Integer>, Integer> outputBiDegree(DirectedGraph graph) throws FileNotFoundException {
        HashMap<ArrayList<Integer>, Integer> countV = new HashMap<ArrayList<Integer>, Integer>();
        for (Object v : graph.vertexSet()) {
            int indegree = graph.inDegreeOf(v);
            int outdegree = graph.outDegreeOf(v);
            ArrayList<Integer> arr = new ArrayList<Integer>();
            arr.add(indegree);
            arr.add(outdegree);
            if (!countV.containsKey(arr)) {
                countV.put(arr, 1);
            } else {
                countV.put(arr, countV.get(arr) + 1);
            }
        }
        return countV;
    }

    private TreeMap<Double, Integer> outputOutDegree(DirectedGraph graph) throws FileNotFoundException {
        HashMap<Double, Integer> countV = new HashMap<Double, Integer>();
        for (Object v : graph.vertexSet()) {
            double outdegree = graph.outDegreeOf(v);
            if (!countV.containsKey(outdegree)) {
                countV.put(outdegree, 1);
            } else {
                countV.put(outdegree, countV.get(outdegree) + 1);
            }
        }
        TreeMap<Double, Integer> treeMap = new TreeMap<Double, Integer>(countV);
        return treeMap;
    }

    private void readInVertex(String file, DirectedGraph dg) throws FileNotFoundException {
        InputStream input = null;

        input = new FileInputStream(file);
        Scanner scanner = new Scanner(input);
        HashSet<Integer> nodeset = new HashSet<>();
        //   scanner.nextLine();
        HashMap<ArrayList<String>, Integer> te = new HashMap<>();
        int max = 0;
        while (scanner.hasNext()) {
            try {

                String[] temp = scanner.nextLine().split("\\s+");
                String from = temp[0];

                if (max < Integer.parseInt(from)) {
                    max = Integer.parseInt(from);
                }
                String too = temp[1];
                if (max < Integer.parseInt(too)) {
                    max = Integer.parseInt(too);
                }
                nodeset.add(Integer.parseInt(too));
                nodeset.add(Integer.parseInt(from));
                
                dg.addVertex(from);
                dg.addVertex(too);
                ArrayList<String> arr = new ArrayList<>();
                arr.add(from);
                arr.add(too);
                if (!te.containsKey(arr) && !from.equals(too)) {
                    dg.addEdge(from, too);
                    te.put(arr, 0);
                }
            } catch (NumberFormatException e) {

            }
        }
        
        for (int i=0;i<max;i++){
            String temp="";
            temp=""+i;
            if (!dg.containsVertex(temp)){
                dg.addVertex(temp);
            }
        }
        System.out.println(dg.edgeSet().size());
        ArrayList<Integer> nodeArr = new ArrayList<>(nodeset);
        Collections.sort(nodeArr);
        HashMap<Integer,Integer> maps = new HashMap<>();
        for(int i=0;i<nodeArr.size();i++){
           maps.put(nodeArr.get(i), i);
           
        }
         System.out.println("node_size"+dg.vertexSet().size());
        
    //   PrintWriter pw = new PrintWriter(output);
    //   for(Object e:dg.edgeSet()){
    //       if (maps.containsKey(Integer.parseInt(dg.getEdgeSource(e).toString())) && maps.containsKey(Integer.parseInt(dg.getEdgeTarget(e).toString())))pw.println(maps.get(Integer.parseInt(dg.getEdgeSource(e).toString()))+" "+maps.get(Integer.parseInt(dg.getEdgeTarget(e).toString())));
    //   }
    //   pw.close();
    //    scanner.close();
       
        //      System.out.println(max);
    }

  

}
