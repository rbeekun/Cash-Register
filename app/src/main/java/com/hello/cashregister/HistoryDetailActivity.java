package com.hello.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hello.cashregister.data.DataManager;
import com.hello.cashregister.models.PurchaseHistory;

import org.w3c.dom.Text;

public class HistoryDetailActivity extends AppCompatActivity {
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Set Toolbar
        Toolbar toolbar = findViewById(R.id.historyToolbar);
        setSupportActionBar(toolbar);

        //Add back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Assignment_2");
        }

        //Get position from intent
        position = getIntent().getIntExtra("position", 0);

        //Get product purchased at position
        PurchaseHistory purchase = DataManager.shared.getPurchase(position);

        //Get text views from XML file
        TextView productName = findViewById(R.id.productPurchase_name);
        TextView quantity = findViewById(R.id.quantityPurchased);
        TextView total = findViewById(R.id.total_ActivityHistoryDetail_xml);
        TextView date = findViewById(R.id.date_purchased_ActivityHistoryDetail_xml);

        //Set TextViews to display data in purchase object
        productName.setText(purchase.getName());
        quantity.setText(String.valueOf(purchase.getQuantity()));
        total.setText(String.valueOf(purchase.getTotal()));
        date.setText(String.valueOf(purchase.getPurchaseDate()));
    }
}