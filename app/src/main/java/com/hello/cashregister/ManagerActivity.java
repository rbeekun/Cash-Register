package com.hello.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.Objects;

public class ManagerActivity extends AppCompatActivity {
    private Serializable products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Get History and Restock buttons
        Button btnHistory = findViewById(R.id.btnHistory);
        Button btnRestock = findViewById(R.id.btnRestock);

        btnHistory.setOnClickListener(view -> {
            Intent goToHistoryIntent = new Intent(ManagerActivity.this, HistoryActivity.class);
            startActivity(goToHistoryIntent);
        });

        btnRestock.setOnClickListener(view -> {
            Intent goToRestockIntent = new Intent(ManagerActivity.this, RestockActivity.class);
            startActivity(goToRestockIntent);
        });
    }
}