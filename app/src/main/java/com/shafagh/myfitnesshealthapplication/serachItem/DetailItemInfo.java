package com.shafagh.myfitnesshealthapplication.serachItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.shafagh.myfitnesshealthapplication.R;

public class DetailItemInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item_infromation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String nameValue = getIntent().getExtras().getString("itemName");
        String servingSizeValue = getIntent().getExtras().getString("servingSize");
        String caloriesValue = getIntent().getExtras().getString("calories");

        ((TextView)findViewById(R.id.d_name)).setText(nameValue);
        ((TextView)findViewById(R.id.d_number_of_serving_value)).setText(servingSizeValue);
        ((TextView)findViewById(R.id.d_calories_value)).setText(caloriesValue);
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
}
