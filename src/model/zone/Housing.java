package model.zone;

public class Housing extends Zone {

    public Housing(int row, int col) { super(row, col); }

    @Override public char   getSymbol()       { return 'H'; }
    @Override public String getResourceType() { return "population"; }

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
        return coveredSecurity && coveredHealth && coveredEducation;
    }

    @Override
    protected boolean hasRequiredResources() {
        return receivedLifestyle > 0;
    }

    @Override
    protected long computeLevelOutput() {
        long m = minRequiredUtility();
        if (level == 1) return m;
        if (level == 2) return 2 * m;
        if (level == 3) return 2 * m + receivedLifestyle;
        return 0;
    }
}