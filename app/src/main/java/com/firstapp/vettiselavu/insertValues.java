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

public class insertValues extends AppCompatActivity {

    EditText amount,extra_expence, description;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_values);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DBConnection db = new DBConnection(this);

        amount = findViewById(R.id.amount1);
        extra_expence = findViewById(R.id.extra_expence1);
        description = findViewById(R.id.description);
        extra_expence.setEnabled(false);
        submit = findViewById(R.id.submit1);

        final int[] first_spinner = {1};
        final int[] second_spinner = {1};
        final int[] select_others = {0};

        final String[] mode = {""};
        final String[] mode_of = {""};
        final String[] amount_money = {""};
        final String[] reason = {""};
        final String[] reason_of = {""};
        final String[] desc = {""};

        Spinner mode_spin = (Spinner) findViewById(R.id.mode_of_transaction1);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this,R.array.Mode,android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mode_spin.setAdapter(ad);

        Spinner change = (Spinner) findViewById(R.id.expence_type1);
        ArrayAdapter<CharSequence> ad1 = ArrayAdapter.createFromResource(this,R.array.list_of_expence,android.R.layout.simple_spinner_item);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        change.setAdapter(ad1);

        mode_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                mode_of[0] = adapterView.getItemAtPosition(i).toString();
                first_spinner[0] = 1;

                if(i == 0){
                    first_spinner[0] = 0;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        change.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                reason_of[0] = adapterView.getItemAtPosition(i).toString();
                second_spinner[0] = 1;

                if(i == 0){
                    second_spinner[0] = 0;
                }


                if(i==5){
                    extra_expence.setEnabled(true);
                    select_others[0] = 1;
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

                if(amount.getText().toString().equals("")){
                    toastMessage("Please..Enter the Amount!");
                }else{
                    if(first_spinner[0] == 0){
                        toastMessage("Please..select any mode of Transaction!");
                        //first_spinner[0] = 1;
                    }else if(second_spinner[0] == 0){
                        toastMessage("Please..select Reason for transaction!");
                        //second_spinner[0] = 1;
                    }else if(select_others[0] == 1 && extra_expence.getText().toString().equals("")){
                        toastMessage("Please..fill the Custom reason field!");
                    }else{
                        mode[0] = mode_of[0];
                        amount_money[0] = amount.getText().toString();
                        reason[0] = reason_of[0];
                        desc[0] = description.getText().toString();


                        if(select_others[0] == 0) {

                            boolean result = db.insertData(mode[0], amount_money[0], reason[0], desc[0]);

                            if(result) {
                                toastMessage("Success!!!");
                            }else{
                                toastMessage("Data not inserted!");
                            }

                        }else{
                            reason[0] = extra_expence.getText().toString();

                            boolean result = db.insertData(mode[0], amount_money[0], reason[0], desc[0]);

                            if(result) {
                                toastMessage("Success!!!");
                            }else{
                                toastMessage("Data not inserted!");
                            }

                        }

                        mode[0] = "";
                        amount_money[0] = "";
                        reason[0] = "";
                        desc[0] = "";

                    }
                }

            }
        });
    }

    private void toastMessage(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }
}