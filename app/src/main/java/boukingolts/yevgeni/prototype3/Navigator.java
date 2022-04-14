package boukingolts.yevgeni.prototype3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Navigator extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = Navigator.class.getSimpleName();
    private GoogleMap mMap;
    private Button option1;
    private ArrayList<LatLng> checkpoints = new ArrayList<LatLng>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        TextView timerTextView = (TextView) findViewById(R.id.timer);


        new CountDownTimer(5*60*1000, 1000) {

            int timerColor = Color.YELLOW;

            public void onTick(long millisUntilFinished) {
                timerTextView.setText(((millisUntilFinished / 1000)/60)+":"+(millisUntilFinished / 1000)%60);
                timerTextView.setTextColor(timerColor);
            }

            public void onFinish() {
                if(timerColor == Color.YELLOW)
                    timerColor = Color.GRAY;
                else
                    timerColor = Color.rgb(126, 81, 9);
                start();
            }

        }.start();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        checkpoints.add(new LatLng(31.264121, 34.789687));
        checkpoints.add(new LatLng(31.264949, 34.781327));
        checkpoints.add(new LatLng(31.266306, 34.776156));
        checkpoints.add(new LatLng(31.262170, 34.767712));
        checkpoints.add(new LatLng(31.257032, 34.766815));
        checkpoints.add(new LatLng(31.253894, 34.771798));
        checkpoints.add(new LatLng(31.259161, 34.787530));
        checkpoints.add(new LatLng(31.259038, 34.796923));
        checkpoints.add(new LatLng(31.262230, 34.794012));

        mMap.addMarker(new MarkerOptions().position(checkpoints.get(0)).title("Start"));
        for (int i = 1; i < checkpoints.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(checkpoints.get(i)).title("Checkpoint " + i));
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(checkpoints.get(1), 5f));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(checkpoints.get(0), 18f));

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                openOption1();
            }
        });

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_map));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
    }

    private String getDirectionURL(LatLng origin, LatLng dest, String secret) {
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}" +
                "&destination=${dest.latitude},${dest.longitude}" +
                "&sensor=false" +
                "&mode=driving" +
                "&key=$secret";
    }

    public void openOption1() {
        Intent intent = new Intent(this, RunDetails.class);
        startActivity(intent);
    }
}