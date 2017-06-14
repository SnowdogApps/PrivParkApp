package pl.snowdog.privpark.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.snowdog.privpark.BaseActivity;
import pl.snowdog.privpark.R;
import pl.snowdog.privpark.data_source.ParkingHistoryDataSource;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.rv_history)
    RecyclerView mRecyclerView;
    private HistoryAdapter mHistoryAdapter;
    private ParkingHistoryDataSource mParkingHistoryDataSource;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HistoryActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("History");
        mParkingHistoryDataSource = new ParkingHistoryDataSource(getParkingSpotsRepository());
        initRecyclerView();
    }

    private void initRecyclerView() {
        mHistoryAdapter = new HistoryAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHistoryAdapter);
        mHistoryAdapter.setParkingActions(mParkingHistoryDataSource.getParkingActions());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
