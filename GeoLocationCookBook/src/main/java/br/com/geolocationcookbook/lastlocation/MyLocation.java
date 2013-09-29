package br.com.geolocationcookbook.lastlocation;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import br.com.geolocationcookbook.R;

/**
 * Created by Fabricio on 23/09/13.
 */
public class MyLocation extends Activity {

    LocationManager mLocationManager;
    TextView mTextView;

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

        mTextView.setText("Last location: \nlat = " +
                lLocation.getLatitude() +
                "\nlong = " +
                lLocation.getLongitude());

    }
}
