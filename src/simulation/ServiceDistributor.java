package simulation;

import io.OutputPrinter;
import model.Cell;
import model.Grid;
import model.service.ServiceBuilding;
import model.zone.Zone;

    public class ServiceDistributor {

        public void distributeServices(Grid grid) {
            for (int r = 0; r < grid.getRows(); r++) {
                for (int c = 0; c < grid.getCols(); c++) {
                    Cell cell = grid.getCell(r, c);

                    if (cell instanceof ServiceBuilding) {
                        distributeServiceFrom(grid, (ServiceBuilding) cell);
                    }
                }
            }
        }

        private void distributeServiceFrom(Grid grid, ServiceBuilding serviceBuilding) {
            for (int r = 0; r < grid.getRows(); r++) {
                for (int c = 0; c < grid.getCols(); c++) {
                    Cell cell = grid.getCell(r, c);

                    if (cell instanceof Zone && isWithinRadius(serviceBuilding, cell)) {
                        Zone zone = (Zone) cell;
                        String serviceType = serviceBuilding.getServiceType();

                        if (zone.needsService(serviceType)) {
                            zone.coverService(serviceType);
                            OutputPrinter.printServiceReceived(zone, serviceType);
                        }
                    }
                }
            }
        }

        private boolean isWithinRadius(ServiceBuilding serviceBuilding, Cell cell) {
            int rowDifference = serviceBuilding.getRow() - cell.getRow();
            int colDifference = serviceBuilding.getCol() - cell.getCol();
            int radius = serviceBuilding.getRadius();
            return rowDifference * rowDifference + colDifference * colDifference <= radius * radius;
        }
    }
