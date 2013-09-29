package br.com.geolocationcookbook.maps;

/**
 * Created by Fabricio on 29/09/13.
 */
import android.app.Activity;
import android.os.Bundle;

import br.com.geolocationcookbook.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }
}