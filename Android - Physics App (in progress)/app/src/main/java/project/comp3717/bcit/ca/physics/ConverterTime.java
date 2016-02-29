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
 * Class for the time units conversion fragment.
 */
public class ConverterTime extends Fragment{

    //all time units
    private static TextView msec, sec, min, hour, day, week, month, year;

    //maximum number length
    private static final int MAX_LENGTH = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.converter_time, container, false);  //assign converter_time layout to this class

        //get corresponded unit rows
        msec = (TextView)view.findViewById(R.id.TimeNum0);
        sec = (TextView)view.findViewById(R.id.TimeNum1);
        min = (TextView)view.findViewById(R.id.TimeNum2);
        hour = (TextView)view.findViewById(R.id.TimeNum3);
        day = (TextView)view.findViewById(R.id.TimeNum4);
        week = (TextView)view.findViewById(R.id.TimeNum5);
        month = (TextView)view.findViewById(R.id.TimeNum6);
        year = (TextView)view.findViewById(R.id.TimeNum7);

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
        msec.setText("0");
        sec.setText("0");
        min.setText("0");
        hour.setText("0");
        day.setText("0");
        week.setText("0");
        month.setText("0");
        year.setText("0");
    }

    /**
     * Convert and update conversion table
     * @param userNum user input
     * @param userUnit user chosen unit
     */
    public static void update(double userNum, String userUnit) {
        //detect user chosen unit as the base unit
        switch(userUnit) {
            case "msec":
                msec.setText(formatNum(userNum));
                sec.setText(formatNum(userNum / 1000));
                min.setText(formatNum(userNum / 60000));
                hour.setText(formatNum(userNum / 3600000));
                day.setText(formatNum(userNum / 86400000));
                week.setText(formatNum(userNum / 604800000));
                month.setText(formatNum(userNum / 2628E+6));
                year.setText(formatNum(userNum / 3.1540E+10));
                break;

            case "sec":
                msec.setText(formatNum(userNum * 1000));
                sec.setText(formatNum(userNum));
                min.setText(formatNum(userNum / 60));
                hour.setText(formatNum(userNum / 3600));
                day.setText(formatNum(userNum / 86400));
                week.setText(formatNum(userNum / 604800));
                month.setText(formatNum(userNum / 2628000));
                year.setText(formatNum(userNum / 31536000));
                break;

            case "min":
                msec.setText(formatNum(userNum * 60000));
                sec.setText(formatNum(userNum * 60));
                min.setText(formatNum(userNum));
                hour.setText(formatNum(userNum / 60));
                day.setText(formatNum(userNum / 1440));
                week.setText(formatNum(userNum / 10080));
                month.setText(formatNum(userNum / 43800));
                year.setText(formatNum(userNum / 525600));
                break;

            case "hour":
                msec.setText(formatNum(userNum * 3600000));
                sec.setText(formatNum(userNum * 3600));
                min.setText(formatNum(userNum * 60));
                hour.setText(formatNum(userNum));
                day.setText(formatNum(userNum / 24));
                week.setText(formatNum(userNum / 168));
                month.setText(formatNum(userNum / 730));
                year.setText(formatNum(userNum / 8760));
                break;

            case "day":
                msec.setText(formatNum(userNum * 86400000));
                sec.setText(formatNum(userNum * 86400));
                min.setText(formatNum(userNum * 1440));
                hour.setText(formatNum(userNum * 24));
                day.setText(formatNum(userNum));
                week.setText(formatNum(userNum / 7));
                month.setText(formatNum(userNum / 30.416667));
                year.setText(formatNum(userNum / 365));
                break;

            case "week":
                msec.setText(formatNum(userNum * 604800000));
                sec.setText(formatNum(userNum * 604800));
                min.setText(formatNum(userNum * 10080));
                hour.setText(formatNum(userNum * 168));
                day.setText(formatNum(userNum * 7));
                week.setText(formatNum(userNum));
                month.setText(formatNum(userNum / 4.345238));
                year.setText(formatNum(userNum / 52.142857));
                break;

            case "month":
                msec.setText(formatNum(userNum * 2628E+6));
                sec.setText(formatNum(userNum * 2628000));
                min.setText(formatNum(userNum * 43800));
                hour.setText(formatNum(userNum * 730));
                day.setText(formatNum(userNum * 30.416667));
                week.setText(formatNum(userNum * 4.345238));
                month.setText(formatNum(userNum));
                year.setText(formatNum(userNum / 12));
                break;

            case "year":
                msec.setText(formatNum(userNum * 3.1540E+10));
                sec.setText(formatNum(userNum * 31536000));
                min.setText(formatNum(userNum * 525600));
                hour.setText(formatNum(userNum * 8760));
                day.setText(formatNum(userNum * 365));
                week.setText(formatNum(userNum * 52.142857));
                month.setText(formatNum(userNum * 12));
                year.setText(formatNum(userNum));
                break;
        }
    }
}
