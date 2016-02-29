package project.comp3717.bcit.ca.physics;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author: Jeremy Yang
 * Last Update: Feb. 27, 2016
 *
 * Class for the length units conversion fragment.
 */
public class ConverterLength extends Fragment {

    //all length units
    private static TextView µmUnit, mmUnit, cmUnit, dmUnit, mUnit, kmUnit, inchUnit, ftUnit, ftIUnit, ydUnit, mileUnit;

    //maximum number length
    private static final int MAX_LENGTH = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.converter_length, container, false);  //assign converter_length layout to this class

        //get corresponded unit rows
        µmUnit = (TextView)view.findViewById(R.id.LengthNum0);
        mmUnit = (TextView)view.findViewById(R.id.LengthNum1);
        cmUnit = (TextView)view.findViewById(R.id.LengthNum2);
        dmUnit = (TextView)view.findViewById(R.id.LengthNum3);
        mUnit = (TextView)view.findViewById(R.id.LengthNum4);
        kmUnit = (TextView)view.findViewById(R.id.LengthNum5);
        inchUnit = (TextView)view.findViewById(R.id.LengthNum6);
        ftUnit = (TextView)view.findViewById(R.id.LengthNum7);
        ftIUnit = (TextView)view.findViewById(R.id.LengthNum8);
        ydUnit = (TextView)view.findViewById(R.id.LengthNum9);
        mileUnit = (TextView)view.findViewById(R.id.LengthNum10);

        return view;
    }

    /**
     * Format user input.
     * @param number user input
     * @return formatted string
     */
    private static String formatNum(double number) {
        String output = null;
        for (int i = 0; i < MAX_LENGTH; i++) {
            String format = "%." + i + "G";
            output = String.format(format, number);
            if (output.length() == MAX_LENGTH) {
                //remove all trailing zeroes
                if(output.indexOf(".") >= 0) {
                    output = output.replaceAll("0*$", "").replaceAll("\\.$", "");
                }
                return output;
            }
        }

        //remove all trailing zeroes
        if(output.indexOf(".") >= 0) {
            output = output.replaceAll("0*$", "").replaceAll("\\.$", "");
        }

        return output;
    }

    /**
     * Set all units to 0.
     */
    public static void defaultValue() {
        µmUnit.setText("0");
        mmUnit.setText("0");
        cmUnit.setText("0");
        dmUnit.setText("0");
        mUnit.setText("0");
        kmUnit.setText("0");
        inchUnit.setText("0");
        ftUnit.setText("0");
        ftIUnit.setText("0");
        ydUnit.setText("0");
        mileUnit.setText("0");
    }

    /**
     * Convert and update conversion table
     * @param userNum user input
     * @param userUnit user chosen unit
     */
    public static void update(double userNum, String userUnit) {
        //detect user chosen unit as the base unit
        switch(userUnit) {
            case "µm":
                µmUnit.setText(formatNum(userNum));
                mmUnit.setText(formatNum(userNum / 1000));
                cmUnit.setText(formatNum(userNum / 10000));
                dmUnit.setText(formatNum(userNum / 100000));
                mUnit.setText(formatNum(userNum / 1000000));
                kmUnit.setText(formatNum(userNum / 1000000000));
                inchUnit.setText(formatNum(userNum / 25400));
                ftUnit.setText(formatNum(userNum / 304800));
                ftIUnit.setText("WIP");
                ydUnit.setText(formatNum(userNum / 914400));
                mileUnit.setText(formatNum(userNum / 1609344000));
                break;

            case "mm":
                µmUnit.setText(formatNum(userNum * 1000));
                mmUnit.setText(formatNum(userNum));
                cmUnit.setText(formatNum(userNum / 10));
                dmUnit.setText(formatNum(userNum / 100));
                mUnit.setText(formatNum(userNum / 1000));
                kmUnit.setText(formatNum(userNum / 1000000));
                inchUnit.setText(formatNum(userNum / 25.4));
                ftUnit.setText(formatNum(userNum / 304.8));
                ftIUnit.setText("WIP");
                ydUnit.setText(formatNum(userNum / 914.4));
                mileUnit.setText(formatNum(userNum / 1609344));
                break;

            case "cm":
                µmUnit.setText(formatNum(userNum * 10000));
                mmUnit.setText(formatNum(userNum * 10));
                cmUnit.setText(formatNum(userNum));
                dmUnit.setText(formatNum(userNum / 10));
                mUnit.setText(formatNum(userNum / 100));
                kmUnit.setText(formatNum(userNum / 100000));
                inchUnit.setText(formatNum(userNum / 2.54));
                ftUnit.setText(formatNum(userNum / 30.48));
                ftIUnit.setText("WIP");
                ydUnit.setText(formatNum(userNum / 91.44));
                mileUnit.setText(formatNum(userNum / 160934.4));
                break;

            case "dm":
                µmUnit.setText(formatNum(userNum * 100000));
                mmUnit.setText(formatNum(userNum * 100));
                cmUnit.setText(formatNum(userNum * 10));
                dmUnit.setText(formatNum(userNum));
                mUnit.setText(formatNum(userNum / 10));
                kmUnit.setText(formatNum(userNum / 10000));
                inchUnit.setText(formatNum(userNum * 3.93700787));
                ftUnit.setText(formatNum(userNum / 3.048));
                ftIUnit.setText("WIP");
                ydUnit.setText(formatNum(userNum / 9.144));
                mileUnit.setText(formatNum(userNum / 16093.44));
                break;

            case "m":
                µmUnit.setText(formatNum(userNum * 1000000));
                mmUnit.setText(formatNum(userNum * 1000));
                cmUnit.setText(formatNum(userNum * 100));
                dmUnit.setText(formatNum(userNum * 10));
                mUnit.setText(formatNum(userNum));
                kmUnit.setText(formatNum(userNum / 1000));
                inchUnit.setText(formatNum(userNum * 39.3700787));
                ftUnit.setText(formatNum(userNum * 3.28084));
                ftIUnit.setText("WIP");
                ydUnit.setText(formatNum(userNum * 1.093613));
                mileUnit.setText(formatNum(userNum / 1609.344));
                break;

            case "km":
                µmUnit.setText(formatNum(userNum * 1000000000));
                mmUnit.setText(formatNum(userNum * 1000000));
                cmUnit.setText(formatNum(userNum * 100000));
                dmUnit.setText(formatNum(userNum * 10000));
                mUnit.setText(formatNum(userNum * 1000));
                kmUnit.setText(formatNum(userNum));
                inchUnit.setText(formatNum(userNum * 39370.0787));
                ftUnit.setText(formatNum(userNum * 3280.8399));
                ftIUnit.setText("WIP");
                ydUnit.setText(formatNum(userNum * 1093.6133));
                mileUnit.setText(formatNum(userNum / 1.609344));
                break;

            case "inch":
                µmUnit.setText(formatNum(userNum * 25400));
                mmUnit.setText(formatNum(userNum * 25.4));
                cmUnit.setText(formatNum(userNum * 2.54));
                dmUnit.setText(formatNum(userNum / 0.328084));
                mUnit.setText(formatNum(userNum / 39.370079));
                kmUnit.setText(formatNum(userNum / 39370.0787));
                inchUnit.setText(formatNum(userNum));
                ftUnit.setText(formatNum(userNum / 12));
                ftIUnit.setText("WIP");
                ydUnit.setText(formatNum(userNum / 36));
                mileUnit.setText(formatNum(userNum / 63360));
                break;

            case "ft":
                µmUnit.setText(formatNum(userNum * 304800));
                mmUnit.setText(formatNum(userNum * 304.8));
                cmUnit.setText(formatNum(userNum * 30.48));
                dmUnit.setText(formatNum(userNum * 3.048));
                mUnit.setText(formatNum(userNum / 3.28084));
                kmUnit.setText(formatNum(userNum / 3280.8399));
                inchUnit.setText(formatNum(userNum * 12));
                ftUnit.setText(formatNum(userNum));
                ftIUnit.setText(formatNum(userNum) + "\'" + " 0\"");
                ydUnit.setText(formatNum(userNum / 3));
                mileUnit.setText(formatNum(userNum / 5280));
                break;

            case "yd":
                µmUnit.setText(formatNum(userNum * 914400));
                mmUnit.setText(formatNum(userNum * 914.4));
                cmUnit.setText(formatNum(userNum * 91.44));
                dmUnit.setText(formatNum(userNum * 9.144));
                mUnit.setText(formatNum(userNum / 1.093613));
                kmUnit.setText(formatNum(userNum / 1093.6133));
                inchUnit.setText(formatNum(userNum * 36));
                ftUnit.setText(formatNum(userNum * 3));
                ftIUnit.setText(formatNum(userNum * 3) + "\'" + " 0\"");
                ydUnit.setText(formatNum(userNum));
                mileUnit.setText(formatNum(userNum / 1760));
                break;

            case "mile":
                µmUnit.setText(formatNum(userNum * 1609344000));
                mmUnit.setText(formatNum(userNum * 1609344));
                cmUnit.setText(formatNum(userNum * 160934.4));
                dmUnit.setText(formatNum(userNum * 16093.44));
                mUnit.setText(formatNum(userNum * 1609.344));
                kmUnit.setText(formatNum(userNum * 1.609344));
                inchUnit.setText(formatNum(userNum * 63360));
                ftUnit.setText(formatNum(userNum * 5280));
                ftIUnit.setText(formatNum(userNum * 5280) + "\'" + " 0\"");
                ydUnit.setText(formatNum(userNum * 1760));
                mileUnit.setText(formatNum(userNum));
                break;
        }
    }
}