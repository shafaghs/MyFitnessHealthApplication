package com.shafagh.myfitnesshealthapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {
    Context ctx;
    View rootView;
    EditText LoginUserName,LoginPassword;
    String Login_UserName,Login_Password,method;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        rootView = findViewById(android.R.id.content);
        ctx = this.getApplicationContext();
        LoginUserName = (EditText) findViewById(R.id.LogIn_userName);
        LoginPassword = (EditText) findViewById(R.id.LogIn_password);

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

    public void logIn_logIn(View view){
        if(isEmpty(LoginUserName) || isEmpty(LoginPassword)){
            showDialog("You should fill all fields");
        }
        else{
            method = "logIn";
            Login_UserName = LoginUserName.getText().toString().trim();
            Login_Password = LoginPassword.getText().toString().trim();
            BgTask bgTask = new BgTask(ctx,rootView);
            bgTask.execute(method,Login_UserName,Login_Password);
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
}
