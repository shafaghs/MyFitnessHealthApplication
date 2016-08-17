package com.shafagh.myfitnesshealthapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.text.DecimalFormat;

public class WeightFragment extends DialogFragment  {
    public WeightFragment(){}
    EditText kgValue,lbsValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weight_fragment,container,false);
        //set buttons
        Button kgButton = (Button) view.findViewById(R.id.kgBut);
        Button poundButton = (Button) view.findViewById(R.id.poundBut);
        Button doneButton = (Button) view.findViewById(R.id.doneBut);
        //set layouts
        final LinearLayout pound = (LinearLayout) view.findViewById(R.id.lbs);
        final LinearLayout kilogram = (LinearLayout) view.findViewById(R.id.kg);

        kgValue = (EditText) view.findViewById(R.id.kg_value);
        lbsValue = (EditText) view.findViewById(R.id.lbs_value);

        //button listener
        kgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                pound.setVisibility(View.GONE);
                kilogram.setVisibility(View.VISIBLE);
                if(!isEmpty(lbsValue)){
                    Double v = roundOneDecimals(Double.parseDouble(lbsValue.getText().toString()) * 0.45);
                    kgValue.setText(Double.toString(v));
                }
            }
        });

        poundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                kilogram.setVisibility(View.GONE);
                pound.setVisibility(View.VISIBLE);
                if(!isEmpty(kgValue)){
                    Double v = roundOneDecimals(Double.parseDouble(kgValue.getText().toString()) / 0.45);
                    lbsValue.setText(Double.toString(v));
                }
            }
        });


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                if(isEmpty(lbsValue) && isEmpty(kgValue)){
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Error Message")
                            .setMessage("You should fill your weight")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else {
                    EditDialogListener activity = (EditDialogListener) getActivity();
                    if(!isEmpty(lbsValue)){
                        activity.updateResult(lbsValue.getText().toString(),"lbs");
                        dismiss();}
                    if(!isEmpty(kgValue)){
                        activity.updateResult(kgValue.getText().toString(),"kg");
                        dismiss();
                    }
                }
            }
        });


        getDialog().setTitle("Your Weight Information");
        kilogram.setVisibility(View.GONE);
        return view;
    }

    public interface EditDialogListener {
        void updateResult(String inputText,String unit);
    }

    double roundOneDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
