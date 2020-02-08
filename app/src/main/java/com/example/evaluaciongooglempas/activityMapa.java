package com.example.evaluaciongooglempas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;


public class activityMapa extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        ImageView imgPais= (ImageView)findViewById(R.id.imgFotoPais);
        TextView txtPais= (TextView)findViewById(R.id.txtNombrePais);
        txtPais.setText(getIntent().getStringExtra("nombre_pais"));
        Glide.with( this.getApplicationContext()).load(getIntent().getStringExtra("url_imagen")).into(imgPais);
    }


    GoogleMap mapa;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        double [] lstCoord = getIntent().getDoubleArrayExtra("listaCooordenadas");


        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(lstCoord[4],lstCoord[5]), 5);
        mapa.moveCamera(camUpd1);

        PolylineOptions lineas = new PolylineOptions()
                .add(new LatLng(lstCoord[2], lstCoord[0])) //NOR OESTE
                .add(new LatLng(lstCoord[2], lstCoord[1])) //NOR ESTE
                .add(new LatLng(lstCoord[3], lstCoord[1]))  //SUR ESTE
                .add(new LatLng(lstCoord[3], lstCoord[0]))  // SUR OESTE
                .add(new LatLng(lstCoord[2], lstCoord[0]));  // NOR OESTE
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);

    }

}
