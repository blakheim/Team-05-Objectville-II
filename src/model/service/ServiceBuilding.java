package model.service;

import model.Cell;

public abstract class ServiceBuilding extends Cell {
    protected int radius;
    protected String serviceType;

    public ServiceBuilding(int row, int col, int radius, String serviceType) {
        super(row, col);
        this.radius = radius;
        this.serviceType = serviceType;
    }

    public int getRadius() {
        return radius;
    }

    public String getServiceType() {
        return serviceType;
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
