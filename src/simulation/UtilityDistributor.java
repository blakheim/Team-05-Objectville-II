package simulation;

import controller.network.InfrastructureNetworkManager;
import io.OutputPrinter;
import model.Cell;
import model.Grid;
import model.utility.UtilityProvider;
import model.zone.Zone;
import java.util.List;

public class UtilityDistributor {
    private Grid grid;
    private InfrastructureNetworkManager networkManager;

    public UtilityDistributor(Grid grid) {
        this.grid = grid;
        this.networkManager = new InfrastructureNetworkManager(grid);
    }

    public void distributeUtilities() {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);
                if (cell instanceof UtilityProvider) {
                    distributeFromProvider((UtilityProvider) cell);
                }
            }
        }
    }

    private void distributeFromProvider(UtilityProvider provider) {
        long remainingCapacity = provider.getCapacity();
        String utilityType = provider.getUtilityType();
        List<Zone> connectedZones = networkManager.getConnectedConsumers(provider);

        for (Zone zone : connectedZones) {
            if (remainingCapacity <= 0) {
                return;
            }

            long accepted = zone.receiveUtility(utilityType, remainingCapacity);
            if (accepted > 0) {
                remainingCapacity -= accepted;
                OutputPrinter.printUtilityReceived(zone, accepted, utilityType);
            }
        }
    }
}