package com.hello.cashregister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hello.cashregister.adapters.ProductAdapter;
import com.hello.cashregister.data.DataManager;
import com.hello.cashregister.models.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class RestockActivity extends AppCompatActivity {
    private ArrayList<Product> products;
    private Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restock);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Create product adapter and connect to list view
        ProductAdapter productAdapter = new ProductAdapter(this, DataManager.shared.getProducts());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(productAdapter);

        //Set on click listener when user taps on a row
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedProduct = (Product) productAdapter.getItem(i);

                //Get selected product text view from xml file
                TextView selectedProductTextView = findViewById(R.id.product_selected_textview);
                selectedProductTextView.setText(selectedProduct.getName());
            }
        });

        //Ok button
        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(view -> {
            EditText quantityText = findViewById(R.id.enter_qty_textview);

            if(String.valueOf(quantityText.getText()).isEmpty() || selectedProduct == null)
            {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            }
            else
            {
                int enteredQuantity = Integer.parseInt(String.valueOf(quantityText.getText()));
                int currentQuantity = selectedProduct.getQuantity();
                selectedProduct.setQuantity(enteredQuantity + currentQuantity);
                finish();
            }
        });

        // Cancel button
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(view -> {
            finish();
        });
    }
}