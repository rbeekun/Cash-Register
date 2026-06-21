package com.hello.cashregister.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hello.cashregister.HistoryDetailActivity;
import com.hello.cashregister.R;
import com.hello.cashregister.models.PurchaseHistory;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>
{
    private Context context;
    private ArrayList<PurchaseHistory> history;

    public HistoryAdapter(Context context, ArrayList<PurchaseHistory> history)
    {
        this.context = context;
        this.history = history;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.productview_row, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position)
    {
        //Get product purchased at position
        PurchaseHistory purchase = this.history.get(position);

        //Set text to each text views
        holder.productName.setText(purchase.getName());
        holder.productQuantity.setText(String.valueOf(purchase.getQuantity()));
        holder.productTotal.setText(String.valueOf(purchase.getTotal()));
    }

    @Override
    public int getItemCount() { return history.size(); }

    public class HistoryViewHolder extends RecyclerView.ViewHolder
   {
       private final TextView productName;
       private final TextView productQuantity;
       private final TextView productTotal;

       public HistoryViewHolder(View itemView)
       {
           super(itemView);
           productName = itemView.findViewById(R.id.product_name_textView);
           productQuantity = itemView.findViewById(R.id.qty_value_textView);
           productTotal = itemView.findViewById(R.id.price_textView);

           itemView.setOnClickListener(view -> {
               Intent goToHistoryDetailIntent = new Intent(context, HistoryDetailActivity.class);
               int position = getAbsoluteAdapterPosition();
               goToHistoryDetailIntent.putExtra("position", position);

               ((Activity) context).startActivity(goToHistoryDetailIntent);
           });
       }

       //Getters
       public TextView getProductName() { return productName; }
       public TextView getProductQuantity() { return productQuantity;}
       public TextView getProductTotal() {return productTotal;}
   }
}
