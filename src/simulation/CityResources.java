package simulation;

import model.Cell;
import model.Grid;
import model.zone.Commercial;
import model.zone.Housing;
import model.zone.Industrial;
import java.util.ArrayList;
import java.util.List;

public class CityResources {

    private long populationPool;
    private long goodsPool;
    private long lifestylePool;

    public long getPopulationPool() {
        return populationPool;
    }

    public long getGoodsPool() {
        return goodsPool;
    }

    public long getLifestylePool() {
        return lifestylePool;
    }

    private List<Housing> getHousingZones(Grid grid) {
        List<Housing> housingZones = new ArrayList<>();

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);
                if (cell instanceof Housing) {
                    housingZones.add((Housing) cell);
                }
            }
        }

        return housingZones;
    }

    private List<Industrial> getIndustrialZones(Grid grid) {
        List<Industrial> industrialZones = new ArrayList<>();

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);
                if (cell instanceof Industrial) {
                    industrialZones.add((Industrial) cell);
                }
            }
        }

        return industrialZones;
    }

    private List<Commercial> getCommercialZones(Grid grid) {
        List<Commercial> commercialZones = new ArrayList<>();

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);
                if (cell instanceof Commercial) {
                    commercialZones.add((Commercial) cell);
                }
            }
        }

        return commercialZones;
    }
}