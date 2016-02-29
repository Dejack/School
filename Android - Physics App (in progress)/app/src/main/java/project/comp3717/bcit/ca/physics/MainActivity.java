package project.comp3717.bcit.ca.physics;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        generateListView();
        registerListViewClick();
    }

    // Adds the text to the menu (list view)
    private void generateListView() {

        final Resources res;
        final String[] menuItems;
        final ArrayAdapter<String> adapter;
        final ListView list;

        res = getResources();
        menuItems = res.getStringArray(R.array.mainMenu);
        adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_view_item,
                menuItems);

        list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);

    }

    private void registerListViewClick() {
        final ListView list;

        list = (ListView) findViewById(R.id.listViewMain);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                final TextView textView;

                textView = (TextView) viewClicked;

                final Intent intent;
                if (textView.getText().equals("Unit Converter")) {
                    intent = new Intent(MainActivity.this, ConverterMain.class);
                    //Toast.makeText(MainActivity.this, "WOWZOR", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (textView.getText().equals("Kinematics")){
                    intent = new Intent(MainActivity.this, Kinematics.class);
                    //Toast.makeText(MainActivity.this, "WOWZOR", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
            }
        });
    }
}
