package traversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
* Time Complexity using Adjacency Matrix - O(n*n)
* Time Complexity using Adjacency List   - O(V+E)
* */

public class BFS {

    public static void main(String[] args)
    {
        int vertices = 5, edges = 4;
        System.out.println("BFS with Adjacency Matrix");
        GraphWithMatrix graphWithMatrix = new GraphWithMatrix(vertices,edges);
        graphWithMatrix.addEdge(0, 1);
        graphWithMatrix.addEdge(0, 2);
        graphWithMatrix.addEdge(1, 3);
        graphWithMatrix.BFS(0);

        System.out.println("\n");

        System.out.println("BFS with Adjacency List");
        GraphWithList graphWithList = new GraphWithList(5);
        graphWithList.addEdge(0,1);
        graphWithList.addEdge(0,2);
        graphWithList.addEdge(1,3);
        graphWithList.BFS(0);
    }

    // BFS implementation using adjacency matrix
    static class GraphWithMatrix
    {
        int vertices,edges;
        int[][] adjacencyMatrix;
        boolean[] visited;

        public GraphWithMatrix(int vertices, int edges)
        {
            this.vertices = vertices;
            this.edges = edges;

            // Initialising the entire matrix with 0 and the visited array with false
            adjacencyMatrix = new int[vertices][vertices];
            visited = new boolean[vertices];
            for(int row = 0;row < vertices;row++)
            {
                Arrays.fill(adjacencyMatrix[row],0);
            }

            Arrays.fill(visited,Boolean.FALSE);
        }

        // Function to add an edge in the graph
        public void addEdge(int to, int from)
        {
            // Considering a bidirectional edge
            adjacencyMatrix[to][from] = 1;
            adjacencyMatrix[from][to] = 1;
        }

        void BFS(int start)
        {
            Queue<Integer> queue = new LinkedList<>();

            // Add the starting node in the queue and mark the node as visited
            queue.add(start);
            visited[start] = Boolean.TRUE;
            int currentVertex;

            while(!queue.isEmpty())
            {
                currentVertex = queue.poll();
                System.out.print(currentVertex + " ");

                // Check for the connected vertices which is not visited
                for(int i=0;i<vertices;i++)
                {
                    if(adjacencyMatrix[currentVertex][i] == 1 && !visited[i])
                    {
                        // Push the adjacent node to the queue
                        queue.add(i);
                        visited[i] = Boolean.TRUE;
                    }
                }

            }
        }
    }

    // BFS implementation using Adjacency List
    static class GraphWithList
    {
        private int V;
        private LinkedList<Integer> adjacencyList[];
        boolean[] visited;

        GraphWithList(int V)
        {
            this.V = V;
            adjacencyList = new LinkedList[V];
            visited = new boolean[V];

            // Initialise adjacency list and visited array
            for(int i=0;i<V;i++)
            {
                adjacencyList[i] = new LinkedList<>();
            }

            Arrays.fill(visited,Boolean.FALSE);
        }

        // Function to add edges on the graph
        public void addEdge(int to, int from)
        {
            adjacencyList[to].add(from);
        }

        public void BFS(int start)
        {
            Queue<Integer> queue = new LinkedList<>();
            visited[start] = Boolean.TRUE;
            queue.add(start);
            int currentVertex;

            while(!queue.isEmpty())
            {
                currentVertex = queue.poll();
                System.out.print(currentVertex+ " ");

                // Check for the connected vertices
                adjacencyList[currentVertex].stream()
                        .forEach(
                                (vertex) ->
                                {
                                    queue.add(vertex);
                                    visited[vertex] = Boolean.TRUE;
                                }
                        );
            }
        }
    }
}
