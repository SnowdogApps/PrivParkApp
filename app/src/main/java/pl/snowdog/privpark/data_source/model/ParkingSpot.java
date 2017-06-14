package pl.snowdog.privpark.data_source.model;

import com.google.android.gms.maps.model.LatLng;

public class ParkingSpot {
    private LatLng mLatLng;
    private String mName;

    public ParkingSpot(String name, double v1, double v2) {
        mLatLng = new LatLng(v1, v2);
        mName = name;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public String getName() {
        return mName;
    }
}
