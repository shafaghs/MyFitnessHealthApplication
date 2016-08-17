package com.shafagh.myfitnesshealthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

public class SignUpPageCon extends AppCompatActivity {

    String gender,weight,height,birthDay,activityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page_con);
        Intent ex = getIntent();
        Bundle bn = ex.getExtras();
        gender = bn.getString("gender");
        weight = bn.getString("weight");
        height = bn.getString("height");
        birthDay = bn.getString("birthDay");

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent parent = NavUtils.getParentActivityIntent(this);
            NavUtils.shouldUpRecreateTask(this,parent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void submit(View view){
        Double hbf=0.0;
        int checkedActivityId =((RadioGroup)findViewById(R.id.activity)).getCheckedRadioButtonId();
        switch (checkedActivityId){
            case (R.id.sedentary):
                hbf=1.2;
                break;
            case R.id.lightActive:
                hbf=1.375;
                break;
            case R.id.moderateActive:
                hbf=1.55;
                break;
            case R.id.veryActive:
                hbf=1.725;
                break;
            case R.id.extraActive:
                hbf=1.9;
                break;
        }

        int birthYear =Integer.parseInt(birthDay.substring(birthDay.length()-4));
        int age = Calendar.getInstance().get(Calendar.YEAR)-birthYear;
        Double BMR=0.0;

        switch(gender){
            case "Female":
                if(weight.contains("kg") && height.contains("cm")){
                    //BMR = 655 + ( 9.6 x weight in kilos ) + ( 1.8 x height in cm ) - ( 4.7 x age in years )
                    BMR = 655 + (9.6 * Double.parseDouble(weight.replaceAll("[^0-9.]",""))) +(1.8 * Double.parseDouble(height.replaceAll("[^0-9]",""))) - (4.7 * age);
                }
                if(weight.contains("lbs") && height.contains("ft")){
                    //BMR = 655 + ( 4.35 x weight in pounds ) + ( 4.7 x height in inches ) - ( 4.7 x age in years )
                    String[] heightEl = height.split(",");
                    heightEl[0]=heightEl[0].replaceAll("[^0-9]","");
                    heightEl[1]=heightEl[1].replaceAll("[^0-9]","");
                    Double inchHeight = Double.parseDouble(heightEl[0])*12 + Double.parseDouble(heightEl[1]);
                    Log.i("height", Double.toString(inchHeight));
                    Log.i("weight", weight.replaceAll("[^0-9.]",""));
                    Log.i("age", Integer.toString(age));
                    BMR = 655 + (4.35 * Double.parseDouble(weight.replaceAll("[^0-9.]",""))) +(4.7 * inchHeight) - (4.7 * age);
                }
                break;
            case "Male":
                if(weight.contains("kg") && height.contains("cm")){
                    // Men: BMR = 66 + ( 13.7 x weight in kilos ) + ( 5 x height in cm ) - ( 6.8 x age in years )
                    BMR = 66 + (13.7 * Double.parseDouble(weight.replaceAll("[^0-9.]",""))) +(5 * Double.parseDouble(height.replaceAll("[^0-9]",""))) - (6.8 * age);
                }
                if(weight.contains("lbs") && height.contains("ft")){
                    //Men: BMR = 66 + ( 6.23 x weight in pounds ) + ( 12.7 x height in inches ) - ( 6.8 x age in year )
                    String[] heightEl = height.split(",");
                    heightEl[0]=heightEl[0].replaceAll("[^0-9]","");
                    heightEl[1]=heightEl[1].replaceAll("[^0-9]","");
                    Double inchHeight = Double.parseDouble(heightEl[0])*12 + Double.parseDouble(heightEl[1]);
                    BMR = 66 + (6.23 * Double.parseDouble(weight.replaceAll("[^0-9.]",""))) +(12.7 * inchHeight) - (6.8 * age);
                }
                break;
        }
        Log.i("hbf",Double.toString(BMR));
        Log.i("info",gender+","+weight+","+height+","+birthDay);

        Double dailyCalories = hbf * BMR;
        Intent intent = new Intent(this,Dairy.class).putExtra("dailyCalories",dailyCalories);
        startActivity(intent);
    }
}

