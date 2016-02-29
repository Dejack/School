package project.comp3717.bcit.ca.physics;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: Jeremy Yang
 * Last Update: Feb. 27, 2016
 *
 * Main class of the converter.
 * In charge of the unit fragments.
 */
public class ConverterMain extends AppCompatActivity {
    private static FragmentManager manager;     //deal with fragments, use to switch between fragments
    private static String currentFrag;          //remember the name of the current active fragment
    private static ConverterTime timeFrag;      //an instance of the time unit fragment
    private static ConverterLength lengthFrag;  //an instance of the length unit fragment

    //currentFrag getter
    public static String getCurrFrag() {
        return currentFrag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_main);  //set this class to layout converter_main

        manager = getFragmentManager();

        timeFrag = new ConverterTime();     //create a new time unit fragment
        lengthFrag = new ConverterLength(); //create a new time unit fragment
        //set the length unit fragment as the default fragment
        manager.beginTransaction().add(R.id.converter_layout, lengthFrag).commit();

        //these are used to fix weird bug where the fragment won't switch to timeFrag without going
        //into lengthFrag manually
        manager.beginTransaction().replace(R.id.converter_layout, timeFrag).commit();
        manager.beginTransaction().replace(R.id.converter_layout, lengthFrag).commit();

        currentFrag = "length";
    }

    /**
     * Switch unit fragment to length fragment.
     */
    public static void switchToLengthFrag() {
        manager.beginTransaction().replace(R.id.converter_layout, lengthFrag).commit();  //switch
        currentFrag = "length";
    }

    /**
     * Switch unit fragment to length fragment.
     */
    public static void switchToTimeFrag() {
        manager.beginTransaction().replace(R.id.converter_layout, timeFrag).commit();  //switch
        currentFrag = "time";
    }
}
