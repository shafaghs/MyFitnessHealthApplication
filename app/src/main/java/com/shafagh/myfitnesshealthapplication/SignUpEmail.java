package com.shafagh.myfitnesshealthapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SignUpEmail extends AppCompatActivity {
    private EditText user_name, email, password, con_password;
    private TextView userMsg;
    String userName, emailAdd, userPass, method ;
    Context ctx;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);
        rootView = findViewById(android.R.id.content);

        user_name = (EditText) findViewById(R.id.user_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        con_password = (EditText) findViewById(R.id.con_password);

        userMsg = (TextView) findViewById(R.id.user_msg);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ctx = this.getApplicationContext();

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        user_name.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    method = "checkUser";
                    userName = user_name.getText().toString().trim();
                    BgTask backgroundTask = new BgTask(ctx,rootView);
                    backgroundTask.execute(method, userName);
                }
            }
        });

    }


    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void register(View view) {
        if (isEmpty(user_name) || isEmpty(email) || isEmpty(password) || isEmpty(con_password)) {
            showDialog("You should fill all fields");
        } else {
            if (!password.getText().toString().equals(con_password.getText().toString())) {
                showDialog("Password and Confirm Password fields should be the same");
            } else if (!isValidEmail((CharSequence) email.getText().toString())) {
                showDialog("Email Address Format is not valid");
            } else {
                method = "register";
                userName = user_name.getText().toString();
                userPass = password.getText().toString();
                emailAdd = email.getText().toString();
                BgTask backgroundTask = new BgTask(this,rootView);
                backgroundTask.execute(method, userName, userPass, emailAdd);
            }
        }

    }

    public boolean isEmpty(EditText text) {
        return text.getText().toString().trim().length() == 0;
    }

    public void showDialog(String msg) {
        AlertDialog show = new AlertDialog.Builder(this)
                .setTitle("Error Message")
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
