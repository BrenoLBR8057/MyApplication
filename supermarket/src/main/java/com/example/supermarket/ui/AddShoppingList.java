package com.example.supermarket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Supermarket.R;
import com.example.supermarket.ui.model.Supermarket;

public class AddShoppingList extends AppCompatActivity {
    private EditText editTextProduct;
    private EditText editTextQuantify;
    private EditText editTextPrice;
    private boolean isEditForm;
    private Supermarket supermarket;
    private Button btnSave;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_list);

        loadViewss();
        buttonClick();

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_EDIT_PRODUCT)){
            isEditForm = true;
            supermarket = (Supermarket) intent.getSerializableExtra(Constants.KEY_EDIT_PRODUCT);
            dataLoadingForm();
        }
    }

    private void buttonClick() {
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPrice.getText().toString().isEmpty() | editTextQuantify.getText().toString().isEmpty() | editTextProduct.getText().toString().isEmpty()) {
                    btnSave.setError("");
                    return;
                }
                if (isEditForm){
                    updateList();
                    Intent intent = new Intent(AddShoppingList.this, MainActivity.class);
                    intent.putExtra(Constants.KEY_EDIT_PRODUCT, supermarket);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    supermarket = getSupermarket();
                    Intent intent = new Intent(AddShoppingList.this, MainActivity.class);
                    intent.putExtra(Constants.KEY_SAVE_PRODUCT, supermarket);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void updateList() {
        String product = editTextProduct.getText().toString();
        double price = Double.parseDouble(editTextPrice.getText().toString());
        int quantify = Integer.parseInt(editTextQuantify.getText().toString());

        total = price * quantify;

        supermarket.setPrice(price);
        supermarket.setProduct(product);
        supermarket.setQuantify(quantify);
        supermarket.setTotal(total);
    }

    private Supermarket getSupermarket(){
        String product = editTextProduct.getText().toString();
        int quantify = Integer.parseInt(editTextQuantify.getText().toString());
        double price = Double.parseDouble(editTextPrice.getText().toString());

        total = price * quantify;
        return new Supermarket(product, quantify, price, total);
    }

    private void loadViewss() {
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextProduct = findViewById(R.id.editTextProduct);
        editTextQuantify = findViewById(R.id.editTextQuantify);
    }

    private void dataLoadingForm() {
        editTextQuantify.setText(supermarket.getQuantifyAsString());
        editTextProduct.setText(supermarket.getProduct());
        editTextPrice.setText(supermarket.getPriceAsString());
    }
}