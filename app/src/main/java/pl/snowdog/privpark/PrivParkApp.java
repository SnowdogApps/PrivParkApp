package pl.snowdog.privpark;

import android.app.Application;

import com.google.firebase.iid.FirebaseInstanceId;

import pl.snowdog.privpark.data_source.ParkingSpotsRepository;
import timber.log.Timber;

public class PrivParkApp extends Application {
    private ParkingSpotsRepository mParkingSpotsRepository;
    private RemoteDatabaseController mRemoteDatabaseController;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        String token = FirebaseInstanceId.getInstance().getToken();
        Timber.i("Token: " + token);
        mParkingSpotsRepository = new ParkingSpotsRepository();
        mRemoteDatabaseController = new RemoteDatabaseController();
    }

    public ParkingSpotsRepository getParkingSpotsRepository() {
        return mParkingSpotsRepository;
    }

    public RemoteDatabaseController getRemoteDatabaseController() {
        return mRemoteDatabaseController;
    }
}
