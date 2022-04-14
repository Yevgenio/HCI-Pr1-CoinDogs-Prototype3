package boukingolts.yevgeni.prototype3;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Sandbox extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button option1;
    private Button option2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandbox);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        ArrayList<LatLng> checkpoints = new ArrayList<LatLng>();

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(checkpoints.get(1), 5f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.264121, 34.789687), 16f));

        option1 = (Button) findViewById(R.id.option1);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption1();
            }
        });

        option2 = (Button) findViewById(R.id.option2);
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOption2();
            }
        });
    }

    public void openOption1() {
        LatLng target = mMap.getCameraPosition().target;
        //checkpoints.add(target);
        mMap.addMarker(new MarkerOptions().position(target).title("Checkpoint"));
    }

    public void openOption2() {
        Intent intent = new Intent(this, Navigator.class);
        startActivity(intent);
    }
}