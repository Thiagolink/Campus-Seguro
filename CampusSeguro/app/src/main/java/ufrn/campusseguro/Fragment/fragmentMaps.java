package ufrn.campusseguro.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ufrn.campusseguro.Banco.DBHandler;
import ufrn.campusseguro.Model.RegistrosAssaltos;
import ufrn.campusseguro.R;
import ufrn.campusseguro.Utils.Icones;

import com.androidmapsextensions.GoogleMap;
import com.androidmapsextensions.OnMapReadyCallback;
import com.androidmapsextensions.SupportMapFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.androidmapsextensions.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;

/**
 * Created by Wesley Reuel on 05/12/2017.
 */

public class fragmentMaps extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    public static final String ARG_PAGE = "ARG_PAGE";
    View view;
    public GoogleMap mMap;
    private SupportMapFragment mapFragment;
    public LatLngBounds mCurrentCameraBounds;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    LatLng UFRN = new LatLng(-5.836822, -35.203805);

    public static fragmentMaps newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragmentMaps fragment = new fragmentMaps();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maps, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createMapFragmentIfNeeded();
        setUpMapIfNeeded();


        //LatLng CGE = new LatLng(-7.226751, -35.879893);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CGE, 16.0f));

    }

    private void createMapFragmentIfNeeded() {
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = createMapFragment();
            FragmentTransaction tx = fm.beginTransaction();
            tx.add(R.id.map, mapFragment);
            tx.commit();
        }
    }

    protected SupportMapFragment createMapFragment() {
        return SupportMapFragment.newInstance();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {

            mapFragment.getExtendedMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    setUpMap(googleMap);
                }
            });
        }
    }

    public void setUpMap(GoogleMap googleMap) {
        if(mMap != null){
            return ;
        }

        mMap = googleMap;
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                checkLocationPermission();
            }
        } else {
            //TODO:     // Mostrar tratamento de que não aceitou Permissão e pedir para aceitar
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        final fragmentMaps frgmaps = this;


        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mCurrentCameraBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                float zoom = mMap.getCameraPosition().zoom;
            }
        });


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UFRN,  17.0f));
        DBHandler dbHandler = new DBHandler(this.getContext());
        List<RegistrosAssaltos> listRegistros = dbHandler.getAllRegistros();
        List<MarkerOptions> listMarker = new ArrayList<MarkerOptions>();


        for(RegistrosAssaltos registrosAssaltos : listRegistros){
            listMarker.add(new MarkerOptions().position(new LatLng(registrosAssaltos.getLat(), registrosAssaltos.getLng())).icon(Icones.iconePadrao(R.mipmap.ic_thief, this.getContext()))
                    .title(registrosAssaltos.getDescricao()));


        }

        for(MarkerOptions markerOptions: listMarker){
            mMap.addMarker(markerOptions);
        }


        LatLng UFRN = new LatLng(-5.836302, -35.209755);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UFRN));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));
        mMap.addMarker(new MarkerOptions()
                .position(UFRN).icon(Icones.iconePadrao(R.mipmap.ic_thief, this.getContext()))
                .title("Hello world"));

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1999;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(com.google.android.gms.location.LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
