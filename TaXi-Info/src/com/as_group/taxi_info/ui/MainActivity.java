package com.as_group.taxi_info.ui;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.as_group.taxi_info.R;
import com.as_group.taxi_info.ui.fragments.NavigationListFragment;
import com.as_group.taxi_info.ui.fragments.TaxiServiceFragment;

public class MainActivity extends FragmentActivity {
    private ListView mListView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.app_name,  /* "open drawer" description */
                R.string.app_name  /* "close drawer" description */
        ) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(R.string.app_name);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(R.string.app_name);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.left_drawer, new NavigationListFragment());
        ft.replace(R.id.content_frame, new TaxiServiceFragment());
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
