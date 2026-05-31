package io;



import model.Cell;

import model.Grid;

import model.zone.Commercial;

import model.zone.Housing;

import model.zone.Industrial;

import model.zone.Zone;



public class OutputPrinter {



    public static void printTickHeader(int tick) {

        System.out.println("Tick " + tick);

    }



    public static void printServiceReceived(Zone zone, String serviceType) {

        System.out.println(zonePrefix(zone) + " received " + serviceType + " service");

    }



    public static void printUtilityReceived(Zone zone, long amount, String utilityType) {

        System.out.println(zonePrefix(zone) + " received " + amount + " " + utilityType);

    }



    public static void printResourceReceived(Zone zone, long amount, String resourceType) {

        System.out.println(zonePrefix(zone) + " received " + amount + " " + resourceType);

    }



    public static void printGenerated(Zone zone, long amount, String resourceType) {

        System.out.println(zonePrefix(zone) + " generated " + amount + " " + resourceType);

    }



    public static void printLevelChange(Zone zone, int oldLevel, int newLevel) {

        if (newLevel > oldLevel) {

            System.out.println(zonePrefix(zone) + " levels up from " + oldLevel + " to " + newLevel);

        } else if (newLevel < oldLevel) {

            System.out.println(zonePrefix(zone) + " levels down from " + oldLevel + " to " + newLevel);

        }

    }



    public static void printGrid(Grid grid) {

        for (int r = 0; r < grid.getRows(); r++) {

            StringBuilder line = new StringBuilder();

            for (int c = 0; c < grid.getCols(); c++) {

                Cell cell = grid.getCell(r, c);

                if (cell != null) {

                    line.append(cell.getSymbol());

                }

            }

            System.out.println(line);

        }

    }



    private static String zonePrefix(Zone zone) {

        return getZoneDisplayName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ")";

    }



    private static String getZoneDisplayName(Zone zone) {

        if (zone instanceof Housing) {

            return "House";

        } else if (zone instanceof Industrial) {

            return "Industrial";

        } else if (zone instanceof Commercial) {

            return "Commercial";

        }

        return "Zone";

    }
}
