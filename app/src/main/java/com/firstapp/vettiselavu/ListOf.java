package com.firstapp.vettiselavu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListOf extends AppCompatActivity {

    BottomNavigationView navigationView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        navigationView1 = findViewById(R.id.bottomNavigationView2);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.todayLiFragment,
                R.id.weekLiFragment,R.id.monthLiFragment).build();
        NavController navController = Navigation.findNavController(this,R.id.fragmentContainerView2);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView1,navController);
    }
}