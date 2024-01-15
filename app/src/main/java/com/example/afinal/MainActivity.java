package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public EditText editName, editN1, editN2;
    TextView nameResult, sumResult;

    float n1, n2;
    String nameVal;

    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    HashMap<String,String> item;

    public void getVals(){
        editName = (EditText) findViewById(R.id.nameEdit);
        editN1 = (EditText) findViewById(R.id.num1);
        editN2 = (EditText) findViewById(R.id.num2);

        nameResult = (TextView) findViewById(R.id.nameView);
        sumResult = (TextView) findViewById(R.id.sumView);

        nameVal = editName.getText().toString();
        String n1s = editN1.getText().toString();
        String n2s = editN2.getText().toString();

        if (n1s.equals("") || n2s.equals("")){
            n1 = 0; n2 = 0;
            return;
        }
        n1 = Float.parseFloat(n1s);
        n2 = Float.parseFloat(n2s);
    }

    public void applyWizardry(View v){
        getVals();

        nameResult.setText(nameVal);
        float sum = n1+n2;
        sumResult.setText(String.format(Float.toString(sum)));

        item = new HashMap<String,String>();
        item.put( "name", nameVal );
        item.put( "sum", Float.toString(sum) );
        item.put( "num1", Float.toString(n1) );
        item.put( "num2", Float.toString(n2) );
        list.add(item);
    }

    public void moveToList(View v){
        Intent intent = new Intent(getApplicationContext(),ListActivity.class);

        intent.putExtra("list",list);

        startActivity(intent);
    }

    public void clearList(View v){
        int deletedEntries = list.size();
        list.clear();

        Toast.makeText(getApplicationContext(),
                String.format(Locale.US,"List cleared, deleted %d elements.",deletedEntries),
                Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}