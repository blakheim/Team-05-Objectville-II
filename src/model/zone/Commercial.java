package model.zone;

public class Commercial extends Zone {

    public Commercial(int row, int col) { super(row, col); }

    @Override public char   getSymbol()       { return 'C'; }
    @Override public String getResourceType() { return "lifestyle"; }

    @Override
    protected long minRequiredUtility() {
        return Math.min(deliveredElectricity, Math.min(deliveredWater, deliveredInternet));
    }

    @Override
    protected boolean hasLostAllUtilities() {
        return deliveredElectricity == 0 && deliveredWater == 0 && deliveredInternet == 0;
    }

    @Override
    protected boolean hasRequiredServices() {
        return coveredSecurity;
    }

    @Override
    protected boolean hasBaseResources() {
        return receivedPopulation > 0 && receivedGoods > 0;
    }

    @Override
    protected boolean hasRequiredResources() {
        return receivedPopulation > 0 && receivedGoods > 0;
    }

    @Override
    protected long computeLevelOutput() {
        long m = minRequiredUtility();
        if (level == 1) return m;
        if (level == 2) return 2 * m;
        if (level == 3) return 2 * m + Math.min(receivedPopulation, receivedGoods);
        return 0;
    }

    @Override
    public boolean needsService(String serviceType) {
        return serviceType.equals("security");
    }

    @Override
    public boolean needsUtility(String utilityType) {
        return utilityType.equals("electricity")
                || utilityType.equals("water")
                || utilityType.equals("internet");
    }
}