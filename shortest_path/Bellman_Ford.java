package shortest_path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Node {
    int to;
    int from;

    public Node(int u, int v) {
        this.to = u;
        this.from = v;
    }

    @Override
    public String toString() {
        return "Node{" +
                "to=" + to +
                ", from=" + from +
                '}';
    }
}

/*
 * Single source shortes path
 * It is a dynamic programming approach
 * It's time complexity is O(V * E)
 *
 * Limitation : It works on negative edge weights but it doesn't work on negative edge weight cycles
 * */

public class Bellman_Ford {

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0, 6, 5, 5, MAX_VALUE, MAX_VALUE, MAX_VALUE},
                {MAX_VALUE, 0, MAX_VALUE, MAX_VALUE, -1, MAX_VALUE, MAX_VALUE},
                {MAX_VALUE, -2, 0, MAX_VALUE, 1, MAX_VALUE, MAX_VALUE},
                {MAX_VALUE, MAX_VALUE, -2, 0, MAX_VALUE, -1, MAX_VALUE},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 0, MAX_VALUE, 3},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 0, 3},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 0}};

        Bellman_Ford.bellmanFord(graph, 0);

    }

    public static void bellmanFord(int[][] graph, int source) {

        // Dist array will hold the minimum distance of all vertices from source
        // Parent array to keep track of parent of each node
        // visited checks if all the vertices are included
        int[] dist = new int[graph.length];
        int[] parent = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        boolean cycleFound = Boolean.FALSE;

        //Initialise all the distance as INFINITE and the visited array as false
        Arrays.fill(dist, MAX_VALUE);
        Arrays.fill(visited, Boolean.FALSE);
        Arrays.fill(parent, -1);

        // Generation of edge list
        List<Node> edgeList = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] != 0 && graph[i][j] != MAX_VALUE)
                    edgeList.add(new Node(i, j));
            }
        }

        // Mark the distance of first node as 0
        dist[source] = 0;

        //Relaxation of vertices should be done n-1 times
        for (int i = 0; i < graph.length - 1; i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                int to = edgeList.get(j).to;
                int from = edgeList.get(j).from;

                if (dist[to] + graph[to][from] < dist[from]) {
                    dist[from] = dist[to] + graph[to][from];
                    parent[from] = to;
                }
            }
        }

        // To make sure if there is any negative edge weight cycle
        for (int i = 0; i < edgeList.size(); i++) {
            int to = edgeList.get(i).to;
            int from = edgeList.get(i).from;
            if (dist[to] + graph[to][from] < dist[from]) {
                cycleFound = Boolean.TRUE;
                break;
            }
        }

        // If cycle is found, then this problem can't be solved with bellman ford
        // Else print the expected graph.
        if (cycleFound)
            System.out.println("The graph is having negative edge weight cycle");
        else {
            System.out.println("Vertex\t\tCost\t\tParent");
            for (int i = 0; i < dist.length; i++) {
                System.out.println(i + "\t\t" + dist[i] + "\t\t" + parent[i]);
            }
        }
    }
}
