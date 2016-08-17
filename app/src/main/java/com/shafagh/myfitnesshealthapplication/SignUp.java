package com.shafagh.myfitnesshealthapplication;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {
    private Toolbar toolbar;

    Button bEmail;
    Button bFacebook;
    Button bGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bEmail = (Button) findViewById(R.id.sign_email);
        bFacebook = (Button) findViewById(R.id.sign_facebook);
        bGoogle = (Button) findViewById(R.id.sign_google) ;

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    public void signUpEmail(View view){
        Intent intent = new Intent(this,SignUpEmail.class);
        startActivity(intent);
    }

    public void signUpPage(View view){
        Intent intent=new Intent(this,SignUpPage.class);
        startActivity(intent);

    }
}
