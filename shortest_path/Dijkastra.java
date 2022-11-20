package shortest_path;

import java.util.Arrays;

/*
 * Single source shortes path
 * Time complexity of this implementation - O(V2)
 * Time complexity of dijkastra using adjacency list with binary heap - O(E * logV) i.e E- total no of edges & logV for getting minimum vertex
 * and arranging into heap again
 *
 * Limitation of Dijkastra Algorithm - It doesn't work with negative edge weights, it may or may not give correct results in that case
 * */

public class Dijkastra {

    // Utility Method to find the vertex with minimum distance value from the set of vertices not included in shortest path tree
    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    //Utility Method to print the distance array
    private void printShortestPath(int dist[]) {
        System.out.println("Vertex\t\tDistance ");
        for (int i = 0; i < dist.length; i++)
            System.out.println(i + "\t\t" + dist[i]);
    }

    //Dijkastra's Implementation
    private void dijkastra(int[][] graph, int source) {
        // Dist array will hold the minimum distance of all vertices from source
        // visited checks if all the vertices are included
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        //Initialise all the distance as INFINITE and the visited array as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, Boolean.FALSE);


        //Distance of source vertex from itself is always 0
        dist[source] = 0;

        //Iterate over all vertices and find the shortest path
        for (int i = 0; i < graph.length - 1; i++) {
            // Find the minimum distance vertex not included in the mstSet yet and mark it as visited
            int minDistanceVertex = minDistance(dist, visited);
            visited[minDistanceVertex] = Boolean.TRUE;

            // Relax all the adjacent vertices
            for (int j = 0; j < graph.length; j++) {
                // If the next node is unvisited
                // There should be a direct edge between the two vertices
                // Relaxation condition should be satisified
                if (!visited[j] && graph[minDistanceVertex][j] != 0 && dist[minDistanceVertex] != Integer.MAX_VALUE &&
                        dist[minDistanceVertex] + graph[minDistanceVertex][j] <= dist[j]) {
                    dist[j] = dist[minDistanceVertex] + graph[minDistanceVertex][j];
                }
            }

        }

        printShortestPath(dist);
    }

    public static void main(String[] args) {

        int[][] graph = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        Dijkastra dijkastra = new Dijkastra();
        dijkastra.dijkastra(graph, 0);

    }
}
