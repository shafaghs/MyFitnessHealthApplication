package com.shafagh.myfitnesshealthapplication.serachItem;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import com.shafagh.myfitnesshealthapplication.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

public class SearchBgTask extends AsyncTask<String,Void,String> {

    String searchUrl;
    String result;
    Context ctx;
    private View rootView;
    private String searchKey;
    ListView listView;
    ItemAdapter itemAdapter;

    SearchBgTask(Context ctx, View rootView){
        this.ctx = ctx;
        this.rootView=rootView;
    }

    @Override
    protected void onPreExecute() {
        searchUrl = "http://10.0.2.2/androiddb/autoSearch.php";
    }

    @Override
    protected String doInBackground(String... params) {
        searchKey=params[0];
        try {
            URL url = new URL(searchUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //send request
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data = URLEncoder.encode("itemName","UTF-8")+"="+URLEncoder.encode(searchKey,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            //get result
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while((result = bufferedReader.readLine())!= null){
                stringBuilder.append(result+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String finalResult) {
        itemAdapter = new ItemAdapter(ctx,R.layout.row_layout);
        listView =(ListView) rootView.findViewById(R.id.res);
        listView.setAdapter(itemAdapter);
        try {
            JSONObject jsonObj =new JSONObject(finalResult);
            JSONArray jsonArray = jsonObj.getJSONArray("server_res");
            int count = 0;
            String itemName,itemUnit,itemCalories;
            while(count<jsonArray.length()){
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                itemName = jsonObject.getString("item_name");
                itemUnit = jsonObject.getString("item_unit");
                itemCalories = jsonObject.getString("item_calories");
                ItemInfo itemInfo = new ItemInfo(itemName,itemUnit,itemCalories);
                itemAdapter.add(itemInfo);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
