package simulation;

import io.OutputPrinter;
import model.Cell;
import model.Constants;
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

    public void distributeResources(Grid grid) {
        List<Industrial> industrialZones = getIndustrialZones(grid);
        List<Commercial> commercialZones = getCommercialZones(grid);
        List<Housing> housingZones = getHousingZones(grid);

        int workZoneCount = industrialZones.size() + commercialZones.size();

        long populationShare = 0;
        if (workZoneCount > 0) {
            populationShare = populationPool / workZoneCount;
        }

        long goodsShare = 0;
        if (!commercialZones.isEmpty()) {
            goodsShare = goodsPool / commercialZones.size();
        }

        long lifestyleShare = 0;
        if (!housingZones.isEmpty()) {
            lifestyleShare = lifestylePool / housingZones.size();
        }

        for (Industrial industrial : industrialZones) {
            if (populationShare > 0) {
                industrial.receiveResource(Constants.POPULATION, populationShare);
                OutputPrinter.printResourceReceived(industrial, populationShare, Constants.POPULATION);
            }
        }

        for (Commercial commercial : commercialZones) {
            if (populationShare > 0) {
                commercial.receiveResource(Constants.POPULATION, populationShare);
                OutputPrinter.printResourceReceived(commercial, populationShare, Constants.POPULATION);
            }

            if (goodsShare > 0) {
                commercial.receiveResource(Constants.GOODS, goodsShare);
                OutputPrinter.printResourceReceived(commercial, goodsShare, Constants.GOODS);
            }
        }

        for (Housing housing : housingZones) {
            if (lifestyleShare > 0) {
                housing.receiveResource(Constants.LIFESTYLE, lifestyleShare);
                OutputPrinter.printResourceReceived(housing, lifestyleShare, Constants.LIFESTYLE);
            }
        }
        populationPool = 0;
        goodsPool = 0;
        lifestylePool = 0;
    }

    public void accumulateProduction(Grid grid) {
        populationPool = 0;
        goodsPool = 0;
        lifestylePool = 0;

        for (Housing housing : getHousingZones(grid)) {
            populationPool += housing.getOutput();
        }
        for (Industrial industrial : getIndustrialZones(grid)) {
            goodsPool += industrial.getOutput();
        }

        for (Commercial commercial : getCommercialZones(grid)) {
            lifestylePool += commercial.getOutput();
        }
    }

    private List<Housing> getHousingZones(Grid grid){
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

    private List<Industrial> getIndustrialZones(Grid grid){
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
        } return commercialZones;
    }
}