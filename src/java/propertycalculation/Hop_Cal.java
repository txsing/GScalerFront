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
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 *
 * @author workshop
 */
class Hop_Cal {

    String input = "";
    String output = "";
    int number_thread = 1;
    int s = 0;
    String serverPath = "";

    Hop_Cal(String inStr, String outStr, int number_thread, int s) {
        input = inStr;
        this.number_thread = number_thread;
        output = outStr;
        this.s = s;
        serverPath = this.output;
      //  serverPath = input.substring(0, input.lastIndexOf(File.separator));

    }

    void cal() throws FileNotFoundException {

        HashMap<Integer, HashSet<Integer>> graph = graphConstruct();
        int para = 10;
        ArrayList<ArrayList<Integer>> interval = new ArrayList<>();
        int intver = (max - min) / para;
System.out.println(intver);
System.out.println(max);
System.out.println(min);
        for (int i = 0; i < para; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(min + i * intver);
            if (i == para - 1) {
                temp.add(min + max + 1);
            } else{
                temp.add(min+(i+1)*intver-1);
            }
            interval.add(temp);
        }
        System.out.println(interval);

        ArrayList<Thread> liss = new ArrayList<>();
        ArrayList<BFS_PARA> computed = new ArrayList<>();
        for (ArrayList<Integer> arr : interval) {
            BFS_PARA bfs = new BFS_PARA(graph, arr);
            Thread thr = new Thread(bfs);
            computed.add(bfs);
            liss.add(thr);
            thr.start();
        }
        
        for (Thread thr : liss) {
            try {
                thr.join();
            } catch (InterruptedException ex) {
            }
        }
        
        HashMap<Integer, Double> hops = new HashMap<>();
        double sum = 0;
        for (BFS_PARA bfs: computed){
            for (Entry<Integer, Double> entry: bfs.result.entrySet()){
                sum += entry.getValue();
                if (!hops.containsKey(entry.getKey())){
                    hops.put(entry.getKey(), 0.0);
                }
                hops.put(entry.getKey(), hops.get(entry.getKey()) + entry.getValue());
            }
        }
        System.out.println("Calculation Done");
        
        
        HashMap<Integer, Double> result = new HashMap<>();
        for (Entry<Integer, Double> entry:hops.entrySet()){
             result.put(entry.getKey(), 1.0*entry.getValue()/sum);
        }
        
        
        
        /*
        for (String s : dg1.getVertices()) {
            BFSDistance bfs = new BFSDistance();
            HashMap<String, Double> maps = new HashMap<>();
            maps = bfs.getdistanceMap(dg1, s);

            double vl = 0.0;
            for (Map.Entry<String, Double> en : maps.entrySet()) {
                if (en.getValue() > 0) {
                    vl += en.getValue();
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
        */
      //  double sum = 0.0;
        double threshSum = 0.0;
        double dia = 0.0;
        for (Entry<Integer, Double> en : result.entrySet()) {
            if (threshSum < 0.9) {
                threshSum += en.getValue();
                if (threshSum >= 0.9) {
                    dia = en.getKey();
                }
            }
            System.out.println(en);
       }
        sum = 0.0;
        double v1 = 0;
        for (Entry<Integer, Double> en: hops.entrySet()){
            System.out.println(en.getKey() + "\t" + en.getValue());
            sum += 1.0*en.getKey() * en.getValue();
            v1+=en.getValue();
        }

        double avpl = sum / (1.0*hops.get(0) * (hops.get(0) - 1));
        
        PrintWriter pw = new PrintWriter(serverPath + "avpl.txt");
        pw.println(avpl);
        System.out.println(sum/v1);
        System.out.println(avpl);
        pw.close();
        PrintWriter pw1 = new PrintWriter(serverPath + "effective-diameter.txt");
        pw1.println(dia);
        pw1.close();

    }
    int max = 0;
    int min = Integer.MAX_VALUE;

    private HashMap<Integer, HashSet<Integer>> graphConstruct() throws FileNotFoundException {
        //Graph<String, String> dg1 = new DirectedSparseGraph<>();
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        InputStream input = new FileInputStream(this.input);
        Scanner scanner = new Scanner(input);
        // HashMap<ArrayList<String>, Integer> te = new HashMap<>();
        // HashMap<String, HashSet<String>> outGoing = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] temp = line.split("[^A-Za-z0-9]+");
            try {
                int from = Integer.parseInt(temp[0]);
                //   System.out.println(from+" from");

                int to = Integer.parseInt(temp[1]);
              
                max = Math.max(from, max);
                max = Math.max(to, max);

                min = Math.min(from, min);
                min = Math.min(to, min);

                if (from != to || true) {
                    if (!graph.containsKey(from)) {
                        graph.put(from, new HashSet<Integer>());
                    }
                    if (!graph.containsKey(to)) {
                        graph.put(to, new HashSet<Integer>());
                    }
                    graph.get(from).add(to);
                }

            } catch (Exception e) {

            }
        }
        System.out.println(graph.size());

        scanner.close();
        return graph;
    }

}
