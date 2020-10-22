package com.example.projetointegrador.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetointegrador.R;
import com.example.projetointegrador.ui.RecyclerView.adapter.RecyclerViewItensList;

import com.example.projetointegrador.DAO.PIDAO;
import com.example.projetointegrador.model.Model;

import static com.example.projetointegrador.ui.Interface.KEY_SAVE_ITENS;
import static com.example.projetointegrador.ui.Interface.RETURN_CODE_SAVE_ITENS;

public class AddItensList extends AppCompatActivity {
    private TextView product;
    private TextView quantify;
    private TextView price;
    private Button apply;
    private Model itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_itens_list);

        loadfields();
        buttonClick();
    }

    private void loadfields(){
        product = findViewById(R.id.textViewProduct);
        quantify = findViewById(R.id.textViewQuantify);
        price = findViewById(R.id.textViewPrice);
        apply = findViewById(R.id.buttonApply);
    }

    private void buttonClick(){
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model updatedItens = getItens();
                Intent intent = new Intent(AddItensList.this, ItensList.class);
                updatedItens.setId(itens.getId());
                intent.putExtra(KEY_SAVE_ITENS, updatedItens);
                setResult(RETURN_CODE_SAVE_ITENS, intent);
                new PIDAO().insert(itens);
                startActivity(intent);
                finish();
            }
        });
    }

    private Model getItens(){
        String product = this.product.getText().toString();
        int quantify = Integer.parseInt(this.quantify.getText().toString());
        Double price = Double.parseDouble(this.price.getText().toString());

        itens = new Model(product, price, quantify);
        return itens;
    }
}