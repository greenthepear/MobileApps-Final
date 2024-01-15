package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    private SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();

        ArrayList<HashMap<String, String>> raw = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("list");

        if (raw == null) return;
        Log.v("print-debug",raw.toString());

        String[] from = {"name", "sum"};
        int[] to = {R.id.nameTextView, R.id.sumTextView};
        SimpleAdapter sa = new SimpleAdapter(this, raw, R.layout.list_row_items,from,to);

        ListView listItem = findViewById(R.id.listItem);
        listItem.setAdapter(sa);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> selectedItem = raw.get(position);

                // Extract "Name" and "Sum" values
                String name = selectedItem.get("name").toString();
                String sum = selectedItem.get("sum").toString();
                String n1 = selectedItem.get("num1").toString();
                String n2 = selectedItem.get("num2").toString();

                // Display a Toast with the extracted values
                String toastMessage = String.format("%s: %s + %s = %s",name,n1,n2,sum);
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}