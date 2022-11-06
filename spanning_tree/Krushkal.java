package spanning_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Edge implements Comparable<Edge> {
    public int source;
    public int destination;
    public int weight;

    public Edge(int u, int v, int w) {
        this.source = u;
        this.destination = v;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }

}

/*
* It is a greedy algorithm.
* Time Complexity of Krushkal's Algorithm
* O(ElogE) is definitely O(ElogV) because E <= V^2 (fully connected graph)
* ElogE <= Elog(V^2) = 2ElogV = O(ElogV)
* */

public class Krushkal {
    static int minimumCostOfSpanningTree = 0;

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0, 28, 0, 0, 0, 10, 0}, {28, 0, 16, 0, 0, 0, 14}, {0, 16, 0, 12, 0, 0, 0}, {0, 0, 12, 0, 22, 0, 18}, {0, 0, 0, 22, 0, 25, 24}, {10, 0, 0, 0, 25, 0, 0}, {0, 14, 0, 18, 24, 0, 0}};
        List<Edge> listOfEdges = new ArrayList<>();
        int[] belongs;
        List<Edge> spanningTreeList = new ArrayList<>();

        // Creation of Edge collection
        // Loop is started from 1 so as to skip the bidirectional values
        for (int i = 1; i < graph.length; i++)
            for (int j = 0; j < i; j++) {
                if (graph[i][j] != 0) {
                    Edge tempEdge = new Edge(i, j, graph[i][j]);
                    listOfEdges.add(tempEdge);
                }
            }

        // Sort the edges collection
        Collections.sort(listOfEdges);

        // Initialisation of belongs array for the disjoint sets
        belongs = new int[graph.length];
        for (int i = 0; i < belongs.length; i++)
            belongs[i] = i;

        // Check if the vertices in a edge is having different parents
        // If different parents, unite them and mark them in a same set
        // If same parents, then skip it ,else it'll make a loop
        listOfEdges.stream().forEach(
                edge -> {
                    int parentTo = find(belongs,edge.source);
                    int parentFrom= find(belongs,edge.destination);

                    if(parentTo != parentFrom)
                    {
                        union(belongs,parentTo,parentFrom);
                        spanningTreeList.add(edge);
                    }
                }
        );

        // Calculation of minimum cost of the spanning Tree
        spanningTreeList.stream().forEach(
                edge -> {
                    System.out.println("Source : "+ edge.source + " ,Destination : "+ edge.destination + " ,Weight "+ edge.weight);
                    minimumCostOfSpanningTree += edge.weight;
                }
        );

        System.out.println("Minimum Cost of Spanning Tree : "+minimumCostOfSpanningTree);

    }

    // Union method for the combination of two vertices
    public static void union(int[] array, int vertex1, int vertex2) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (array[i] == vertex2)
                array[i] = vertex1;
        }
    }

    // Find method to find the parent of a vertex
    public static int find(int[] array, int vertex) {
        return array[vertex];
    }
}
