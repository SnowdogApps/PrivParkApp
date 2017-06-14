package pl.snowdog.privpark;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpotsDataSource {

    private List<ParkingSpot> mParkingSpots;

    public ParkingSpotsDataSource() {
        mParkingSpots = new ArrayList<>();
        mParkingSpots.add(new ParkingSpot("Poplar", "12:00 - 18:00", "2.0 £ /1h", 51.514043, -0.012855));
        mParkingSpots.add(new ParkingSpot("East Rd.", "10:00 - 17:00", "2.5 £ /1h", 51.529494, -0.087048));
        mParkingSpots.add(new ParkingSpot("Warneford St,", "10:00 - 17:00", "2.5 £ /1h", 51.537058, -0.055350));
    }

    public List<ParkingSpot> getParkingSpots() {
        return mParkingSpots;
    }
}
