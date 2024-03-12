/*
* Time Complexity: O(H*W*C(H*W, n))
    H - height
    W - width
    n - building
    C - combination C(n,r)
* 
* Space Complexity: O(H*W)
* 
*/
import java.util.LinkedList;
import java.util.Queue;

public class OptimalBuildingPlacement {
    int[][] grid;

    int height;

    int width;

    public OptimalBuildingPlacement(int width, int height) {
        grid = new int[height][width];
        this.height = height;
        this.width = width;
    }

    private int getMaxDistance() {
        Queue<Integer> queue = new LinkedList<>();

        boolean[][] isVisited = new boolean[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (grid[row][col] == -1) {
                    isVisited[row][col] = true;
                    queue.add(row);
                    queue.add(col);
                }
            }
        }

        int distance = 0;

        int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int index = 0; index < size / 2; index++) {
                int row = queue.poll();
                int col = queue.poll();

                for (int[] dir : dirs) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];

                    if (nr >= 0 && nr < height && nc >= 0 && nc < width && !isVisited[nr][nc]) {
                        queue.add(nr);
                        queue.add(nc);
                        isVisited[nr][nc] = true;
                    }
                }
            }

            distance++;
        }

        return distance - 1;
    }

    public int placeBuildings(int totalBuildings, int startIndex) {
        if (totalBuildings == 0) {
            return getMaxDistance();
        }

        int minDistance = Integer.MAX_VALUE;

        for (int k = startIndex; k < width * height; k++) {
            int row = k / width;
            int col = k % width;

            grid[row][col] = -1;

            int distance = placeBuildings(totalBuildings - 1, k + 1);

            minDistance = Math.min(minDistance, distance);

            grid[row][col] = 0;
        }

        return minDistance;
    }
}