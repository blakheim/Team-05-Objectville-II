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
                        zone.coverService(serviceBuilding.getServiceType());
                        OutputPrinter.printServiceReceived(zone, serviceBuilding.getServiceType());
                    }
                }
            }
        }

        private boolean isWithinRadius(ServiceBuilding serviceBuilding, Cell cell) {
            int distance = Math.abs(serviceBuilding.getRow() - cell.getRow())
                    + Math.abs(serviceBuilding.getCol() - cell.getCol());

            return distance <= serviceBuilding.getRadius();
        }
    }
