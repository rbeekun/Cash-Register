package com.hello.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.hello.cashregister.models.PurchaseHistory;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

//    private ArrayList<Product> products = new ArrayList<>();
    private List<Button> buttons = new ArrayList<>();
    private String quantity = "";
    private TextView quantityTextView;
    private TextView selectedProductTextView;
    private TextView totalTextView;
    private Product selectedProduct;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create 4 Products object
        Product pants = new Product("pants", 10, 10.50);
        Product shoes = new Product("shoes", 15, 23.45);
        Product hats  = new Product("hats", 4, 20.45);
        Product shirts = new Product("shirts", 23, 30.00);

        //Create dataManager products list and history list
        DataManager.shared.setProducts(new ArrayList<>());
        DataManager.shared.setPurchaseHistory(new ArrayList<>());

        // Add each product object to DataManager
        DataManager.shared.addProduct(pants);
        DataManager.shared.addProduct(shoes);
        DataManager.shared.addProduct(hats);
        DataManager.shared.addProduct(shirts);


        // Create ProductAdapter and connect to listView
        productAdapter = new ProductAdapter(this, DataManager.shared.getProducts());
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(productAdapter);

        //Set onClickListener when user taps on product row
        //Get current product selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get product ohject when row is clicked
                Product product = (Product)productAdapter.getItem(i);

                //Set Product tapped to selected product
                selectedProduct = product;

                // Get textView from XML file
                TextView product_name = findViewById(R.id.selected_product_text);

                //Set textViews with values
                product_name.setText(product.getName());

            }
        });

        quantityTextView = findViewById(R.id.total_qty_text);
        selectedProductTextView = findViewById(R.id.selected_product_text);
        totalTextView = findViewById(R.id.total_textView);

        // Set number buttons to update quantity field
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        buttons.add(btn0);
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);

        for(Button button : buttons)
        {
            button.setOnClickListener(view -> {
                //Get number from button, convert to string and add to textView
                String num = (String) button.getText();
                quantity = quantity + num;
                quantityTextView.setText(quantity);

                double total;

                if(selectedProduct != null)
                {
                    total = selectedProduct.getPrice() * Integer.parseInt(quantity);
                    totalTextView.setText(String.valueOf(total));

                }
                else
                {
                    total = 0.0;
                    totalTextView.setText(String.valueOf(total));
                }
            });
        }

        //Clear button
        Button btnClear = findViewById(R.id.btnClr);
        btnClear.setOnClickListener(view -> {
            quantity = "";
            quantityTextView.setText(quantity);
        });

        //Buy button
        Button btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(view -> {
            // Display error message if no product selected or no quantity entered
            if((selectedProduct == null) || Objects.equals(quantity,""))
            {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //Convert quantity from String to int & get current quantity for selected product
                int quantityEntered = Integer.parseInt(quantity);
                int currentQuantity = selectedProduct.getQuantity();

                //Display error message if quantity entered is more than current quantity
                if(quantityEntered > currentQuantity)
                {
                    Toast.makeText(this, "Not enough stock", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    double total = quantityEntered * selectedProduct.getPrice();
                    TextView totalTextView = findViewById(R.id.total_textView);
                    totalTextView.setText(String.valueOf(total));

                    //Update selectedProductQuantity
                    int newQuantity = currentQuantity - quantityEntered;
                    selectedProduct.setQuantity(newQuantity);

                    //Display confirmation toast
                    Toast.makeText(this, "Your purchase is " + quantityEntered
                                                        +
                                                        " " + selectedProduct.getName()
                                                        +
                                                        " for $" + total, Toast.LENGTH_SHORT).show();

                    //Add purchase to purchase history
                    PurchaseHistory purchase = new PurchaseHistory(selectedProduct.getName(), quantityEntered, total, LocalDateTime.now());
                    DataManager.shared.addPurchase(purchase);

                    //Reset UI
                    selectedProductTextView.setText("");
                    quantityTextView.setText("");
                    totalTextView.setText("");
                    quantity = "";
                    selectedProduct = null;

                    //Refresh listView
                    productAdapter.notifyDataSetChanged();
                }
            }
        });

        // Manager button
        Button btnManager = findViewById(R.id.btnManager);
        btnManager.setOnClickListener(view -> {
            Intent goToManagerIntent = new Intent(MainActivity.this, ManagerActivity.class);
            startActivity(goToManagerIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        productAdapter.notifyDataSetChanged();
    }
}