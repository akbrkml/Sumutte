package com.plapsstudio.sumutte.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.plapsstudio.sumutte.About;
import com.plapsstudio.sumutte.R;
import com.plapsstudio.sumutte.Wisata;
import com.plapsstudio.sumutte.adater.CustomListAdapter;
import com.plapsstudio.sumutte.app.AppController;
import com.plapsstudio.sumutte.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    public static final int ADD_MAP_KEY = 1;
    private static final String SELECTED_ITEM_ID = "selected_item_id";
    private static final String FIRST_TIME = "first_time";
    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mSelectedId;
    private boolean mUserSawDrawer = false;
    private static final String url = "http://sebelashuruf.web.id/sumutte/wisata.json";
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    private CustomListAdapter adapter;
    private static final String TAG = Home.class.getSimpleName();
    static final int tampil_error=1;
    InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sumutte");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8296380544960186/4856935353");


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                moveTaskToBack(true);
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd.loadAd(adRequest);


        mDrawer = (NavigationView) findViewById(R.id.main_drawer2);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout2);
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        if (!didUserSeeDrawer()) {
            showDrawer();
            markDrawerSeen();
        } else {
            hideDrawer();
        }

        navigate(mSelectedId);
        mDrawerLayout.closeDrawer(GravityCompat.START);

        if (cek_status(this))
        {
            listView = (ListView) findViewById(R.id.list);
            adapter = new CustomListAdapter(this, movieList);
            listView.setAdapter(adapter);

            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Mohon tunggu...");
            pDialog.show();

            // changing action bar color


            // Creating volley request obj
            JsonArrayRequest movieReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Movie movie = new Movie();
                                    movie.setTitle(obj.getString("title"));
                                    movie.setThumbnailUrl(obj.getString("image"));
                                    movie.setLokasi(obj.getString("lokasi"));
                                    movie.setId(obj.getString("id"));


                                    // adding movie to movies array
                                    movieList.add(movie);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();

                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(movieReq);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View view, int position, long id) {
                    String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                            .toString();

                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(),
                            Wisata.class);
                    // sending pid to next activity
                    in.putExtra("id", pid);

                    // starting new activity and expecting some response back
                    startActivityForResult(in, 100);

                }
            });

        }
        else {
            TextView konek = (TextView) findViewById(R.id.konek);
            konek.setText("Tidak ada koneksi internet");
            TextView load = (TextView) findViewById(R.id.load);
            load.setText("Muat ulang");
            load.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                }
            });
            showDialog(tampil_error);
        }


    }


    public boolean cek_status(Context cek) {
        ConnectivityManager cm = (ConnectivityManager) cek.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void showShareDialog(final Activity activity) {
        final Dialog dialog = new Dialog(activity, R.style.AppCompatAlertDialogStyle);
        dialog.setContentView(R.layout.dialog_share);

        Button twit = (Button) dialog.findViewById(R.id.btnTw);
        Button fb = (Button) dialog.findViewById(R.id.btnFb);
        Button mail = (Button) dialog.findViewById(R.id.btnMail);

        twit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sharerUrl = "https://twitter.com/intent/tweet?text=Download aplikasi Sumutte (Sumatera Utara The Explorer) hanya di https://play.google.com/store/apps/details?id=com.plapsstudio.sumutte";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                v.getContext().startActivity(intent);
                dialog.dismiss();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=play.google.com/store/apps/details?id=com.plapsstudio.sumutte";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));

                v.getContext().startActivity(intent);
                dialog.dismiss();
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sumutte");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Download aplikasiSumutte (Sumatera Utara The Explorer) hanya di https://play.google.com/store/apps/details?id=com.plapsstudio.sumutte");
                v.getContext().startActivity(Intent.createChooser(emailIntent, "Mengirim email..."));
            }
        });

        dialog.show();

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case tampil_error:
                AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
                errorDialog.setTitle("Kesalahan");
                errorDialog.setMessage("Tidak ada koneksi internet");
                errorDialog.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        dialog.dismiss();
                        Intent exit = new Intent(getApplicationContext(), History.class);
                        startActivity(exit);
                    }
                });
                AlertDialog errorAlert = errorDialog.create();
                return errorAlert;
            default:
                break;
        }
        return dialog;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
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

    private boolean didUserSeeDrawer() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = sharedPreferences.getBoolean(FIRST_TIME, false);
        return mUserSawDrawer;
    }

    private void markDrawerSeen() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = true;
        sharedPreferences.edit().putBoolean(FIRST_TIME, mUserSawDrawer).apply();
    }

    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void navigate(int mSelectedId) {
        Intent intent = null;
        switch (mSelectedId){

            case R.id.share:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                showShareDialog(Home.this);
                break;
            case R.id.rute:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(getApplicationContext(), AddRouteActivity.class);
                startActivity(intent);
                break;
            case R.id.history:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
                break;
            case R.id.navAbout:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
                break;


        }
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent intent = new Intent(getApplicationContext(), AddRouteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();

        navigate(mSelectedId);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }


    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            moveTaskToBack(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        return true;
    }


}
