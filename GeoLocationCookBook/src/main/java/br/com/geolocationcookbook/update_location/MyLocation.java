package br.com.geolocationcookbook.update_location;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import br.com.geolocationcookbook.R;

/**
 * Created by Fabricio on 23/09/13.
 */
public class MyLocation extends Activity implements LocationListener {

    LocationManager mLocationManager;
    TextView mTextView;
    Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.tv1);

        mLocationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        Criteria lCriteria = new Criteria();
        lCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        lCriteria.setPowerRequirement(Criteria.POWER_LOW);

        String lLocationProvider = mLocationManager.getBestProvider(lCriteria, true);

        Location lLocation = mLocationManager.getLastKnownLocation(lLocationProvider);

        mLocationManager.requestLocationUpdates(lLocationProvider, 5000, (float) 2.0, this);
    }

    @Override
    public void onLocationChanged(Location pLocation) {
         mLocation = pLocation;
        showUpdate();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle pBundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void showUpdate()
    {
        mTextView.setText("Last location: \nlat = " +
                mLocation.getLatitude() +
                "\nlong = " +
                mLocation.getLongitude());
    }
}
