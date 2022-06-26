package com.example.final_exam.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.final_exam.Entry.EntryManager;
import com.example.final_exam.Locale.BaseActivity;
import com.example.final_exam.R;

import java.util.List;
import java.util.UUID;

import info.hoang8f.android.segmented.SegmentedGroup;

public class CreateEntry extends BaseActivity implements LocationListener  {
    private EntryManager manager;
    private Button submit;
    private EditText amka, name, phone;
    private RadioButton ill, recovered, deceased;
    private SegmentedGroup group;
    private int selection;
    static final int REQ_LOC_CODE = 23;
    private boolean GPSPerm = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);
        manager = new EntryManager();
        amka = findViewById(R.id.amka);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        submit = findViewById(R.id.submit);
        ill = findViewById(R.id.ill);
        recovered = findViewById(R.id.recovered);
        deceased = findViewById(R.id.dead);
        group = findViewById(R.id.radioGroup2);
        group.check(R.id.ill);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GpsPermissionCheck();
                if (GPSPerm) {
                    CreateEntry();
                }
                else{
                    Toast.makeText(CreateEntry.this,getString(R.string.no_gps),Toast.LENGTH_LONG).show();
                }

            }});
    }
    public void CreateEntry()
    {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, (LocationListener) this);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);

        if (location == null) {
            Toast.makeText(getApplicationContext(), "GPS signal not found", Toast.LENGTH_SHORT).show();
        }
        if (location != null) {
            if (amka.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || name.getText().toString().isEmpty() || group.getCheckedRadioButtonId() == -1) {
                new AlertDialog.Builder(CreateEntry.this).setTitle(getString(R.string.insuff_data)).setMessage(getString(R.string.empty_fields)).show();
            }else{
                if(ill.isChecked()){
                    selection=1;
                }else if(recovered.isChecked()){
                    selection=0;
                }else{
                    selection=2;
                }
                UUID id = UUID.randomUUID();
                String uuid = id.toString();
                manager.CreateEntry(uuid,name.getText().toString(),amka.getText().toString(),phone.getText().toString(),selection,System.currentTimeMillis(), (float) location.getLatitude(), (float) location.getLongitude());
                Toast.makeText(CreateEntry.this,getString(R.string.entry_created),Toast.LENGTH_LONG).show();
            }
        }
    }

    public void GpsPermissionCheck()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_LOC_CODE);
        } else {
            GPSPerm = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_LOC_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            GPSPerm = true;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}