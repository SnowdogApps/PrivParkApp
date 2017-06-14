package pl.snowdog.privpark.data_source;

import java.util.ArrayList;
import java.util.List;

import pl.snowdog.privpark.data_source.model.ParkingAction;

public class ParkingHistoryDataSource {
    private ParkingSpotsRepository mParkingSpotsRepository;
    private List<ParkingAction> mParkingActions;

    public ParkingHistoryDataSource(ParkingSpotsRepository parkingSpotsRepository) {
        mParkingSpotsRepository = parkingSpotsRepository;
        mParkingActions = new ArrayList<>();
        mParkingActions.add(new ParkingAction(mParkingSpotsRepository.getParkingSpot(2), "2.3 £", "yesteday, 12:12 - 14:23"));
        mParkingActions.add(new ParkingAction(mParkingSpotsRepository.getParkingSpot(1), "3.1 £", "12th May, 12:12 - 14:23"));
    }

    public List<ParkingAction> getParkingActions() {
        return mParkingActions;
    }
}
