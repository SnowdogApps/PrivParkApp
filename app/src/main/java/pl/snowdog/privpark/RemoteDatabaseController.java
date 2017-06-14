package pl.snowdog.privpark;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.snowdog.privpark.map.MapsActivity;
import timber.log.Timber;

public class RemoteDatabaseController {
    public RemoteDatabaseController(final Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("car_plate");

//        myRef.setValue("Hello, World!");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                showNotification(context, value);
                Timber.i("Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Timber.w("Failed to read value.", error.toException());
            }
        });
    }

    private void showNotification(Context context, String value) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra("show_dialog", true);
        PendingIntent pi = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification n  = new Notification.Builder(context)
                .setContentTitle("Parking spot available for: " + value)
                .setContentText("Do you want to park there?")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Cancel", pi)
                .addAction(R.mipmap.ic_launcher, "Accept", pi).build();


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
