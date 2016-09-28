package com.demos.contextmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView lv_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_country = (ListView) findViewById(R.id.lv_country);

    }

    @Override
    protected void onResume() {

        String[] countries = getResources().getStringArray(R.array.countries);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);

        lv_country.setAdapter(adapter);

        registerForContextMenu(lv_country);

        lv_country.setOnItemClickListener(onCountryClicked);

        super.onResume();
    }

    private AdapterView.OnItemClickListener onCountryClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            // Do something
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_add_country:
                // Do something...
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){

            case R.id.lv_country:

                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) menuInfo;
                String country = ((TextView) info.targetView).getText().toString();
                menu.setHeaderTitle(country);

                String [] actions = getResources().getStringArray(R.array.context_menu);
                for (int i=0;i<actions.length;i++){
                    menu.add(Menu.NONE, i, i, actions[i]);
                }
                break;

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuItemIndex = item.getItemId();
        String [] menuItems = getResources().getStringArray(R.array.context_menu);
        String menuItemName = menuItems[menuItemIndex];

        switch (menuItemName) {

            case "Edit":
                // Do something...
                break;

            case "Delete":
                // Do something...
                break;

        }

        return true;
    }

}
