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
    protected boolean hasRequiredResources() {
        return receivedPopulation > 0 && receivedGoods > 0;
    }

    @Override
    protected long computeLevelOutput() {
        long m = minRequiredUtility();
        return switch (level) {
            case 1  -> m;
            case 2  -> 2 * m;
            case 3  -> 2 * m + Math.min(receivedPopulation, receivedGoods);
            default -> 0;
        };
    }
}