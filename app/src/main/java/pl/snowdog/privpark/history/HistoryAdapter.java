package pl.snowdog.privpark.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.snowdog.privpark.R;
import pl.snowdog.privpark.data_source.model.ParkingAction;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<ParkingAction> mParkingActions = new ArrayList<>();

    public void setParkingActions(List<ParkingAction> parkingActions) {
        mParkingActions = parkingActions;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setParkingAction(mParkingActions.get(position));
    }

    @Override
    public int getItemCount() {
        return mParkingActions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.history_name)
        TextView mHistoryName;
        @BindView(R.id.history_time)
        TextView mHistoryTime;
        @BindView(R.id.history_cost)
        TextView mHistoryCost;

        private ParkingAction mParkingAction;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(ViewHolder.this, itemView);
        }

        void setParkingAction(ParkingAction parkingAction) {
            mParkingAction = parkingAction;
            mHistoryName.setText(mParkingAction.getName());
            mHistoryTime.setText(mParkingAction.getTime());
            mHistoryCost.setText(mParkingAction.getCost());
        }
    }
}
