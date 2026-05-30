package model.zone;

public class Industrial extends Zone {

    public Industrial(int row, int col) { super(row, col); }

    @Override public char   getSymbol()       { return 'I'; }
    @Override public String getResourceType() { return "goods"; }

    @Override
    protected long minRequiredUtility() {
        return Math.min(deliveredElectricity, deliveredWater);
    }

    @Override
    protected boolean hasLostAllUtilities() {
        return deliveredElectricity == 0 && deliveredWater == 0;
    }

    @Override
    protected boolean hasRequiredServices() {
        return coveredSecurity;
    }

    @Override
    protected boolean hasRequiredResources() {
        return receivedPopulation > 0;
    }

    @Override
    protected long computeLevelOutput() {
        long m = minRequiredUtility();
        if (level == 1) return m;
        if (level == 2) return 2 * m;
        if (level == 3) return 2 * m + receivedPopulation;
        return 0;
    }
}