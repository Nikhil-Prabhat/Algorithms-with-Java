import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphColoring {

    private static List<int[]> colorsList = new ArrayList<>();
    private static int VERTEX = 4;

    public static void main(String[] args) {
        int graphMatrix[][] = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };
        int noOfColors = 3;

        int[] colors = new int[VERTEX];
        Arrays.fill(colors, 0);

        GraphColoring.colorGraph(graphMatrix, noOfColors, colors, 0);
        colorsList.stream().forEach(
                arr -> System.out.println(Arrays.toString(arr))
        );
    }

    private static void colorGraph(int[][] graphMatrix, int noOfColors, int[] colors, int currentVertex) {

        // Bounding Condition : If all vertices are assigned a color, then return
        // Since single array is being used to track the colors, that's why the colorsList is assigned the copy of colors
        if (currentVertex == VERTEX) {
            colorsList.add(Arrays.copyOf(colors, colors.length));
            return;
        }

        // Try all possible colors for the VERTEX
        for (int i = 1; i <= noOfColors; i++) {

            // Check if it is safe to color the vertex with the given color
            if (isSafeToColor(currentVertex, graphMatrix, colors, i)) {
                colors[currentVertex] = i;

                // Check for other vertices
                GraphColoring.colorGraph(graphMatrix, noOfColors, colors, currentVertex + 1);

                // If the currentVertex doesn't lead to solution
                colors[currentVertex] = 0;
            }
        }
    }

    private static boolean isSafeToColor(int currentVertex, int[][] graphMatrix, int[] colors, int currentColor) {
        // Check for each edge that the connecting vertex shouldn't be having the same color as the currentColors
        for (int i = 0; i < VERTEX; i++)
            if (graphMatrix[currentVertex][i] == 1 && colors[i] == currentColor)
                return false;
        return true;
    }
}
