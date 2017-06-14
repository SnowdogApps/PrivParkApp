package pl.snowdog.privpark;

import android.support.v7.app.AppCompatActivity;

import pl.snowdog.privpark.data_source.ParkingSpotsRepository;

public class BaseActivity extends AppCompatActivity {

    public ParkingSpotsRepository getParkingSpotsRepository() {
        return ((PrivParkApp) getApplication()).getParkingSpotsRepository();
    }
}
