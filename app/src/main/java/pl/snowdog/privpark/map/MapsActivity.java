package pl.snowdog.privpark.map;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.annimon.stream.Stream;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.snowdog.privpark.BaseActivity;
import pl.snowdog.privpark.history.HistoryActivity;
import pl.snowdog.privpark.R;
import pl.snowdog.privpark.data_source.ParkingCurrentDataRepository;
import pl.snowdog.privpark.data_source.model.ParkingCurrentData;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    private GoogleMap mMap;
    private ParkingCurrentDataRepository mParkingCurrentDataRepository;
    private final LatLng london = new LatLng(51.509865, -0.118092);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mParkingCurrentDataRepository = new ParkingCurrentDataRepository(getParkingSpotsRepository());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mNavigationView.setNavigationItemSelectedListener(this);
        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("show_dialog", false)) {
            showDialog();
        }
//        showChargeDialog();
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Want to park there?")
                .setMessage("Available until 17:00, costs 2 £ per hour")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getRemoteConfigController().setSelectedState();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                showChargeDialog();
                            }
                        }, 3000);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getRemoteConfigController().setSpotAvailable();
                    }
                })
                .show();
    }


    private void showChargeDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Parking finished")
                .setMessage("Thank you for using PrivPark.\n You parked 2 hrs 21 minutes. Total cost: 5.12 £ ")
                .setPositiveButton(android.R.string.ok, null)
                .show();
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
        mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .position(london)
                        .title("You are here"));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                getRemoteConfigController().showNotification(MapsActivity.this, "ABC 123");
                return false;
            }
        });
        mMap.setMinZoomPreference(12.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(london));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
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
