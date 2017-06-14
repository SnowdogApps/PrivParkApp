package pl.snowdog.privpark.data_source.model;

public class ParkingAction {
    private ParkingSpot mParkingSpot;
    private String mCost;
    private String mTime;

    public ParkingAction(ParkingSpot parkingSpot, String cost, String time) {
        mParkingSpot = parkingSpot;
        mCost = cost;
        mTime = time;
    }

    public String getName() {
        return mParkingSpot.getName();
    }

    public String getCost() {
        return mCost;
    }

    public String getTime() {
        return mTime;
    }
}
