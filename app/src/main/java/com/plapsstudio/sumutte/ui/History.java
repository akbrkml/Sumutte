package com.plapsstudio.sumutte.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.plapsstudio.sumutte.BaseActivity;
import com.plapsstudio.sumutte.R;
import com.plapsstudio.sumutte.adapter.ListRouteAdapter;
import com.plapsstudio.sumutte.helper.RecyclerItemClickListener;
import com.plapsstudio.sumutte.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;


public class History extends BaseActivity implements RecyclerItemClickListener.OnItemClickListener {

    public static final int FROM_LOCAL = 0;
    public static final int FROM_NET = 1;


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerView;
    @InjectView(R.id.emptytext)
    TextView emptyText;

    private ListRouteAdapter adapterRoute;
    private List<LocationModel> locationModels;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.inject(this);

        realm = Realm.getInstance(this);

        getSupportActionBar().setTitle("Riwayat Wisata");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        locationModels = new ArrayList<>();
        adapterRoute = new ListRouteAdapter(this, locationModels);

        updateView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRoute);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this) {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        readDataFromDb();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void updateView() {
        if (locationModels.isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void readDataFromDb() {
        if (locationModels.size() > 0)
            locationModels.clear();
        RealmResults<LocationModel> result = realm
                .where(LocationModel.class)
                .findAll();
        // sortiny by id.
        // show greater first
        result.sort("id", RealmResults.SORT_ORDER_DESCENDING);
        for (LocationModel data : result) {
            Log.d("tag", "datanya " + data.getId());
            locationModels.add(data);
        }
        // notify adapter
        adapterRoute.notifyDataSetChanged();

        // update view
        updateView();
    }



    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "" + locationModels.get(position).getTujuan(), Toast.LENGTH_SHORT).show();
        Bundle data = new Bundle();
        data.putInt("status", FROM_LOCAL);
        data.putString("id", locationModels.get(position).getId());
        startActivity(new Intent(this, ViewRouteActivity.class).putExtras(data));
    }

    @Override
    protected void onResume() {
        super.onResume();
        readDataFromDb();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
