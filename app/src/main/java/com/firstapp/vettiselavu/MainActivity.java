package com.firstapp.vettiselavu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText amount,extra_expence;
    Button submit,list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        amount = findViewById(R.id.amount1);
        extra_expence = findViewById(R.id.extra_expence1);
        extra_expence.setEnabled(false);
        submit = findViewById(R.id.submit1);
        list = findViewById(R.id.submit2);

        Spinner mode_spin = (Spinner) findViewById(R.id.mode_of_transaction1);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this,R.array.Mode,android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mode_spin.setAdapter(ad);

        Spinner change = (Spinner) findViewById(R.id.expence_type1);
        ArrayAdapter<CharSequence> ad1 = ArrayAdapter.createFromResource(this,R.array.list_of_expence,android.R.layout.simple_spinner_item);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        change.setAdapter(ad1);

        change.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==5){
                    extra_expence.setEnabled(true);
                }else{
                    extra_expence.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Mathankumar R", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),pieChart.class));
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Mathankumar R", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),ListOf.class));
            }
        });
    }
}