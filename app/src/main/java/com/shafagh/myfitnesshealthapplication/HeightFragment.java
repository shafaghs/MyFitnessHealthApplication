package com.shafagh.myfitnesshealthapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.DecimalFormat;

public class HeightFragment extends DialogFragment {
    public HeightFragment() {}

    EditText ftValue, inchValue, centimeterValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.height_fragment, container, false);
        //set buttons
        Button centBut = (Button) view.findViewById(R.id.centimeterBut);
        Button inchBut = (Button) view.findViewById(R.id.inchBut);
        Button doneButton = (Button) view.findViewById(R.id.doneBut);
        //set layouts
        final LinearLayout inch = (LinearLayout) view.findViewById(R.id.ft);
        final LinearLayout centimeter = (LinearLayout) view.findViewById(R.id.cen);

        ftValue = (EditText) view.findViewById(R.id.height_ft);
        inchValue = (EditText) view.findViewById(R.id.height_in);
        centimeterValue = (EditText) view.findViewById(R.id.height_centimeter);

        //Button Listeners
        centBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                inch.setVisibility(View.GONE);
                centimeter.setVisibility(View.VISIBLE);
                if (!isEmpty(ftValue) && !isEmpty(inchValue)) {
                    Double v = roundOneDecimals((Double.parseDouble(ftValue.getText().toString()) * 12 + Double.parseDouble(inchValue.getText().toString())) * 2.54);
                    centimeterValue.setText(Double.toString(v));
                }
            }
        });

        inchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                centimeter.setVisibility(View.GONE);
                inch.setVisibility(View.VISIBLE);
                if (!isEmpty(centimeterValue)) {
                    Double cm = Double.parseDouble(centimeterValue.getText().toString());
                    long v = (long) (cm / 30.48);
                    long vv = Math.round((cm - (v * 30.48)) / 2.54);
                    ftValue.setText(Long.toString(v));
                    inchValue.setText(Long.toString(vv));
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                if (isEmpty(ftValue) && isEmpty(inchValue) && isEmpty(centimeterValue)) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Error Message")
                            .setMessage("You should fill your height")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    EditDialogListener activity = (EditDialogListener) getActivity();
                    if (!isEmpty(ftValue) && !isEmpty(ftValue)) {
                        Log.i("feet", "ft");
                        String rrresult = ftValue.getText().toString() + "," + inchValue.getText().toString();
                        activity.updateResult(rrresult, "ft");
                        dismiss();
                    }
                    if (!isEmpty(centimeterValue)) {
                        Log.i("centimeter", "cm");
                        activity.updateResult(centimeterValue.getText().toString(), "cm");
                        dismiss();
                    }
                }
            }
        });


        getDialog().setTitle("Your Height Information");
        centimeter.setVisibility(View.GONE);
        return view;
    }

    public interface EditDialogListener {
        void updateResult(String inputText, String unit);
    }

    double roundOneDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
