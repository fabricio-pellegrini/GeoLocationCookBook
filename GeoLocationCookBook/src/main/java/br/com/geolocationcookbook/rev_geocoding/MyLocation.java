package br.com.geolocationcookbook.rev_geocoding;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.impl.entity.LaxContentLengthStrategy;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import br.com.geolocationcookbook.R;

/**
 * Created by Fabricio on 23/09/13.
 */
public class MyLocation extends Activity {

    LocationManager mLocationManager;
    Location mLocation;
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
        lCriteria.setPowerRequirement(Criteria.POWER_HIGH);

        String lLocationProvider = mLocationManager.getBestProvider(lCriteria, true);

        Location lLocation = mLocationManager.getLastKnownLocation(lLocationProvider);

        mLocationManager.requestLocationUpdates(lLocationProvider, 5000, 2.0f, new LocationListener() {
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
        });



    }

    private void showUpdate() {
        List<Address> lAddresses;
        try{
            Geocoder lGeocoder = new Geocoder(this);
            lAddresses = lGeocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);

            if(lAddresses != null) {
                Address lCurrentAddress = lAddresses.get(0);
                StringBuilder lStringBuilder = new StringBuilder("Address: \n");
                for(int i = 0;
                        i < lCurrentAddress.getMaxAddressLineIndex();
                        i++) {
                    lStringBuilder.append(lCurrentAddress.getAddressLine(i)).append("\n");
                }
                mTextView.setText(lStringBuilder.toString());
            }
        } catch (IOException e) {
            mTextView.setText(e.getMessage());
        } catch (Exception e) {
            mTextView.setText(e.getMessage());
        }
    }
}
