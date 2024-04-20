import java.util.Arrays;
import java.util.PriorityQueue;

import CITS2200.Graph;
import CITS2200.Path;

public class PathImp implements Path {

  /**
   * Computes the total weight of the minimum spanning tree of a graph.
   * 
   * @param g the graph to compute the minimum spanning tree for.
   * @return the total weight of the minimum spanning tree, or -1 if the graph is
   *         empty or not connected
   */
  public int getMinSpanningTree(Graph g) {
    int numVertices = g.getNumberOfVertices();
    int[][] matrix = g.getEdgeMatrix();
    boolean[] visited = new boolean[numVertices];
    int[] parentList = new int[numVertices];
    int[] keyList = new int[numVertices];
    int totalWeight = 0;

    Arrays.fill(keyList, Integer.MAX_VALUE);

    keyList[0] = 0;
    parentList[0] = -1;

    if (numVertices == 0) {
      return -1;
    }

    // Prim's algorithm
    for (int i = 0; i < numVertices - 1; i++) {

      int minIndex = -1;
      int minValue = Integer.MAX_VALUE;

      // Find the unvisited vertex with the smallest key value
      for (int j = 0; j < numVertices; j++) {
        if (!visited[j] && keyList[j] < minValue) {

          minIndex = j;
          minValue = keyList[j];
        }
      }

      visited[minIndex] = true;

      // Update key values for adjacent vertices
      for (int j = 0; j < numVertices; j++) {
        if (matrix[minIndex][j] != 0 && !visited[j] && matrix[minIndex][j] < keyList[j]) {

          parentList[j] = minIndex;
          keyList[j] = matrix[minIndex][j];
        }
      }
    }

    // Compute total weight of the minimum spanning tree
    for (int i = 1; i < numVertices; i++) {
      if (parentList[i] != -1) {
        int weight = g.getWeight(i, parentList[i]);

        if (weight == 0) {
          return -1;
        }

        totalWeight += weight;
      }
    }

    return totalWeight;
  }

  /**
   * Computes the shortest path from a source vertex to all other vertices in a
   * graph using Dijkstra's algorithm.
   * 
   * @param graph  the graph to compute shortest paths for
   * @param source the index of the source vertex
   * @return an array containing the distance to each vertex from the source
   *         vertex, or -1 if the vertex is unreachable
   */
  public int[] getShortestPaths(Graph graph, int source) {
    int numVertices = graph.getNumberOfVertices();
    int[] distance = new int[numVertices];
    boolean[] visited = new boolean[numVertices];
    Arrays.fill(distance, Integer.MAX_VALUE);

    PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> distance[a] - distance[b]);

    queue.offer(source);
    distance[source] = 0;

    // Dijkstra's algorithm
    while (!queue.isEmpty()) {
      int vertex = queue.poll();

      if (visited[vertex]) {
        continue;
      }
      visited[vertex] = true;

      // Update distances for adjacent vertices
      for (int i = 0; i < numVertices; i++) {
        int weight = graph.getEdgeMatrix()[vertex][i];

        if (weight > 0) {
          int newDistance = distance[vertex] + weight;

          if (newDistance < distance[i]) {
            distance[i] = newDistance;
            queue.offer(i);
          }
        }
      }
    }

    // Convert unreachable vertices to -1
    for (int i = 0; i < numVertices; i++) {
      if (distance[i] == Integer.MAX_VALUE) {
        distance[i] = -1;
      }
    }

    return distance;
  }

}
