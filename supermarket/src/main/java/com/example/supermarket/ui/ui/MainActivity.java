package com.example.supermarket.ui.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Supermarket.R;
import com.example.supermarket.ui.database.AppDatabase;
import com.example.supermarket.ui.recyclerview.adapter.helper.SupermarketItemTouchHelper;
import com.example.supermarket.ui.model.Supermarket;
import com.example.supermarket.ui.recyclerview.adapter.AdapterShoppingList;
import com.example.supermarket.ui.recyclerview.adapter.listener.SupermarketItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.supermarket.ui.ui.Constants.KEY_EDIT_PRODUCT;
import static com.example.supermarket.ui.ui.Constants.KEY_SAVE_PRODUCT;
import static com.example.supermarket.ui.ui.Constants.REQUEST_EDIT_PRODUCT;
import static com.example.supermarket.ui.ui.Constants.REQUEST_SAVE_PRODUCT;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    static List<Supermarket> supermarketList;
    private FloatingActionButton fabAminActivity;
    private AdapterShoppingList adapter;
    private int itemClickPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateList();
        configureRecyclerView();
        buttonClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) return;
        if (requestCode == REQUEST_SAVE_PRODUCT && data.hasExtra(KEY_SAVE_PRODUCT)) {
            if (resultCode == RESULT_OK) {
                Supermarket supermarket = (Supermarket) data.getSerializableExtra(KEY_SAVE_PRODUCT);
                AppDatabase.getInstance(getApplicationContext()).supermarketDAO().insert(supermarket);

                supermarketList.clear();
                supermarketList.addAll(AppDatabase.getInstance(getApplicationContext()).supermarketDAO().getAll());
                adapter.notifyDataSetChanged();

            }
        }else if(requestCode == REQUEST_EDIT_PRODUCT && data.hasExtra(KEY_EDIT_PRODUCT)) {
            if(resultCode == RESULT_OK){
                Supermarket supermarket = (Supermarket)data.getSerializableExtra(KEY_EDIT_PRODUCT);
                AppDatabase.getInstance(getApplicationContext()).supermarketDAO().update(supermarket);

                supermarketList.set(itemClickPosition, supermarket);
                adapter.notifyItemChanged(itemClickPosition);
            }
            }
    }

    private void configureRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewMainActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterShoppingList(getApplicationContext(), supermarketList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SupermarketItemClickListener() {
            @Override
            public void itemClick(Supermarket supermarket, int position) {
                itemClickPosition = position;
                Intent intent = new Intent(MainActivity.this, AddShoppingList.class);
                intent.putExtra(KEY_EDIT_PRODUCT, supermarket);
                startActivityForResult(intent, REQUEST_EDIT_PRODUCT);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SupermarketItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void generateList() {
        supermarketList = AppDatabase.getInstance(getApplicationContext()).supermarketDAO().getAll();
    }

    private void buttonClick() {
        fabAminActivity = findViewById(R.id.fabMainActivity);
        fabAminActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddShoppingList.class);
                startActivityForResult(intent, REQUEST_SAVE_PRODUCT);
            }
        });
    }


}