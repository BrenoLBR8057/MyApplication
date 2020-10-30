package com.example.supermarket.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Supermarket.R;
import com.example.supermarket.ui.database.AppDatabase;
import com.example.supermarket.ui.model.Supermarket;
import com.example.supermarket.ui.recyclerview.adapter.listener.SupermarketItemClickListener;

import java.util.Collections;
import java.util.List;

public class AdapterShoppingList extends RecyclerView.Adapter<AdapterShoppingList.ViewHolder> {
    private List<Supermarket> supermarketList;
    private Context context;
    private SupermarketItemClickListener onItemClickListener;
    private Supermarket supermarket;

    public AdapterShoppingList(Context context, List<Supermarket> supermarketList){
        this.context = context;
        this.supermarketList = supermarketList;
    }
    public void setOnItemClickListener(SupermarketItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public AdapterShoppingList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_shopping_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShoppingList.ViewHolder holder, int position) {
        Supermarket supermarket = supermarketList.get(position);
        holder.link(supermarket);
    }

    @Override
    public int getItemCount() {
        return supermarketList.size();
    }

    public void removeItem(int position){
        AppDatabase.getInstance(context).supermarketDAO().delete(supermarketList.get(position));
        supermarketList.remove(position);
        notifyItemRemoved(position);
    }

    public void changePosition(int initialPosition, int finalPosition){
        Collections.swap(supermarketList, initialPosition, finalPosition);
        notifyItemMoved(initialPosition, finalPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textProduct;
        private TextView textTotal;
        private TextView textQuantify;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textQuantify = itemView.findViewById(R.id.textQuantify);
            textProduct = itemView.findViewById(R.id.textProduct);
            textTotal = itemView.findViewById(R.id.textTotal);
        }


        public void link(Supermarket supermarket) {
            textTotal.setText(supermarket.getTotalAsString());
            textProduct.setText(supermarket.getProduct());
            textQuantify.setText(supermarket.getQuantifyAsString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    onItemClickListener.itemClick(supermarketList.get(getAdapterPosition()),position);
                }
            });
        }
    }
}
