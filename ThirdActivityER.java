package com.example.intentcje;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivityER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third_er);
        Button ButStart = (Button)findViewById(R.id.ButStart);
        Button ButStop = (Button)findViewById(R.id.ButStop);
        TextView Szer2 = (TextView)findViewById(R.id.Szer2);
        TextView Dlu2 = (TextView)findViewById(R.id.Dlu2);
        LocationManager LocMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ButStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButStart.setEnabled(true);
                ButStop.setEnabled(false);
            }
        LocationListener listen = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String szerokosc = String.valueOf(location.getLatitude());
                String dlugosc = String.valueOf(location.getLongitude());
                Dlu2.setText(dlugosc);
                Szer2.setText(szerokosc);
                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                            requestLocPerm();

                }

                LocMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) listen);
            }

            private final ActivityResultLauncher<String[]> requestPermissionLauncher =
                    registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permission -> {
                        boolean fineLocationGrant = Boolean.TRUE.equals(permission.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false));
                        boolean courseLocationGrant = Boolean.TRUE.equals(permission.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false));

                    });

            private void requestLocPerm(){
                requestPermissionLauncher.launch(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                });
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }
            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        });

        ButStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButStart.setEnabled(false);
                ButStop.setEnabled(true);
            }
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}