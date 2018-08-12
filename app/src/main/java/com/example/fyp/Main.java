package com.example.fyp;


import android.content.Intent;
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
import android.support.v4.app.FragmentManager;
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

import static com.example.fyp.R.id.navigation_timetable;

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
        displayFragment(fragment, null, "");
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            String tag = "";
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    actionBar.setTitle("Dashboard");
                    break;
                case navigation_timetable:
                    fragment = new TimetableFragment();
                    tag = "FRAG_TIMETABLE";
                    actionBar.setTitle("Timetable");
                    break;
                case R.id.navigation_subject:
                    fragment = new SubjectFragment();
                    actionBar.setTitle("Subject");
                    break;
                case R.id.navigation_feedback:
                    fragment = new FeedbackFragment();
                    actionBar.setTitle("Feedback");
                    break;
                case R.id.navigation_expenditure:
                    fragment = new ExpenditureFragment();
                    actionBar.setTitle("Expenditure Planning");
                    break;
            }
            displayFragment(fragment, null, tag);

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
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 10001) && (resultCode == 10001)) {

            Fragment fragment  = new Fragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
        }
    }

}

