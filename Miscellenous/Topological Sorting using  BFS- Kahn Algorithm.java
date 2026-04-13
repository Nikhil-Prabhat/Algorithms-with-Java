public List<Integer> kahnTopologicalSort(int V, List<List<Integer>> adj) {
    int[] indegree = new int[V];

    // Step 1: Compute indegree of each node
    for (int i = 0; i < V; i++) {
        for (int neighbor : adj.get(i)) {
            indegree[neighbor]++;
        }
    }

    // Step 2: Add all nodes with indegree 0 to queue
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < V; i++) {
        if (indegree[i] == 0) {
            queue.add(i);
        }
    }

    // Step 3: Process nodes
    List<Integer> topoOrder = new ArrayList<>();

    while (!queue.isEmpty()) {
        int node = queue.poll();
        topoOrder.add(node);

        for (int neighbor : adj.get(node)) {
            indegree[neighbor]--;

            if (indegree[neighbor] == 0) {
                queue.add(neighbor);
            }
        }
    }

    // Step 4: Check for cycle
    if (topoOrder.size() != V) {
        return new ArrayList<>(); // cycle exists
    }

    return topoOrder;
}
