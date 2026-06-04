package simulation;

import io.OutputPrinter;
import model.Cell;
import model.Grid;
import model.zone.Zone;

public class SimulationEngine {

    private Grid grid;
    private CityResources cityResources;
    private ServiceDistributor serviceDistributor;
    private UtilityDistributor utilityDistributor;

    public SimulationEngine(Grid grid) {
        this.grid = grid;
        this.cityResources = new CityResources();
        this.serviceDistributor = new ServiceDistributor();
        this.utilityDistributor = new UtilityDistributor(grid);
    }

    public void run(int ticks) {
        for (int tick = 1; tick <= ticks; tick++) {
            resetCells();
            OutputPrinter.printTickHeader(tick);

            serviceDistributor.distributeServices(grid);
            utilityDistributor.distributeUtilities();

            cityResources.distributeResources(grid);
            updateZones();
            cityResources.accumulateProduction(grid);
        }
    }

    private void resetCells() {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {

                Cell cell = grid.getCell(r, c);

                if (cell != null) {
                    cell.resetTick();
                }
            }
        }

    }

    private void updateZones() {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int oldLevel = zone.getLevel();
                    zone.update();
                    OutputPrinter.printGenerated(zone, zone.getOutput(), zone.getResourceType());
                    OutputPrinter.printLevelChange(zone, oldLevel, zone.getLevel());
                }
            }
        }
    }
}