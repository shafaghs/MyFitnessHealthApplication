package com.shafagh.myfitnesshealthapplication.serachItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.shafagh.myfitnesshealthapplication.R;

public class Frequent extends Fragment {

    public Frequent() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frequent, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.res);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                String itemName= ((TextView) view.findViewById(R.id.name)).getText().toString();
                bundle.putString("itemName",itemName);
                String servingSize= ((TextView) view.findViewById(R.id.unit)).getText().toString();
                bundle.putString("servingSize",servingSize);
                String calories= ((TextView) view.findViewById(R.id.calories)).getText().toString();
                bundle.putString("calories",calories);
                Intent myIntent = new Intent(getActivity(), DetailItemInfo.class);
                myIntent.putExtras(bundle);
                getActivity().startActivity(myIntent);
            }
        });
        return rootView;
    }
}
