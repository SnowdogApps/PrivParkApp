package pl.snowdog.privpark.data_source;

import java.util.ArrayList;
import java.util.List;

import pl.snowdog.privpark.data_source.model.ParkingCurrentData;

public class ParkingCurrentDataRepository {
    private ParkingSpotsRepository mParkingSpotsRepository;
    private List<ParkingCurrentData> mParkingCurrentDatas;

    public ParkingCurrentDataRepository(ParkingSpotsRepository parkingSpotsRepository) {
        mParkingSpotsRepository = parkingSpotsRepository;
        mParkingCurrentDatas = new ArrayList<>();
        mParkingCurrentDatas.add(new ParkingCurrentData(mParkingSpotsRepository.getParkingSpot(0), "12:00 - 18:00", "2.0 £ /1h"));
        mParkingCurrentDatas.add(new ParkingCurrentData(mParkingSpotsRepository.getParkingSpot(1), "10:00 - 17:00", "2.5 £ /1h"));
        mParkingCurrentDatas.add(new ParkingCurrentData(mParkingSpotsRepository.getParkingSpot(2), "10:00 - 17:00", "2.5 £ /1h"));
    }

    public List<ParkingCurrentData> getParkingCurrentDatas() {
        return mParkingCurrentDatas;
    }
}
