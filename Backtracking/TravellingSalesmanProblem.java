package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Travel all the cities with minimum distance and return back to the starting city
 * We need to check for the optimized Hamiltonian Cycle
 * Hamiltonian Cycle : Hamiltonian Cycle or Circuit in a graph G is a cycle that visits every vertex of G exactly once and returns to the starting vertex.
 * */
public class TravellingSalesmanProblem {

    public static void main(String[] args) {
        int[][] distanceMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        List<Integer> optimalShortestPath = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        boolean[] visited = new boolean[distanceMatrix.length];
        int[] minimumDistance = {Integer.MAX_VALUE};

        // Mark initial node as the starting point
        currentPath.add(0);
        visited[0] = true;

        findShortestPath(distanceMatrix, currentPath, visited, optimalShortestPath, minimumDistance);
        System.out.println("The shortest path is : " + optimalShortestPath);
        System.out.println("The minimum distance is : " + minimumDistance[0]);
    }

    private static void findShortestPath(int[][] distanceMatrix, List<Integer> currentPath, boolean[] visited, List<Integer> optimalShortestPath, int[] minimumDistance) {

        // Base case : When all the nodes are iterated
        if (currentPath.size() == distanceMatrix.length) {
            int currentDistance = calculatePathDistance(distanceMatrix, currentPath);
            if (currentDistance < minimumDistance[0]) {
                minimumDistance[0] = currentDistance;
                optimalShortestPath.clear();
                optimalShortestPath.addAll(currentPath);
            }

            return;
        }

        for (int vertex = 0; vertex < distanceMatrix.length; vertex++) {
            if (!visited[vertex]) {
                visited[vertex] = true;
                currentPath.add(vertex);
                findShortestPath(distanceMatrix, currentPath, visited, optimalShortestPath, minimumDistance);
                visited[vertex] = false;
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private static int calculatePathDistance(int[][] distanceMatrix, List<Integer> currentPath) {
        int totalDistance = 0;

        for (int i = 0; i < currentPath.size() - 1; i++)
            totalDistance += distanceMatrix[currentPath.get(i)][currentPath.get(i + 1)];
        totalDistance += distanceMatrix[currentPath.get(currentPath.size() - 1)][0];  // The salesman need to return back to the starting city

        return totalDistance;
    }
}
