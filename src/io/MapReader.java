package io;

import model.Cell;
import model.EmptyCell;
import model.Grid;
import model.RoadCell;
import model.service.Hospital;
import model.service.PoliceStation;
import model.service.School;
import model.utility.InternetHub;
import model.utility.PowerPlant;
import model.utility.WaterStation;
import model.zone.Commercial;
import model.zone.Housing;
import model.zone.Industrial;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    public static Grid read(String path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read map file: " + path, e);
        }

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Map file is empty");
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        if (cols == 0) {
            throw new IllegalArgumentException("Map file has an empty first row");
        }

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).length() != cols) {
                throw new IllegalArgumentException("Inconsistent row length at row " + i);
            }
        }

        Grid grid = new Grid(rows, cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char symbol = lines.get(r).charAt(c);
                grid.setCell(r, c, createCell(symbol, r, c));
            }
        }
        return grid;
    }

    private static Cell createCell(char symbol, int row, int col) {
        switch (symbol) {
            case 'H': return new Housing(row, col);
            case 'I': return new Industrial(row, col);
            case 'C': return new Commercial(row, col);
            case 'P': return new PowerPlant(row, col);
            case 'W': return new WaterStation(row, col);
            case 'T': return new InternetHub(row, col);
            case 'F': return new PoliceStation(row, col);
            case 'D': return new Hospital(row, col);
            case 'S': return new School(row, col);
            case 'R': return new RoadCell(row, col);
            case 'E': return new EmptyCell(row, col);
            default:
                throw new IllegalArgumentException(
                        "Invalid character '" + symbol + "' at row " + row + ", col " + col);
        }
    }
}