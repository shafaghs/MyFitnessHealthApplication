package com.shafagh.myfitnesshealthapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class BgTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    AlertDialog.Builder show;

    Context ctx;
    private View rootView;

    BgTask(Context ctx, View rootView){
        this.ctx = ctx;
        this.rootView=rootView;
    }

    @Override
    protected String doInBackground(String... params) {
        String regUrl="http://10.0.2.2/androiddb/register.php";
        String loginUrl="http://10.0.2.2/androiddb/login.php";
        String checkUser = "http://10.0.2.2/androiddb/checkUser.php";
        String method = params[0];
        if(method.equals("logIn")){
            String userName = params[1];
            String userPass = params[2];
            try {
                URL url = new URL(loginUrl);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"+
                        URLEncoder.encode("userPass","UTF-8")+"="+URLEncoder.encode(userPass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream Is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Is,"iso-8859-1"));
                String response="";
                String line="";
                while((line = bufferedReader.readLine()) != null){
                    response += line;
                }
                Is.close();
                Log.i("method0000000",response);
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(method.equals("register")){
            String userName = params[1];
            String userPass = params[2];
            String emailAdd = params[3];
            try {
                URL url = new URL(regUrl);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"+
                        URLEncoder.encode("userPass","UTF-8")+"="+URLEncoder.encode(userPass,"UTF-8")+"&"+
                        URLEncoder.encode("emailAdd","UTF-8")+"="+URLEncoder.encode(emailAdd,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream Is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Is,"iso-8859-1"));
                String response="";
                String line="";
                while((line = bufferedReader.readLine()) != null){
                    response += line;
                }
                Is.close();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(method.equals("checkUser")){
            String userName = params[1];
            Log.i("where",userName);
            try {
                URL url = new URL(checkUser);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream Is = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Is,"iso-8859-1"));
                String response="";
                String line="";
                while((line = bufferedReader.readLine()) != null){
                    response += line;
                }
                bufferedReader.close();
                urlConnection.disconnect();
                Is.close();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent;
        TextView userMsg;
        switch (result){
            case "validuser":
                intent = new Intent(ctx,Dairy.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
                break;
            case "invaliduser":
                Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
                break;
            case "Registration Success":
                Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
                intent = new Intent(ctx,SignUpPage.class);
                ctx.startActivity(intent);
                break;
            case "exist":
                userMsg = (TextView) rootView.findViewById(R.id.user_msg);
                userMsg.setText("This username exist, try another one");
                break;
            case "valid":
                userMsg = (TextView) rootView.findViewById(R.id.user_msg);
                userMsg.setText(" ");
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
