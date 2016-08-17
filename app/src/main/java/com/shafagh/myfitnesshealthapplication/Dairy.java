package com.shafagh.myfitnesshealthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Dairy extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.main_container);
        getLayoutInflater().inflate(R.layout.dairy, contentFrameLayout);

        TextView todayDate =(TextView)findViewById(R.id.today_date);

        DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
        todayDate.setText(df.format(Calendar.getInstance().getTime()));

     //   TextView dailyCalories =(TextView)findViewById(R.id.daily_calories);
        Intent getExtraInfo = getIntent();
        Double calories = getExtraInfo.getDoubleExtra("dailyCalories",0.0);
        ((TextView)findViewById(R.id.daily_calories)).setText(Double.toString(calories));
        ((TextView)findViewById(R.id.remaining)).setText(Double.toString(calories));

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);*/

    }

    public void updateResult(String inputText, int unit, int calories) {}

    public void addItem(View view){
        Intent intent = new Intent(this,AddItemInfo.class);
        startActivity(intent);
        //FragmentManager fm = getSupportFragmentManager();
        //AddItemFragment dialogFragment = new AddItemFragment();
        //dialogFragment.show(fm,"Add Items Information");
    }

}
