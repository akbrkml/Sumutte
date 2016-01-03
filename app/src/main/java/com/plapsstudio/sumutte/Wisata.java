package com.plapsstudio.sumutte;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.plapsstudio.sumutte.adater.CustomListAdapter;
import com.plapsstudio.sumutte.model.Movie;
import com.plapsstudio.sumutte.ui.AddRouteActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Wisata extends AppCompatActivity {
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "id";
    private static final String TAG_WST = "wisata";
    private static final String TAG_NAMA = "nama";
    private static final String TAG_LOC = "deskripsi";
    private static final String TAG_IMG = "foto";
    private NetworkImageView mNetworkImageView;
    private ImageLoader mImageLoader;
    private CustomListAdapter adapter;
    private List<Movie> movieList = new ArrayList<Movie>();
    private static final String TAG = Wisata.class.getSimpleName();
    private ProgressDialog pDialog;
    private static final String url = "http://sebelashuruf.web.id/sumutte/wisata.json";
    private static String get_post_url = "http://sebelashuruf.web.id/sumutte/get_product_details.php";
    Toolbar mToolbar;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> postList;
    JSONArray postListArray = null;
    String pid;
    TextView nama, des;
    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Wisata");

        mNetworkImageView = (NetworkImageView) findViewById(R.id
                .thumbnail);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        requestHandler = new RequestHandler();
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Intent i = getIntent();

        // getting product id (pid) from intent
        pid = i.getStringExtra(TAG_ID);
        Log.e("haha", pid);
        //Timeline List
        new LoadAllPosts().execute();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }

    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;
    }

    class LoadAllPosts extends AsyncTask<String, String, String> {
        JSONObject product;
        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Wisata.this);
            pDialog.setMessage("Mohon Tunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", pid));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(get_post_url, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            int success;
            try {
                // Building Parameters


                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // successfully received product details
                    JSONArray productObj = json
                            .getJSONArray(TAG_WST); // JSON Array

                    // get first product object from JSON Array
                    product = productObj.getJSONObject(0);

                    // product with this pid found
                    // Edit Text
                    nama = (TextView) findViewById(R.id.nama);
                    des = (TextView) findViewById(R.id.desc);
                    NetworkImageView foto = (NetworkImageView) findViewById(R.id.thumbnail);

                    final String desc = product.getString(TAG_LOC);
                    final String name = product.getString(TAG_NAMA);
                    final String img = product.getString(TAG_IMG);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_NAMA, name);
                    map.put(TAG_LOC, desc);
                    map.put(TAG_IMG, img);

                    runOnUiThread(new Runnable() {
                        public void run() {
                            nama.setText(name);
                            des.setText(desc);

                            mImageLoader = CustomVolleyRequest.getInstance(getApplicationContext())
                                    .getImageLoader();

                            mImageLoader.get(img, ImageLoader.getImageListener(mNetworkImageView,
                                    R.drawable.placeholder, android.R.drawable
                                            .ic_dialog_alert));
                            mNetworkImageView.setImageUrl(url, mImageLoader);
                        }

                    });

                }else{
                    // product with pid not found
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent intent = new Intent(getApplicationContext(), AddRouteActivity.class);
        startActivity(intent);
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
