package traversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/*
 * Time Complexity using Adjacency Matrix - O(n*n)
 * Time Complexity using Adjacency List   - O(V+E)
 * */
public class DFS {

    public static void main(String[] args) {
        int vertices = 5, edges = 4;
        System.out.println("DFS with Adjacency Matrix");
        GraphWithMatrix graphWithMatrix = new GraphWithMatrix(vertices, edges);
        graphWithMatrix.addEdge(0, 1);
        graphWithMatrix.addEdge(0, 2);
        graphWithMatrix.addEdge(0, 3);
        graphWithMatrix.addEdge(0, 4);
        graphWithMatrix.DFS(0);

        System.out.println("\n");

        System.out.println("DFS with Adjacency List");
        GraphWithList graphWithList = new GraphWithList(5);
        graphWithList.addEdge(0,1);
        graphWithList.addEdge(0,2);
        graphWithList.addEdge(0,3);
        graphWithList.addEdge(0,4);
        graphWithList.DFS(0);

    }

    // DFS implementation using adjacency matrix
    static class GraphWithMatrix {
        int vertices, edges;
        int[][] adjacencyMatrix;
        boolean[] visited;

        public GraphWithMatrix(int vertices, int edges) {
            this.vertices = vertices;
            this.edges = edges;

            // Initialising the entire matrix with 0 and the visited array with false
            adjacencyMatrix = new int[vertices][vertices];
            visited = new boolean[vertices];
            for (int row = 0; row < vertices; row++) {
                Arrays.fill(adjacencyMatrix[row], 0);
            }

            Arrays.fill(visited, Boolean.FALSE);
        }

        // Function to add an edge in the graph
        public void addEdge(int to, int from) {
            // Considering a bidirectional edge
            adjacencyMatrix[to][from] = 1;
            adjacencyMatrix[from][to] = 1;
        }

        public void DFS(int startNode) {
            Stack<Integer> stack = new Stack<>();
            stack.push(startNode);
            visited[startNode] = Boolean.TRUE;
            int index = 0;
            System.out.print(startNode);

            while (!stack.isEmpty()) {

                // Peek the top Node and iterate over the entire array to check if there is any connecting edge
                startNode = stack.peek();
                for (index = 0; index < adjacencyMatrix[startNode].length; index++) {
                    if (adjacencyMatrix[startNode][index] == 1 && !visited[index]) {
                        System.out.print("\t" + index);
                        stack.push(index);
                        visited[index] = Boolean.TRUE;
                        break;
                    }

                }

                // If there is  no edge to be traversed, then pop the top of the stack
                if (index == adjacencyMatrix[startNode].length)
                    stack.pop();
            }
        }
    }

    // DFS implementation using Adjacency List
    static class GraphWithList {
        private int V;
        private LinkedList<Integer> adjacencyList[];
        boolean[] visited;

        GraphWithList(int V) {
            this.V = V;
            adjacencyList = new LinkedList[V];
            visited = new boolean[V];

            // Initialise adjacency list and visited array
            for (int i = 0; i < V; i++) {
                adjacencyList[i] = new LinkedList<>();
            }

            Arrays.fill(visited, Boolean.FALSE);
        }

        // Function to add edges on the graph
        public void addEdge(int to, int from) {
            adjacencyList[to].add(from);
        }

        public void DFS(int startNode) {
            Stack<Integer> stack = new Stack<>();
            stack.push(startNode);
            visited[startNode] = Boolean.TRUE;
            int index = 0;
            System.out.print(startNode);

            while (!stack.isEmpty()) {

                // Peek the top Node and iterate over the entire array to check if there is any connecting edge
                startNode = stack.peek();
                for (index = 0; index < adjacencyList[startNode].size(); index++) {
                    int num = adjacencyList[startNode].get(index);
                    if (!visited[num]) {
                        stack.push(num);
                        visited[num] = Boolean.TRUE;
                        System.out.print("\t" + num);
                        break;
                    }
                }

                // If there is  no edge to be traversed, then pop the top of the stack
                if (adjacencyList[startNode].size() == index)
                    stack.pop();
            }

        }


    }

}
