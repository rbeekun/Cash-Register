package com.hello.cashregister.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hello.cashregister.R;
import com.hello.cashregister.data.DataManager;
import com.hello.cashregister.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    ArrayList<Product> products;
    Context context;

    public ProductAdapter(Context context, ArrayList<Product> products)
    {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Get product at position i
        Product product = products.get(i);

        //Inflate productrow_view
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.productview_row, viewGroup, false);

        //Get text views from product row view
        TextView productName = view.findViewById(R.id.product_name_textView);
        TextView productQuantity = view.findViewById(R.id.qty_value_textView);
        TextView productPrice = view.findViewById(R.id.price_textView);

        //Setting text on each textview
        productName.setText(product.getName());
        productQuantity.setText(String.valueOf(product.getQuantity()));
        productPrice.setText(String.valueOf(product.getPrice()));
        return view;
    }
}
