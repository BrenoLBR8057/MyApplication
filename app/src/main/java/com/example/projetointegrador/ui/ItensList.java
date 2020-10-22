package com.example.projetointegrador.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projetointegrador.R;
import com.example.projetointegrador.ui.RecyclerView.adapter.RecyclerViewItensList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import com.example.projetointegrador.DAO.PIDAO;
import com.example.projetointegrador.model.Model;

import static com.example.projetointegrador.ui.Interface.KEY_EDIT_ITENS;
import static com.example.projetointegrador.ui.Interface.KEY_SAVE_ITENS;
import static com.example.projetointegrador.ui.Interface.REQUEST_CODE_EDIT_ITENS;
import static com.example.projetointegrador.ui.Interface.REQUEST_CODE_SAVE_ITENS;
import static com.example.projetointegrador.ui.Interface.RETURN_CODE_SAVE_ITENS;

public class ItensList extends AppCompatActivity {
    private FloatingActionButton fabAddItem;
    private RecyclerView recyclerViewItensList;
    private RecyclerViewItensList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_list);

        recyclerViewItensList = findViewById(R.id.recyclerViewItensList);
        fabAddItem = findViewById(R.id.fabItensList);
        fabButtnClick();
        configureListView();
    }

    private void fabButtnClick(){
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItensList.this, AddItensList.class);
                startActivityForResult(intent, REQUEST_CODE_SAVE_ITENS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SAVE_ITENS && resultCode == RETURN_CODE_SAVE_ITENS && data.hasExtra(KEY_SAVE_ITENS)) {
            //salvarGastoFormulario();

            Model itens = (Model) data.getSerializableExtra(KEY_SAVE_ITENS);
            new PIDAO().insert(itens);
            adapter.notifyDataSetChanged();
        }

        else if (requestCode == REQUEST_CODE_EDIT_ITENS && resultCode == REQUEST_CODE_EDIT_ITENS && data.hasExtra(KEY_EDIT_ITENS)) {
            //salvarGastoFormulario();

            Model itens = (Model) data.getSerializableExtra(KEY_EDIT_ITENS);
            new PIDAO().edit(itens);
            adapter.notifyDataSetChanged();
        }
    }

    private void configureListView(){
        recyclerViewItensList.setLayoutManager(new LinearLayoutManager(this));

        final List<Model> itens = new PIDAO().recoverAllItensList();
        adapter = new RecyclerViewItensList(itens);
        recyclerViewItensList.setAdapter(adapter);
    }
    private void generateItens(int quantify) {
        for (int i = 0; i < quantify; i++) {
            new PIDAO().insert(new Model("Café", 3.50, 6));
        }
    }
}