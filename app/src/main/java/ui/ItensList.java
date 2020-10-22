package ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.projetointegrador.ui.AddItensList;
import com.example.projetointegrador.ui.RecyclerView.adapter.RecyclerViewItensList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import DAO.PIDAO;
import model.Model;

import static com.example.projetointegrador.ui.Interface.KEY_EDIT_ITENS;
import static com.example.projetointegrador.ui.Interface.KEY_SAVE_ITENS;
import static com.example.projetointegrador.ui.Interface.REQUEST_CODE_EDIT_ITENS;
import static com.example.projetointegrador.ui.Interface.REQUEST_CODE_SAVE_ITENS;
import static com.example.projetointegrador.ui.Interface.RETURN_CODE_SAVE_ITENS;

public class ItensList extends AppCompatActivity {
    private FloatingActionButton fabAddItem;
    private RecyclerView recyclerViewItensList;
    private RecyclerViewItensList adapter;
    private Model itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_list);

        recyclerViewItensList = findViewById(R.id.recyclerViewItensList);
        fabAddItem = findViewById(R.id.fabItensList);
        fabButtnClick();
        configureListView();
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setOnItemLongClickListener(new RecyclerViewItensList.MyLongClickListener() {
            @Override
            public boolean onItemLongClick(int position, View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ItensList.this);
                builder.setMessage("Tem certeza de que deseja excluir ?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new PIDAO().remove(position);
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                return false;
            }
        });
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