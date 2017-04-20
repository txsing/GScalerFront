/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import edu.uci.ics.jung.graph.Graph;
import java.util.HashMap;
import java.util.HashSet;
/**
 *
 * @author workshop
 */
public class BFSDistance {

    
    HashMap<String, Double> getdistanceMap(Graph<String, String> dg1, String s) {
        HashSet<String> visited = new HashSet<>();
        HashSet<String> neighbours = new HashSet<>();
        neighbours.addAll(dg1.getSuccessors(s));
        double hop = 1;
        HashMap<String, Double> result = new HashMap<>();
        result.put(s, 0.0);

        while (neighbours.size() > 0) {
            HashSet<String> temp = new HashSet<>();

            for (String t : neighbours) {
                if (!visited.contains(t) && !s.equals(t)) {
                    result.put(t, hop);
                    visited.add(t);
                    temp.addAll(dg1.getSuccessors(t));
                }
            }
            hop++;
            neighbours.clear();
            neighbours = temp;
        }
        for (String t : dg1.getVertices()) {
            if (!t.equals(s) && !visited.contains(t)) {
                result.put(t, -1.0);
            }
        }
        return result;
    }

    HashMap<String, Double> getdistanceMap(HashMap<String, HashSet<String>> outgoing, String s) {
        HashSet<String> visited = new HashSet<>();
        boolean flag = true;
        HashSet<String> neighbours = new HashSet<>();
        neighbours.addAll(outgoing.get(s));
        double hop = 1;
        HashMap<String, Double> result = new HashMap<>();
        result.put(s, 0.0);

        while (neighbours.size() > 0) {
            HashSet<String> temp = new HashSet<>();

            for (String t : neighbours) {
                if (!visited.contains(t) && !s.equals(t)) {
                    result.put(t, hop);
                    visited.add(t);
                    if (outgoing.containsKey(t)) {
                        temp.addAll(outgoing.get(t));
                    }
                }
            }
            hop++;
            neighbours.clear();
            neighbours = temp;
        }

        return result;
    }
    

}



