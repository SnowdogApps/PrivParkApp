package pl.snowdog.privpark.data_source;

import java.util.ArrayList;
import java.util.List;

import pl.snowdog.privpark.model.ParkingAction;

public class ParkingHistoryDataSource {
    private List<ParkingAction> mParkingActions;

    public ParkingHistoryDataSource() {
        mParkingActions = new ArrayList<>();
        mParkingActions.add(new ParkingAction());
        mParkingActions.add(new ParkingAction());
        mParkingActions.add(new ParkingAction());
    }

    public List<ParkingAction> getParkingActions() {
        return mParkingActions;
    }
}
