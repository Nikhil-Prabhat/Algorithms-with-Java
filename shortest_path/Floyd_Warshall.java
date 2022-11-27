package shortest_path;

/*
 * Time Complexity : O(V3)
 * It works with negative cycle as well
 * It is a dynamic programming approach
 * It can be solved by greedy method as well by running dijkastra for each vertex with same time complexity
 * */

public class Floyd_Warshall {

    private static int INF = 9999;

    public static void main(String[] args) {
        int graph[][] = {{0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};
        Floyd_Warshall.floyd_Warshall(graph);

    }

    public static void floyd_Warshall(int[][] dist) {
        // k keeps track of the intermediate vertices
        for (int k = 0; k < dist.length; k++) {
            {
                // Pick each source vertex one by one
                for (int i = 0; i < dist.length; i++) {
                    {
                        // Pick each destination vertex one by one
                        for (int j = 0; j < dist.length; j++) {
                            // If vertex k is on the shortest path
                            // from i to j, then update the value of
                            // dist[i][j]
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        }
                    }
                }
            }
        }

        //Printing the shortest distance matrix
        Floyd_Warshall.print(dist);
    }

    public static void print(int[][] dist) {
        System.out.println("Shortest distance from every pair of vertices");
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
