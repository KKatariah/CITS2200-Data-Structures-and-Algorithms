public class Imp{
public int getMinimumSpanningTree(Graph g) {
    int numVertices = g.getNumberOfVertices();
    int[][] matrix = g.getmatrix();
    boolean[] visited = new boolean[numVertices];
    int[] parentList = new int[numVertices];
    int[] keyListList = new int[numVertices];
    int totalWeight = 0;

    for (int i = 0; i < numVertices; i++) {
        keyList[i] = Integer.MAX_VALUE;
    }

    keyList[0] = 0;
    parentList[0] = -1;
    

    for (int i = 0; i < numVertices - 1; i++) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        for (int j = 0; j < numVertices; j++) {
            if (!visited[j] && keyList[j] < minValue) {
                minIndex = j;
                minValue = keyList[j];
            }
        }

        if (minIndex == -1) {
            return -1;

        }

        visited[minIndex] = true;

        for (int j = 0; j < numVertices; j++) {
            if (matrix[minIndex][j] != 0 && !visited[j] && matrix[minIndex][j] < keyList[j]) {
                parentList[j] = minIndex;
                keyList[j] = matrix[minIndex][j];
            }
        }

        totalWeight += keyList[minIndex];
    }
    return totalWeight;
}
}
