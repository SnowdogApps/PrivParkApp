package pl.snowdog.privpark;

import android.app.Application;

import pl.snowdog.privpark.data_source.ParkingSpotsRepository;

public class PrivParkApp extends Application {
    private ParkingSpotsRepository mParkingSpotsRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mParkingSpotsRepository = new ParkingSpotsRepository();
    }

    public ParkingSpotsRepository getParkingSpotsRepository() {
        return mParkingSpotsRepository;
    }
}
