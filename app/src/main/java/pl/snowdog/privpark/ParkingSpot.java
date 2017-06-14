package pl.snowdog.privpark;

import com.google.android.gms.maps.model.LatLng;

public class ParkingSpot {
    private String mName;
    private String mAvailableHours;
    private String mPrice;
    private LatLng mLatLng;

    public ParkingSpot(String name, String availableHours, String price, double v1, double v2) {
        mName = name;
        mAvailableHours = availableHours;
        mPrice = price;
        mLatLng = new LatLng(v1, v2);
    }

    public String getName() {
        return mName;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public String getSnippet() {
        return mAvailableHours + " for " + mPrice;
    }
}
