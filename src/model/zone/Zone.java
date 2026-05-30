package model.zone;

import model.Cell;
import model.Consumer;
import model.Producer;

public abstract class Zone extends Cell implements Producer, Consumer {

    protected int level;
    protected long output;
    protected long demand;

    protected long deliveredElectricity;
    protected long deliveredWater;
    protected long deliveredInternet;

    protected boolean coveredSecurity;
    protected boolean coveredHealth;
    protected boolean coveredEducation;

    protected long receivedPopulation;
    protected long receivedGoods;
    protected long receivedLifestyle;

    public Zone(int row, int col) {
        super(row, col);
        this.demand = 1;
    }

    @Override public boolean isPassable() { return true; }
    @Override public long getDemand()     { return demand; }
    @Override public long getOutput()     { return output; }

    public int getLevel() { return level; }

    @Override
    public void resetTick() {
        deliveredElectricity = 0;
        deliveredWater       = 0;
        deliveredInternet    = 0;
        coveredSecurity      = false;
        coveredHealth        = false;
        coveredEducation     = false;
        receivedPopulation   = 0;
        receivedGoods        = 0;
        receivedLifestyle    = 0;
    }

    public void deliverUtility(String type, long amount) {
        if      (type.equals("electricity")) deliveredElectricity += amount;
        else if (type.equals("water"))       deliveredWater       += amount;
        else if (type.equals("internet"))    deliveredInternet    += amount;
    }

    public void coverService(String type) {
        if      (type.equals("security"))  coveredSecurity  = true;
        else if (type.equals("health"))    coveredHealth    = true;
        else if (type.equals("education")) coveredEducation = true;
    }

    public void receiveResource(String type, long amount) {
        if      (type.equals("population")) receivedPopulation += amount;
        else if (type.equals("goods"))      receivedGoods      += amount;
        else if (type.equals("lifestyle"))  receivedLifestyle  += amount;
    }

    protected abstract long minRequiredUtility();
    protected abstract boolean hasLostAllUtilities();
    protected abstract boolean hasRequiredServices();
    protected abstract boolean hasRequiredResources();
    protected abstract long computeLevelOutput();

    protected boolean hasBaseResources() { return true; }

    @Override
    public void update() {
        int target = 0;
        if (minRequiredUtility() > 0 && hasBaseResources()) {
            target = 1;
            if (hasRequiredServices()) {
                target = 2;
                if (hasRequiredResources()) target = 3;
            }
        }

        if (hasLostAllUtilities()) {
            level = 0;
        } else {
            if      (level < target) level++;
            else if (level > target) level--;
        }

        output = computeLevelOutput();
        demand = Math.max(output, 1);
    }
}