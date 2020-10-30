package com.example.supermarket.ui.recyclerview.adapter.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermarket.ui.recyclerview.adapter.AdapterShoppingList;

public class SupermarketItemTouchHelper extends ItemTouchHelper.Callback {
    private AdapterShoppingList adapter;
    public SupermarketItemTouchHelper(AdapterShoppingList adapter){
        this.adapter = adapter;
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int movimentoSwipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int movimentoDrag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(movimentoDrag, movimentoSwipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int initialPosition = viewHolder.getAdapterPosition();
        int finalPosition = viewHolder.getAdapterPosition();
        adapter.changePosition(initialPosition, finalPosition);
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.removeItem(viewHolder.getAdapterPosition());
    }
}
