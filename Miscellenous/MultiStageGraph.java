package Miscellenous;

import java.util.Arrays;

/*

A Multistage graph is a directed, weighted graph in which the nodes can be divided into a set of stages such that all edges are from a stage to next stage only
(In other words there is no edge between vertices of same stage and from a vertex of current stage to previous stage).

Now there are various strategies we can apply :-

The Brute force method of finding all possible paths between Source and Destination and then finding the minimum.
That’s the WORST possible strategy.

Dijkstra’s Algorithm of Single Source shortest paths. This method will find shortest paths from source to all other nodes which is not required in this case.
So it will take a lot of time and it doesn’t even use the SPECIAL feature that this MULTI-STAGE graph has.

Simple Greedy Method – At each node, choose the shortest outgoing path. If we apply this approach to the example graph given above we get the solution as 1 + 4 + 18 = 23.
But a quick look at the graph will show much shorter paths available than 23. So the greedy method fails !

The best option is Dynamic Programming. So we need to find Optimal Sub-structure, Recursive Equations and Overlapping Sub-problems.

Time complexity : O(n^2)

 */

public class MultiStageGraph {

    private static final int ZERO = 0;

    public static void main(String[] args) {
        // Graph stored in the form of an
        // adjacency Matrix
        int[][] graph = new int[][]{
                {ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO},
                {ZERO, ZERO, 1, 2, 5, ZERO, ZERO, ZERO, ZERO},
                {ZERO, ZERO, ZERO, ZERO, ZERO, 4, 11, ZERO, ZERO},
                {ZERO, ZERO, ZERO, ZERO, ZERO, 9, 5, 16, ZERO},
                {ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, 2, ZERO},
                {ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, 18},
                {ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, 13},
                {ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, 2}
        };

        // Total no of vertices is 8 so the length of array or matrix is 9, first is considered to be zero
        int n = 8;
        int stages = 4;

        int[] cost = new int[9];
        int[] dest = new int[9];
        int[] path = new int[stages + 1];


        Arrays.fill(cost, 0);
        Arrays.fill(dest, -1);
        Arrays.fill(path, -1);

        // Outer loop is to iterate the vertex from sink to source
        // It will start from the second last vertex
        for (int i = n - 1; i >= 1; i--) {
            int min = Integer.MAX_VALUE;

            // Inner loop is to find the minimum path from the current vertex to sink
            for (int k = i + 1; k <= n; k++) {

                if (graph[i][k] != ZERO && graph[i][k] + cost[k] < min) {
                    min = graph[i][k] + cost[k];
                    dest[i] = k;
                }
            }
            cost[i] = min;
        }

        path[1] = 1;
        path[stages] = n;

        for (int i = 2; i <= stages; i++) {
            path[i] = dest[path[i - 1]];
        }

        System.out.println(Arrays.toString(cost));
        System.out.println(Arrays.toString(dest));
        System.out.println(Arrays.toString(path));

    }
}
