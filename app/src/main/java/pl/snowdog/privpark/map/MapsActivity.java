package pl.snowdog.privpark.map;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.annimon.stream.Stream;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.snowdog.privpark.history.HistoryActivity;
import pl.snowdog.privpark.R;
import pl.snowdog.privpark.data_source.ParkingCurrentDataRepository;
import pl.snowdog.privpark.model.ParkingCurrentData;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    private GoogleMap mMap;
    private ParkingCurrentDataRepository mParkingCurrentDataRepository = new ParkingCurrentDataRepository();
    private final LatLng london = new LatLng(51.509865, -0.118092);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Stream.of(mParkingCurrentDataRepository.getParkingCurrentDatas()).forEach(new com.annimon.stream.function.Consumer<ParkingCurrentData>() {
            @Override
            public void accept(ParkingCurrentData parkingCurrentData) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .position(parkingCurrentData.getLatLng())
                        .title(parkingCurrentData.getName())
                        .snippet(parkingCurrentData.getSnippet()));
            }
        });
        mMap.setMinZoomPreference(12.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(london));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.history: {
                startActivity(HistoryActivity.getCallingIntent(this));
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.open_menu)
    public void onMenuOpenClick(View view) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
}
