package com.example.android.campusseguro.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.campusseguro.Banco.DBHandler;
import com.example.android.campusseguro.Fragments.Configuracao;
import com.example.android.campusseguro.Fragments.Estatistica;
import com.example.android.campusseguro.Fragments.Mapa;
import com.example.android.campusseguro.Fragments.MapsFragment;
import com.example.android.campusseguro.Model.RegistrosAssaltos;
import com.example.android.campusseguro.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    SupportMapFragment sMapFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHandler dbHandler = new DBHandler(this);
        List<RegistrosAssaltos> listaRegistrosAssaltos = new ArrayList<RegistrosAssaltos>();
        if(dbHandler.getAllRegistros().size() == 0) {
            RegistrosAssaltos registrosAssaltos0 = new RegistrosAssaltos(1, 1, -5.836302, -35.209755, "2017-11-09 20:00", "Assalto");
            RegistrosAssaltos registrosAssaltos1 = new RegistrosAssaltos(2, 1, -5.8333001, -35.2058291, "2017-11-08 20:00", "Assalto");
            RegistrosAssaltos registrosAssaltos2 = new RegistrosAssaltos(3, 1, -5.8331747, -35.2046221, "2017-11-07 20:00", "Assalto");
            RegistrosAssaltos registrosAssaltos3 = new RegistrosAssaltos(4, 1, -5.833637, -35.204341, "2017-11-06 21:00", "Assalto");
            RegistrosAssaltos registrosAssaltos4 = new RegistrosAssaltos(5, 1, -5.833712, -35.203774, "2017-11-07 22:00", "Assalto");
            RegistrosAssaltos registrosAssaltos5 = new RegistrosAssaltos(6, 1, -5.833012, -35.205624, "2017-11-08 08:00", "Assalto");
            RegistrosAssaltos registrosAssaltos6 = new RegistrosAssaltos(7, 1, -5.835029, -35.204257, "2017-11-06 02:00", "Assalto");
            RegistrosAssaltos registrosAssaltos7 = new RegistrosAssaltos(8, 1, -5.833486, -35.205708, "2017-11-05 14:00", "Assalto");
            RegistrosAssaltos registrosAssaltos8 = new RegistrosAssaltos(9, 1, -5.834216, -35.205584, "2017-11-08 15:00", "Assalto");
            RegistrosAssaltos registrosAssaltos9 = new RegistrosAssaltos(10, 1, -5.833742, -35.202867, "2017-11-08 16:00", "Assalto");
            dbHandler.addRegistro(registrosAssaltos0);
            dbHandler.addRegistro(registrosAssaltos1);
            dbHandler.addRegistro(registrosAssaltos2);
            dbHandler.addRegistro(registrosAssaltos3);
            dbHandler.addRegistro(registrosAssaltos4);
            dbHandler.addRegistro(registrosAssaltos5);
            dbHandler.addRegistro(registrosAssaltos6);
            dbHandler.addRegistro(registrosAssaltos7);
            dbHandler.addRegistro(registrosAssaltos8);
            dbHandler.addRegistro(registrosAssaltos9);
        }

        sMapFragment = SupportMapFragment.newInstance();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // FragmentManager fragmentManager = getFragmentManager();
        //  fragmentManager.beginTransaction().replace(R.id.Fragment, new Configuracao()).commit();

        sMapFragment.getMapAsync(this);


        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            android.support.v4.app.FragmentManager sFm = getSupportFragmentManager();

            int intentFragment = getIntent().getExtras().getInt("fragment");

            switch (intentFragment) {
                case 1:
                    sFm.beginTransaction().replace(R.id.Fragment, sMapFragment).commit();
                    break;
                case 2:
                    Estatistica estatistica = new Estatistica();

                    sFm.beginTransaction().replace(R.id.Fragment, estatistica).commit();
                    break;
                case 3:
                    Configuracao configuracao = new Configuracao();
                    sFm.beginTransaction().replace(R.id.Fragment, configuracao).commit();
                    break;
            }
        }
    }
    //TODO Ajustar o onResume()+


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       // FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager sFm = getSupportFragmentManager();


        int id = item.getItemId();

        if (sMapFragment.isAdded()){
            sFm.beginTransaction().hide(sMapFragment).commit();
        }



        Intent intent = null;
        if (id == R.id.nav_mapa) {
            if (!sMapFragment.isAdded())
                sFm.beginTransaction().add(R.id.Fragment, sMapFragment).commit();
            else
                sFm.beginTransaction().show(sMapFragment).commit();

            // Handle the camera action
        } else if (id == R.id.nav_estatisticas) {
            Estatistica estatistica = new Estatistica();

            sFm.beginTransaction().replace(R.id.Fragment, estatistica).commit();

        } else if (id == R.id.nav_configuracao) {


            Configuracao configuracao = new Configuracao();
            sFm.beginTransaction().replace(R.id.Fragment, configuracao).commit();
        } else if (id == R.id.nav_login){
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivity(intent2);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        DBHandler dbHandler = new DBHandler(this);
        List<RegistrosAssaltos> listRegistros = dbHandler.getAllRegistros();
        List<MarkerOptions> listMarker = new ArrayList<MarkerOptions>();


        for(RegistrosAssaltos registrosAssaltos : listRegistros){
            listMarker.add(new MarkerOptions().position(new LatLng(registrosAssaltos.getLat(), registrosAssaltos.getLng())).icon(iconePadrao(R.mipmap.ic_thief))
                    .title(registrosAssaltos.getDescricao()));


        }

        for(MarkerOptions markerOptions: listMarker){
            mMap.addMarker(markerOptions);
        }


        LatLng UFRN = new LatLng(-5.836302, -35.209755);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UFRN));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));
        mMap.addMarker(new MarkerOptions()
                .position(UFRN).icon(iconePadrao(R.mipmap.ic_thief))
                .title("Hello world"));

        this.mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                return true;
            }
        });
    }


    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
    public BitmapDescriptor iconePadrao(int icone){
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(icone);
        final Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);  //277 / 4222
        BitmapDescriptor iconePadrao = BitmapDescriptorFactory.fromBitmap(smallMarker);
        return iconePadrao;

    }


}
