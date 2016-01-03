package com.plapsstudio.sumutte;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class About extends AppCompatActivity {

    Toolbar mToolbar;
    TextView fb, tw, mail, plaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tentang");

        fb = (TextView) findViewById(R.id.fb);
        tw = (TextView) findViewById(R.id.tw);
        mail = (TextView) findViewById(R.id.mail);
        plaps = (TextView) findViewById(R.id.kolodete);

        fb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://facebook.com/plapsstudio/"));
                startActivity(i);
            }
        });

        plaps.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://plapsstudio.com/"));
                startActivity(i);
            }
        });

        tw.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://twitter.com/plapsstudio/"));
                startActivity(i);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "plapsstudio@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Donornesia");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Kritik dan saran");
                startActivity(Intent.createChooser(emailIntent, "Mengirim email..."));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
