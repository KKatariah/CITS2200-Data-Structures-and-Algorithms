import CITS2200.Graph;
import CITS2200.Search;
import java.util.Arrays;
import java.util.LinkedList;

public class SearchImp implements Search {
    
    /**
    * This method finds the parent vertices of a connected tree in a graph using breadth-first search.
    * It returns an array of parent vertices, where each index i contains the parent vertex of vertex i
    * in the connected tree. If a vertex is not in the connected tree, its parent value is -1.
    * 
    * @param testGraph the graph to search
    * @param startVertex the vertex to start the search from
    * @return an array of parent vertices
    */
    public int[] getConnectedTree(Graph testGraph, int startVertex) {
        LinkedList<Integer> queue = new LinkedList<>();
        int numVertices = testGraph.getNumberOfVertices();
        int[] parentList = new int[numVertices];
        Arrays.fill(parentList, -1);
        boolean[] visited = new boolean[numVertices];

        queue.offer(startVertex);

        for (int i = 0; i < numVertices; i++){
            while (!queue.isEmpty()) {
                int vertex = queue.poll();

                for (int j = 0; j < numVertices; j++) {
                    if (testGraph.getEdgeMatrix()[vertex][j] == 1 && j>= startVertex && !visited[j]) {
                        queue.offer(j);
                        parentList[j] = vertex;
                        visited[j] = true;
                   }
               }
           }
       }
       return parentList;
    }

    /**
    * This method finds the shortest distances from a starting vertex to all other vertices
    * in a graph using breadth-first search. It returns an array of distances, where each index i
    * contains the distance from the starting vertex to vertex i. If a vertex is not reachable from
    * the starting vertex, its distance value is -1.
    * 
    * @param g the graph to search
    * @param startVertex the vertex to start the search from
    * @return an array of distances
    */
    public int[] getDistances(Graph g, int startVertex) {
        int numVertices = g.getNumberOfVertices();
        int[] distanceList = new int[numVertices];
        LinkedList<Integer> queue = new LinkedList<>();
        Arrays.fill(distanceList, -1); 
        boolean[] visited = new boolean[numVertices]; 
        
        distanceList[startVertex] = 0; 

        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            
            for (int j = 0; j < numVertices; j++) {
                if (g.getEdgeMatrix()[vertex][j] == 1) { 
                    int newDistance = distanceList[vertex] + 1; 

                   
                    if (distanceList[j] == -1 || newDistance < distanceList[j] && !visited[j]) {
                        distanceList[j] = newDistance;
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
           }
        }

        return distanceList;
    }
    
    /**
    * Returns a 2D integer array containing the times when each vertex is first discovered (discovery time)
    * and when all its edges have been traversed (finish time) using the breadth-first search algorithm.
    * @param g the Graph to search in
    * @param startVertex the vertex to start the search from
    * @return a 2D integer array where each row represents a vertex and its corresponding discovery and finish time
    */
    public int[][] getTimes(Graph g, int startVertex) {
        int numVertices = g.getNumberOfVertices();
        int[][] timesList = new int[numVertices][2];
        int time = 0;
        int[][] matrix = g.getEdgeMatrix();
        boolean[] visited = new boolean[numVertices];

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        while(!queue.isEmpty()){
            int vertex = queue.getLast();
            timesList[vertex][0] = time++;

            boolean foundNeighbour = false;

            for (int j = 0; j < numVertices; j++) {
                    if (matrix[vertex][j] == 1 && !visited[j]) {
                        queue.add(j);
                        foundNeighbour = true;
                        visited[j] = true;
                    }
                }

            if (!foundNeighbour){
                queue.removeLast();
                timesList[vertex][1] = time++;
            }
           }
           return timesList;
        }

        
    }

        

