package com.example.intentcje;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivityER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button ButNameER = (Button)findViewById(R.id.ButNameER);
        ButNameER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Name = new Intent(MainActivityER.this, SecondActivityER.class);
                startActivity(Name);
            }
        });
        Button ButAdresER = (Button)findViewById(R.id.ButAdresER);
        TextInputLayout UrlER = (TextInputLayout)findViewById(R.id.UrlER);
        ButAdresER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Adres = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(UrlER)));
                startActivity(Adres);
            }
        });
        Button ButGeoER = (Button)findViewById(R.id.ButGeoER);
        ButGeoER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Name = new Intent(MainActivityER.this, ThirdActivityER.class);
                startActivity(Name);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}