package project.comp3717.bcit.ca.physics;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Author: Jeremy Yang
 * Last Update: Feb. 27, 2016
 *
 * Class for the converter header.
 */
public class ConverterHead extends Fragment {

    private static View view;            //the view, converter header fragment, that is associate with this class
    private static EditText inputBox;    //the edit text box
    private static Spinner unitSpinner;  //the spinner
    private static String userUnit;      //user chosen unit (according to the spinner)
    private static ArrayAdapter<CharSequence> lengthArrayAdapter, timeArrayAdapter;  //deal with spinner text list

    /**
     * inputBox setter.
     * empty the inputBox.
     */
    public static void emptyInputBox() {
        if(inputBox.getText().length() != 0)
            inputBox.setText("");
    }

    /**
     * Spinner setter: set to length units.
     */
    public static void spinnerLength() {
        unitSpinner.setAdapter(lengthArrayAdapter);
    }

    /**
     * Spinner setter: set to time units.
     */
    public static void spinnerTime() {
        unitSpinner.setAdapter(timeArrayAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.converter_head, container, false);  //assign converter_head layout to this class

        //setup array adapter for the list of unit for length
        lengthArrayAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.unitsLength, android.R.layout.simple_spinner_item);
        lengthArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setup array adapter for the list of unit for time
        timeArrayAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.unitsTime, android.R.layout.simple_spinner_item);
        timeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ConverterTabListener tabListener = new ConverterTabListener();      //create a tab listener
        Button lengthButton = (Button)view.findViewById(R.id.buttonLength); //get length tab
        lengthButton.setOnClickListener(tabListener);                       //assign listener to tab
        Button timeButton = (Button)view.findViewById(R.id.buttonTime);     //get time tab
        timeButton.setOnClickListener(tabListener);                         //assign listener to tab

        unitSpinner = (Spinner)view.findViewById(R.id.unitSpinner);  //get spinner
        spinnerLength();  //set length units as the default spinner
        //set spinner listener
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Action to take when selecting a item in the spinner.
             * @param parent
             * @param view
             * @param pos
             * @param id
             */
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                userUnit = parent.getItemAtPosition(pos).toString();
                updateConversion();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

        inputBox = (EditText)view.findViewById(R.id.inputText);  //get edit text box
        //set edit text box text-change listener
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            /**
             * Action to take when changing text
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateConversion();  //update unit conversion table
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });

        //set edit text box click listener
        inputBox.setOnClickListener(new View.OnClickListener() {
            /**
             * Action to take when click on the edit text box
             * @param v the edit text box itself
             */
            @Override
            public void onClick(View v) {
                inputBox.setSelection(inputBox.getText().length());  //set the input cursor the end of the text
                                                                     //only let the user edit from the end
            }
        });

        return view;
    }

    /**
     * Update the conversion unit table
     */
    public static void updateConversion() {
        //update for length unit table
        if(ConverterMain.getCurrFrag().equals("length")) {
            if (inputBox.getText().length() > 0) {
                String userInputS = inputBox.getText().toString();   //convert edit text to string
                double userInputD = Double.parseDouble(userInputS);  //convert string to double
                ConverterLength.update(userInputD, userUnit);  //update
            } else {
                ConverterLength.defaultValue();
            }
        }

        //update for time unit table
        if(ConverterMain.getCurrFrag().equals("time")) {
            if (inputBox.getText().length() > 0) {
                String userInputS = inputBox.getText().toString();   //convert edit text to string
                double userInputD = Double.parseDouble(userInputS);  //convert string to double
                ConverterTime.update(userInputD, userUnit);  //update
            } else {
                ConverterTime.defaultValue();
            }
        }
    }

    /**
     * Converter tab onClick listener
     */
    class ConverterTabListener implements View.OnClickListener {
        public void onClick(View v) {
            //detect tab clicked
            switch (v.getId()) {
                case R.id.buttonLength:
                    //only switch if on different fragment
                    if (!ConverterMain.getCurrFrag().equals("length")) {
                        ConverterMain.switchToLengthFrag();  //switch fragment
                        ConverterHead.spinnerLength();       //update spinner
                        ConverterHead.emptyInputBox();      //clear edit text box
                    }
                    break;

                case R.id.buttonTime:
                    //only switch if on different fragment
                    if (!ConverterMain.getCurrFrag().equals("time")) {
                        ConverterMain.switchToTimeFrag();   //switch fragment
                        ConverterHead.spinnerTime();        //update spinner
                        ConverterHead.emptyInputBox();      //clear edit text box
                    }
                    break;
            }
        }
    }
}