package pl.snowdog.privpark.data_source.model;

import com.google.android.gms.maps.model.LatLng;

public class ParkingCurrentData {
    private String mAvailableHours;
    private String mPrice;
    private ParkingSpot mParkingSpot;

    public ParkingCurrentData(ParkingSpot parkingSpot, String availableHours, String price) {
        mAvailableHours = availableHours;
        mPrice = price;
        mParkingSpot = parkingSpot;
    }

    public String getName() {
        return mParkingSpot.getName();
    }

    public LatLng getLatLng() {
        return mParkingSpot.getLatLng();
    }

    public String getSnippet() {
        return mAvailableHours + " for " + mPrice;
    }
}
