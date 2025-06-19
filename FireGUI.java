// FireGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Description: GUI panel that displays and updates the simulation.
public class FireGUI extends JPanel {
    private Grid grid;
    private Timer timer;

    // Initializes the simulation and prompts the user for settings
    public void start() {
        // Prompt user for grid dimensions and tick delay
        String widthStr = JOptionPane.showInputDialog("Enter grid width:");
        String heightStr = JOptionPane.showInputDialog("Enter grid height:");
        String tickStr = JOptionPane.showInputDialog("Enter tick speed in milliseconds (e.g., 200):");

        int width = Integer.parseInt(widthStr);
        int height = Integer.parseInt(heightStr);
        int tickDelay = Integer.parseInt(tickStr);

        // Prompt user for terrain ratios (tree, flower, water, rainbow)
        double[] ratios = getUserRatios();

        // Initialize grid with given parameters
        grid = new Grid();
        grid.init(width, height, ratios[0], ratios[1], ratios[2], ratios[3]);

        // Set up automatic ticking timer
        timer = new Timer(tickDelay, e -> {
            grid.tick();
            repaint();
        });

        // Add button to start auto simulation
        JButton startButton = new JButton("Start Auto");
        startButton.addActionListener(e -> timer.start());
        this.add(startButton);

        // Add button to stop auto simulation
        JButton stopButton = new JButton("Stop Auto");
        stopButton.addActionListener(e -> timer.stop());
        this.add(stopButton);

        // Add button to step through one tick manually
        JButton stepButton = new JButton("Tick Step");
        stepButton.addActionListener(e -> {
            grid.tick();
            repaint();
        });
        this.add(stepButton);

        // Allow mouse click to ignite a fire on burnable terrain
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                click(e.getX(), e.getY());
            }
        });
    }

    // Prompts the user for terrain ratios and validates them
    private double[] getUserRatios() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null,
                        "Enter ratios for Tree, Flower, Water, Rainbow (must sum to 1.0),\nseparated by commas (e.g. 0.4,0.2,0.2,0.2):",
                        "Enter Ratios", JOptionPane.PLAIN_MESSAGE);
                if (input == null) System.exit(0);
                String[] parts = input.split(",");
                if (parts.length != 4) throw new Exception();
                double[] ratios = new double[4];
                double sum = 0.0;
                for (int i = 0; i < 4; i++) {
                    ratios[i] = Double.parseDouble(parts[i].trim());
                    sum += ratios[i];
                }
                if (Math.abs(sum - 1.0) > 0.01) throw new Exception();
                return ratios;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter 4 decimal values that add up to 1.0.");
            }
        }
    }

    // Renders the grid of terrain and fire objects
    public void draw(Graphics g) {
        int cellSize = 10;
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                GridObject obj = grid.get(x, y);
                g.setColor(obj.getColor());
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
                g.setFont(new Font("SansSerif", Font.PLAIN, 8));
                g.drawString(obj.getSymbol(), x * cellSize + 1, y * cellSize + 9);
            }
        }
    }

    // Repaints the grid display when the panel is refreshed
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Manually refreshes the GUI
    public void refresh() {
        repaint();
    }

    // Handles user clicks to ignite fire in burnable terrain
    public void click(int x, int y) {
        int gridX = x / 10;
        int gridY = y / 10;
        GridObject obj = grid.get(gridX, gridY);
        if (obj != null && obj.isBurnable()) {
            grid.set(gridX, gridY, new Fire());
            repaint();
        }
    }
}

// (rest of unchanged files...)
