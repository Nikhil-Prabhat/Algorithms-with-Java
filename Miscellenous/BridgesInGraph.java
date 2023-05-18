import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
*  A bridge in a graph is an edge between two vertices which if removed, can result in multiple components in the graph
*  dfsTimeInsertion : DFS insertion time while traversing
*  lowestTimeInsertion : Minimum lowest time insertion of all adjacent nodes apart from parent
* */
public class BridgesInGraph {

    private static int timer = 0;

    public static void main(String[] args) {

        int vertex = 5;
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for(int i=0;i<vertex;i++)
            adjacencyList.add(new ArrayList<>());

        addEdge(adjacencyList, 1, 0);
        addEdge(adjacencyList, 0, 2);
        addEdge(adjacencyList, 2, 1);
        addEdge(adjacencyList, 0, 3);
        addEdge(adjacencyList, 3, 4);

        boolean[] visited = new boolean[vertex];
        int[] dfsTimeInsertion = new int[vertex];
        int[] lowestTimeInsertion = new int[vertex];
        List<List<Integer>> bridges = new ArrayList<>();
        DFS(0, -1, visited, adjacencyList, dfsTimeInsertion, lowestTimeInsertion, bridges);
        System.out.println(bridges);
    }

    private static void addEdge(List<List<Integer> > adjacencyList, int u, int v)
    {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    private static void DFS(int node, int parent, boolean[] visited, List<List<Integer>> adjacencyList, int[] dfsTimeInsertion, int[] lowestTimeInsertion, List<List<Integer>> bridges) {
        visited[node] = Boolean.TRUE;
        dfsTimeInsertion[node] = lowestTimeInsertion[node] = timer++;

        for(Integer childNode : adjacencyList.get(node)) {
            if(childNode == parent)
                continue;

            if(!visited[childNode]) {
                DFS(childNode, node, visited, adjacencyList, dfsTimeInsertion, lowestTimeInsertion, bridges);
                lowestTimeInsertion[node] = Math.min(lowestTimeInsertion[node], lowestTimeInsertion[childNode]);

                if(lowestTimeInsertion[childNode] > lowestTimeInsertion[node])
                    bridges.add(Arrays.asList(childNode, node));
            }
            else {
                lowestTimeInsertion[node] = Math.min(lowestTimeInsertion[node], lowestTimeInsertion[childNode]);
            }
        }
    }


}
