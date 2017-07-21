package group14.wheresmystuff.controller;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import group14.wheresmystuff.model.Item;


import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Model;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    MapView mapView;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Where's My Stuff? - " + Model.getActiveUser().getName());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                goToPage(LoginActivity.class);

            }

        });

        Button addItem = (Button) findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                goToPage(SubmitItemActivity.class);

            }

        });

        Button showItems = (Button) findViewById(R.id.viewItemsButton);
        showItems.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                goToPage(DisplayItemsActivity.class);

            }

        });
    }

    public Marker addMarker(LatLng latlng, String title, Bitmap icon) {
        MarkerOptions newMarker = new MarkerOptions();
        newMarker.position(latlng).title(title);
        return map.addMarker(newMarker);
    }

    public LatLng convertAddress(String address) {
        Geocoder geoCoder = new Geocoder(this);
        if (address != null && !address.isEmpty()) {
            try {
                List<Address> addressList = geoCoder.getFromLocationName(address, 1);
                if (addressList != null && addressList.size() > 0) {
                    double lat = addressList.get(0).getLatitude();
                    double lng = addressList.get(0).getLongitude();
                    return new LatLng(lat, lng);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        int i = 0;
        for (Item item : Model.getItemList()) {
            LatLng location = convertAddress(item.getLocation());
            Marker itemMarker = addMarker(location, item.getName(), item.getIcon());
            itemMarker.setTag(item);
            if (getIntent().getExtras() != null && i == getIntent().getExtras().getInt("ItemIndex")) {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14));
            }
            i++;
        }
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Bundle bundle = new Bundle();
                int p = 0;
                for (Item item : Model.getItemList()) {
//                    if (item.equals(marker.getTag())) {
                    if (marker.getTag().toString().equals(item.toString())) {
                        bundle.putInt("itemIndex", p);
                        goToPage(ItemActivity.class, bundle);
                    }
                    p++;
                }
            }
        });
    }

    public void mynotify(String title, String content) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notifyicon)
                        .setContentTitle(title)
                        .setContentText(content);
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final int NOTIFICATION_ID = 0;
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }

    public void goToPage(Class next, Bundle bundle) {
        Intent intent = new Intent(this, next);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
