package controller.network;

import model.Cell;
import model.Grid;
import model.utility.UtilityProvider;
import model.zone.Zone;
import java.util.*;

public class InfrastructureNetworkManager {
    private final Grid grid;

    public InfrastructureNetworkManager(Grid grid) {
        this.grid = grid;
    }

    public List<Zone> getConnectedConsumers(UtilityProvider provider) {
        List<Zone> connectedConsumers = new ArrayList<>();
        Queue<Cell> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        visited.add(provider);

        List<Cell> initialNeighbors = getNeighbors(provider.getRow(), provider.getCol());
        for (Cell neighbor : initialNeighbors) {
            if (neighbor.isPassable()) {
                queue.add(neighbor);
                visited.add(neighbor);
            }
        }

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current instanceof Zone) {
                connectedConsumers.add((Zone) current);
            }
            List<Cell> adjacentCells = getNeighbors(current.getRow(), current.getCol());
            for (Cell adj : adjacentCells) {
                if (adj.isPassable() && !visited.contains(adj)) {
                    queue.add(adj);
                    visited.add(adj);
                }
            }
        }

        return connectedConsumers;
    }

    private List<Cell> getNeighbors(int row, int col) {
        List<Cell> neighbors = new ArrayList<>();
        int[][] directions = {
                {-1, 0},  {1, 0},  {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            Cell cell = grid.getCell(newRow, newCol);
            if (cell != null) {
                neighbors.add(cell);
            }
        }
        return neighbors;
    }
}