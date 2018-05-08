package itp.com.aqeelmirza_searchaddress;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import itp.com.aqeelmirza_searchaddress.Adapter.MainRecyclerView_Adapter;
import itp.com.aqeelmirza_searchaddress.Utils.DB_Helper;
import itp.com.aqeelmirza_searchaddress.Utils.List_DataModel;


public class MainActivity extends AppCompatActivity {

    ArrayList<List_DataModel> list_dataModelArrayList;
    DB_Helper db;
    private static final int REQUEST_LOC = 1;
    private View parent_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_layout = findViewById(R.id.parent_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView no_Loc = findViewById(R.id.no_loc_tv);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();

        db = new DB_Helper(this);

        list_dataModelArrayList = db.getAllItems();
        if (list_dataModelArrayList.size() > 0) {

            recyclerView.setVisibility(View.VISIBLE);
            no_Loc.setVisibility(View.GONE);

            MainRecyclerView_Adapter recyclerAdapter = new MainRecyclerView_Adapter(this,
                    R.layout.list_items, list_dataModelArrayList);
            recyclerView.setAdapter(recyclerAdapter);
        } else {

            recyclerView.setVisibility(View.GONE);
            no_Loc.setVisibility(View.VISIBLE);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        requestLocPermission();

                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOC);
                    }
                } else {
                    //allow to Maps Activity
                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    //Requesting LOCATION permission
    private void requestLocPermission() {
        Log.i("", "Location permission has NOT been granted. Requesting permission.");
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.i("",
                    "Displaying location permission rationale to provide additional context.");
            Toast.makeText(this, "Please enable location permission", Toast.LENGTH_SHORT).show();
            Snackbar.make(parent_layout, R.string.permission_location_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Grant", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_LOC);
                        }
                    })
                    .show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOC);
        }
    }
}
