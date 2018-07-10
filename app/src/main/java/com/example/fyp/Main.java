package com.example.fyp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;

import com.example.fyp.DashboardFragment;
import com.example.fyp.TimetableFragment;
import com.example.fyp.SubjectFragment;
import com.example.fyp.FeedbackFragment;
import com.example.fyp.ExpenditureFragment;

public class Main extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Dashboard");

        Fragment fragment = new DashboardFragment();
        displayFragment(fragment, null, "data");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    break;
                case R.id.navigation_timetable:
                    fragment = new TimetableFragment();
                    break;
                case R.id.navigation_subject:
                    fragment = new SubjectFragment();
                    break;
                case R.id.navigation_feedback:
                    fragment = new FeedbackFragment();
                    break;
                case R.id.navigation_expenditure:
                    fragment = new ExpenditureFragment();
                    break;
            }
            displayFragment(fragment, null, "data");

            return true;
        }
    };

    public void displayFragment(Fragment fragment, Bundle bundle, String tag) {

        if (fragment != null) {
            if (bundle != null)
                fragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, fragment, tag);
            ft.addToBackStack("fragment");
            ft.commit();
        }

//    private void loadFragment(android.support.v4.app.Fragment fragment) {
//        // load fragment
//        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
////        FragmentManager fm= getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
    }
}

