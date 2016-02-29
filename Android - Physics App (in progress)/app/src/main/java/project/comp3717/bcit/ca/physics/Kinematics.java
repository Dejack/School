package project.comp3717.bcit.ca.physics;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tyler on 2/18/2016.
 */
public class Kinematics extends AppCompatActivity {
    static FragmentManager manager;
    static String currentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kinematics);
/*
       manager = getFragmentManager();

        Fragment newFrag = ConverterLength.newInstance();
        manager.beginTransaction().add(R.id.converter_layout, newFrag).commit();
        currentFrag = "length";*/
    }

}
