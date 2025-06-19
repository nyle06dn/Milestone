// Grid.java
// Description: Manages the 2D grid of terrain and fire objects.
public class Grid {
    private GridObject[][] grid;
    private int width, height;
    private static Weather weather = new Weather();

    // Provides global access to the weather settings (wind and humidity)
    public static Weather getWeather() { return weather; }

    // Initializes the grid with specified width, height, and terrain ratios
    public void init(int w, int h, double treeRatio, double flowerRatio, double waterRatio, double rainbowRatio) {
        this.width = w;
        this.height = h;
        grid = new GridObject[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                double r = Math.random();
                if (r < treeRatio) grid[x][y] = new Tree();
                else if (r < treeRatio + flowerRatio) grid[x][y] = new Flower();
                else if (r < treeRatio + flowerRatio + waterRatio) grid[x][y] = new Water();
                else grid[x][y] = new Rainbow();
            }
        }
    }

    // Returns the object at the given grid coordinates if in bounds
    public GridObject get(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    // Sets the object at the given grid coordinates if in bounds
    public void set(int x, int y, GridObject obj) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = obj;
        }
    }

    // Performs a simulation tick by calling step() on a copy of the grid
    public void tick() {
        GridObject[][] copy = new GridObject[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                copy[x][y] = grid[x][y];
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                copy[x][y].step(this); // Call step() on each object to update its state
            }
        }
    }

    // Returns the width of the grid
    public int getWidth() { return width; }

    // Returns the height of the grid
    public int getHeight() { return height; }
}
