package net.prahasiwi.carirumahsakit.activities;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.prahasiwi.carirumahsakit.R;
import net.prahasiwi.carirumahsakit.helper.ServiceClient;
import net.prahasiwi.carirumahsakit.helper.ServiceGenerator;
import net.prahasiwi.carirumahsakit.model.ListRumah;
import net.prahasiwi.carirumahsakit.model.Rumah;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetaBtnActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta_btn);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        ProgressDialog pd;
        pd = new ProgressDialog(PetaBtnActivity.this);
        pd.setMessage("Tunggu Sebentar...");
        pd.show();

        Bundle b = getIntent().getExtras();
        String namaR = b.getString("namaRs");
        Double latRumah = Double.valueOf(String.valueOf(b.get("latitude")));
        Double longRumah = Double.valueOf(String .valueOf(b.get("longitude")));

        pd.dismiss();

        // Add a marker in Sydney and move the camera
        LatLng peta = new LatLng(latRumah, longRumah);
        mMap.addMarker(new MarkerOptions().position(peta).title(namaR));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(peta));
    }
}
