package spanning_tree;

import java.util.Arrays;

/*
* Time Complexity with Adjacency Matrix : O(V2)
* Time Complexity with Adjacency List : O(ElogV), while calculating minimum key vertex with binary heap
* */

public class Prim {

    // Initialisation of graph
    static int[][] graph = new int[][]{{0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}};

    // Total no. of vertices
    private static int V = graph.length;

    // To keep track of parents, minimum weight key and included vertices
    static int[] parent = new int[V];
    static int[] key = new int[V];
    static boolean[] included = new boolean[V];

    public static void main(String[] args) {

        Prim prim = new Prim();
        prim.createMSTWithPrim(graph);
        prim.printMST(parent,graph);

    }

    // Utility Method to find the vertex with the minimum value
    private int minKey(int[] key, boolean[] included) {
        int minimumValue = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < V; i++) {
            if (key[i] < minimumValue && !included[i]) {
                minimumValue = key[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    //Utility Method to print MST
    private void printMST(int[] parent, int[][] graph)
    {
        // Loop started from 1 because the first value is the root itself
        System.out.println("\nMinimum Spanning Tree Using Prims's Algorithm\n");
        System.out.println("Edge\tWeight");
        for(int i=1;i<V;i++)
        {
            System.out.println(parent[i] +" - "+ i + "\t" +graph[i][parent[i]]);
        }
    }

    //Utility Method to construct MST
    private void createMSTWithPrim(int[][] graph) {

        // Initialise all keys as INFINITE and included as false
        for (int i = 0; i < V; i++) {
            Arrays.fill(key, Integer.MAX_VALUE);
            Arrays.fill(included, Boolean.FALSE);
        }

        // Mark the key value of the starting node as 0
        key[0] = 0;

        for (int i = 0; i < V - 1; i++) {
            // Pick the minimum vertex and add it to the mstSet
            int minimumWeightVertex = minKey(key, included);
            included[minimumWeightVertex] = Boolean.TRUE;

            // Update only those adjacent vertices of the minimum vertex, which are not included in the mstSet
            // And having current key value less than the previous one
            for (int j = 0; j < V; j++) {
                if (graph[minimumWeightVertex][j] != 0 && !included[j] && graph[minimumWeightVertex][j] < key[j]) {
                    parent[j] = minimumWeightVertex;
                    key[j] = graph[minimumWeightVertex][j];
                }
            }
        }
    }

}
