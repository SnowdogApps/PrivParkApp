package pl.snowdog.privpark.data_source;

import java.util.ArrayList;
import java.util.List;

import pl.snowdog.privpark.model.ParkingCurrentData;

public class ParkingCurrentDataRepository {

    private List<ParkingCurrentData> mParkingCurrentDatas;

    public ParkingCurrentDataRepository() {
        mParkingCurrentDatas = new ArrayList<>();
        mParkingCurrentDatas.add(new ParkingCurrentData("Poplar", "12:00 - 18:00", "2.0 £ /1h", 51.514043, -0.012855));
        mParkingCurrentDatas.add(new ParkingCurrentData("East Rd.", "10:00 - 17:00", "2.5 £ /1h", 51.529494, -0.087048));
        mParkingCurrentDatas.add(new ParkingCurrentData("Warneford St,", "10:00 - 17:00", "2.5 £ /1h", 51.537058, -0.055350));
    }

    public List<ParkingCurrentData> getParkingCurrentDatas() {
        return mParkingCurrentDatas;
    }
}
