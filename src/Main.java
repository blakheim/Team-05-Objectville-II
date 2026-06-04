import io.MapReader;
import model.Grid;
import simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar ObjectVilleGame.jar <map-file> <ticks>");
            return;
        }
        String mapPath = args[0];
        int ticks;

        try {
            ticks = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Tick count must be a number");
            return;
        }

        if (ticks <= 0) {
            System.out.println("Tick count must be positive");
            return;
        }
        try {
            Grid grid = MapReader.read(mapPath);
            SimulationEngine engine = new SimulationEngine(grid);
            engine.run(ticks);
        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}