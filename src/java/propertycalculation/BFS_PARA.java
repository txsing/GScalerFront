/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertycalculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author workshop
 */
public class BFS_PARA implements Runnable{
    
    HashMap<Integer, Double> result = new HashMap<>();
    HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
    ArrayList<Integer> arr = new ArrayList<>();
    
    BFS_PARA(HashMap<Integer, HashSet<Integer>> graph, ArrayList<Integer> arr) {
           this.graph = graph;
           this.arr = arr;
    }
    
    
    @Override
    public void run() {
        
        for (Integer id: graph.keySet()){
      
            if (id >=arr.get(0) && id <=arr.get(1)){
                if (!result.containsKey(0)){
                result.put(0,1.0);}
                else{
                result.put(0, result.get(0)+1);
                }
                HashSet<Integer> visited = new HashSet<>();
                HashSet<Integer> current = new HashSet<>();
                for (int i : graph.get(id)){
                    current.add(i);
                }
                int level = 1;
                while (current.size()>0){
                    if (!result.containsKey(level)){
                        result.put(level, 0.0+current.size());
                    }else{
                    result.put(level, result.get(level)+current.size());}
                    HashSet<Integer> temp = new HashSet<>();
                    for (int cur : current){
                        /// the target node
                        if (graph.containsKey(cur)){
                            for (int j : graph.get(cur)){
                                // the nexthop target node
                                if (!visited.contains(j)){
                                    temp.add(j);
                                    visited.add(j);
                                }
                            }
                        }
                    }
                    
                    current.clear();
                    current = temp;
                    level++;
                }
            }
        }
    }
    
}
