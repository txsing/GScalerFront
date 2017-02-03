/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author workshop
 */
class COC_Cal {

    String input = "";
    String output = "";
    int pthread=0;
    COC_Cal(String inStr, String ouStr, int pthread) {
        this.input = inStr;
        output = ouStr;
        this.pthread = pthread;
    }

    void cal() throws InterruptedException {
        try {
            DirectedGraph<String, DefaultEdge> dg2 = new DefaultDirectedGraph(DefaultEdge.class);
            HashSet<String> edgeMap = new HashSet<>();
            HashMap<String, HashSet<String>> neighbourNode = new HashMap<>();
            
            readInVertex(input, edgeMap, neighbourNode);
            // readInVertex(input,dg2);
            ArrayList<String> sets = new ArrayList<String>(neighbourNode.keySet());
            int size= sets.size();
            int inter  = (int) Math.ceil(size/this.pthread);
            
            for (int i=0;i<this.pthread;i++){
                ArrayList<String> temp=new ArrayList<>();
            for (int j=i*inter;j<(i+1)*inter && j<size; j++){
                temp.add(sets.get(j));
            }
            CCThread pt = new CCThread(edgeMap,neighbourNode,temp);
            Thread t=new Thread(pt);
             t.start();
             t.join();
            globalAverage(edgeMap, neighbourNode);
        }
            // newoutputGlobalAverageCoC(dg2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(COC_Cal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private  void newoutputGlobalAverageCoC(DirectedGraph<String, DefaultEdge> results) throws FileNotFoundException {
        //     results.
        Set<String> ss = results.vertexSet();
        PrintWriter pw = new PrintWriter(output + "2COC.txt");
        double sum = 0;
        //Method 2
        double result = 0;
        for (String s : ss) {
            int count3 = 0;
            int total = 0;

            Set<DefaultEdge> incoming = results.incomingEdgesOf(s);
            Set<DefaultEdge> outgoing = results.outgoingEdgesOf(s);
            HashSet<String> incoming1 = new HashSet<>();
            //   incoming.addAll(outgoing);

            for (DefaultEdge dd : outgoing) {
                incoming1.add(results.getEdgeSource(dd));
                incoming1.add(results.getEdgeTarget(dd));

            }

            for (DefaultEdge dd : incoming) {
                incoming1.add(results.getEdgeSource(dd));
                incoming1.add(results.getEdgeTarget(dd));
                //    incoming1.add(dd);
            }
            incoming1.remove(s);
            total += incoming1.size() * (incoming1.size() - 1);
            //     System.out.println(incoming1.size());
            for (String from : incoming1) {
                for (String to : incoming1) {

                    if (results.containsEdge(to, from)) {
                        count3++;
                    }

                }
            }
            System.out.println(count3 + " " + total + " " + s);
            if (total != 0) {
                result += 1.0 * count3 / total;
                //  pw.println(1.0*count3 / total + " " + incoming1.size());
                sum += 1.0 * count3 / total;
            } else {
                //  pw.println(0 + " " + incoming1.size());
            }

        }
        System.out.println(results.vertexSet().size());
        pw.println(1.0 * result / results.vertexSet().size());
        pw.close();
        System.out.println(1.0 * result / results.vertexSet().size());
    }

    private void newoutputGlobalAverageCoC3(DirectedGraph<String, DefaultEdge> graph) throws FileNotFoundException {
        //     results.
        Set<String> ss = graph.vertexSet();
        double sum = 0;
        //Method 2
        double result = 0;
        for (String s : ss) {
            int count3 = 0;
            int total = 0;

            Set<DefaultEdge> incoming = graph.incomingEdgesOf(s);
            Set<DefaultEdge> outgoing = graph.outgoingEdgesOf(s);
            HashSet<String> incoming1 = new HashSet<>();
            //   incoming.addAll(outgoing);

            for (DefaultEdge dd : outgoing) {
                incoming1.add(graph.getEdgeSource(dd));
                incoming1.add(graph.getEdgeTarget(dd));

            }

            for (DefaultEdge dd : incoming) {
                incoming1.add(graph.getEdgeSource(dd));
                incoming1.add(graph.getEdgeTarget(dd));
            }
            incoming1.remove(s);
            total += incoming1.size() * (incoming1.size() - 1);
            for (String from : incoming1) {
                for (String to : incoming1) {
                    if (graph.containsEdge(to, from)) {
                        count3++;
                    }
                }
            }
            //  System.out.println(count3 + " " + total + " " + s);
            if (total != 0) {
                result += 1.0 * count3 / total;
                sum += 1.0 * count3 / total;
            } else {
            }

        }
        System.out.println(graph.vertexSet().size());
        try (PrintWriter pw = new PrintWriter(output + "COC.txt")) {
            
            pw.println(1.0 * result / graph.vertexSet().size());
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertyCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(1.0 * result / graph.vertexSet().size());
    }

    private void readInVertex(String file, DirectedGraph dg) throws FileNotFoundException {
        InputStream input = new FileInputStream(file);
        Scanner scanner = new Scanner(input);
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

        for (int i = 0; i < max; i++) {
            String temp = "";
            temp = "" + i;
            if (!dg.containsVertex(temp)) {
                dg.addVertex(temp);
            }
        }
        System.out.println(dg.edgeSet().size());
        scanner.close();
        //      System.out.println(max);
    }

    private void readInVertex(String file, HashSet<String> edgeMap, HashMap<String, HashSet<String>> neighbourNode) throws FileNotFoundException {
        InputStream input = new FileInputStream(file);
        Scanner scanner = new Scanner(input);
        HashMap<ArrayList<String>, Integer> te = new HashMap<>();
        int max = 0;
        while (scanner.hasNext()) {
            try {
                String[] temp = scanner.nextLine().split("\\s+");
                //System.out.println(temp[0]+" is temp0");
                String from = temp[0];
                if (max < Integer.parseInt(from)) {
                    max = Integer.parseInt(from);
                }
                String too = temp[1];
                //System.out.println(temp[1]+" is temp1");
                if (max < Integer.parseInt(too)) {
                    max = Integer.parseInt(too);
                }
                if (!neighbourNode.containsKey(from)) {
                    neighbourNode.put(from, new HashSet<String>());
                }
                if (!neighbourNode.containsKey(too)) {
                     neighbourNode.put(too, new HashSet<String>());
                }
               
                String edgeS = from + "_" + too;
                edgeMap.add(edgeS);
                neighbourNode.get(from).add(too);
                neighbourNode.get(too).add(from);

            } catch (NumberFormatException e) {

            }
        }
        
        for (int i = 0; i < max; i++) {
            String temp = "";
            temp = "" + i;
            if (!neighbourNode.containsKey(temp)) {
                neighbourNode.put(temp, new HashSet<String>());
              }
        }

        scanner.close();

    }

    private void globalAverage(HashSet<String> edgeMap, HashMap<String, HashSet<String>> neighbourNode) {
        Set<String> ss = neighbourNode.keySet();
        double sum = 0;
        //Method 2
        double result = 0;
        for (String s : ss) {
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
         //    System.out.println(count3 + " " + total + " " + s);
            if (total != 0) {
                result += 1.0 * count3 / total;
                sum += 1.0 * count3 / total;
            } 

        }
        //System.out.println(graph.vertexSet().size());
        try (PrintWriter pw = new PrintWriter(output + "2COC.txt")) {
            pw.println(1.0 * result / ss.size());
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertyCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(1.0 * result / ss.size());
    }

    private CCThread CCThread(HashSet<String> edgeMap, HashMap<String, HashSet<String>> neighbourNode, ArrayList<String> temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
