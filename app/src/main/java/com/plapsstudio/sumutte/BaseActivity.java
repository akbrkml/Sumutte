package com.plapsstudio.sumutte;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.plapsstudio.sumutte.R;


public class BaseActivity extends ActionBarActivity {

    private Toolbar toolbar;
//
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }


    protected Toolbar getActionBarToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(toolbar);
        }
        return toolbar;
    }
}
