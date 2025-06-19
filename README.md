# Milestone
Forest Fire Fun Simulator - How to Run
This program simulates a forest fire spreading across a grid of terrain using Java and a GUI interface.
------------------------
How to Compile & Run:
------------------------
1. Place all .java files in the same folder.
2. Open terminal in that folder.
3. Compile:  javac *.java
4. Run:      java Main
------------------------
User Inputs at Startup:
------------------------
You will be prompted to enter:
- Grid width (e.g., 50)
- Grid height (e.g., 50)
- Tick speed in milliseconds (e.g., 200)
- Ratios for Tree, Flower, Water, Rainbow
  (must add up to 1.0, e.g., 0.4,0.2,0.2,0.2)
------------------------
Simulation Controls:
- Start Auto  → Starts the simulation at the given tick speed.
- Stop Auto   → Pauses the simulation.
- Tick Step   → Advances one simulation step manually.
- Click any burnable cell (tree or flower) to ignite a fire.
------------------------
Notes:
- Input is validated to prevent crashes
- Wind and humidity influence fire behavior internally
Thank you!
