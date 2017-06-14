package pl.snowdog.privpark.data_source;

import java.util.ArrayList;
import java.util.List;

import pl.snowdog.privpark.data_source.model.ParkingSpot;

public class ParkingSpotsRepository {

    private List<ParkingSpot> mParkingSpots;

    public ParkingSpotsRepository() {
        mParkingSpots = new ArrayList<>();
        mParkingSpots.add(new ParkingSpot("Poplar", 51.514043, -0.012855));
        mParkingSpots.add(new ParkingSpot("East Rd.", 51.529494, -0.087048));
        mParkingSpots.add(new ParkingSpot("Warneford St.", 51.537058, -0.055350));
    }

    public ParkingSpot getParkingSpot(int position) {
        return mParkingSpots.get(position);
    }
}
