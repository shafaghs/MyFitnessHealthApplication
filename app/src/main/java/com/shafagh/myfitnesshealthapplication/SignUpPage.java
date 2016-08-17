package com.shafagh.myfitnesshealthapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.text.DecimalFormat;

public class SignUpPage extends AppCompatActivity implements WeightFragment.EditDialogListener, DatePickerDialog.OnDateSetListener, HeightFragment.EditDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String bDate = (month + 1) + " / " + day + " / " + year;
        ((EditText) findViewById(R.id.birth_day_value)).setText(bDate);
    }

    public void updateResult(String inputText, String unit) {
        String result = inputText + " " + unit;
        if (unit == "lbs" || unit == "kg")
            ((TextView) findViewById(R.id.weight_value)).setText(result);
        else {
            if (unit == "ft") {
                String[] r = inputText.split(",");
                String a = r[0] + " ft, " + r[1] + " in";
                ((TextView) findViewById(R.id.height_value)).setText(a);
            } else
                ((TextView) findViewById(R.id.height_value)).setText(result);
        }
    }

    public void heightDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        HeightFragment dialogFragment = new HeightFragment();
        dialogFragment.show(fm, "Height Fragment");
    }

    public void weightDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        WeightFragment dialogFragment = new WeightFragment();
        dialogFragment.show(fm, "Weight Fragment");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    double roundOneDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
    }

    public void nextPage(View view) {
        RadioGroup rd = (RadioGroup) findViewById(R.id.gender_radio_group);
        EditText heightValue = (EditText) findViewById(R.id.height_value);
        EditText weightValue = (EditText) findViewById(R.id.weight_value);
        EditText birthDayValue = (EditText) findViewById(R.id.birth_day_value);

        if (rd.getCheckedRadioButtonId() == -1 || isEmpty(heightValue) || isEmpty(weightValue) || isEmpty(birthDayValue)) {
            new AlertDialog.Builder(this)
                    .setTitle("Error Message")
                    .setMessage("You should fill all fields")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            int id = rd.getCheckedRadioButtonId();
            String selection = (String)((RadioButton) findViewById(id)).getText();
            Bundle info = new Bundle();
            info.putString("gender",selection);
            info.putString("weight",weightValue.getText().toString());
            info.putString("height",heightValue.getText().toString());
            info.putString("birthDay",birthDayValue.getText().toString());
            Intent myintent=new Intent(this, SignUpPageCon.class).putExtras(info);
            startActivity(myintent);
        }
    }

}
