package com.example.ishppinglistyeraylinares.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishppinglistyeraylinares.R;
import com.example.ishppinglistyeraylinares.database.database;
import com.example.ishppinglistyeraylinares.models.Product;

import java.util.ArrayList;
import java.util.List;

public class NoPendingProductList extends AppCompatActivity {

    private Spinner spinnerNoPendingProducts;
    private Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_pending_product_list);

        spinnerNoPendingProducts = findViewById(R.id.spinnerNoPendingProducts);
        btnback = findViewById(R.id.NoPendingBack);

        // Lista de productos no pendientes
        List<Product> noPendingProducts = database.noPendingProductList;

        // Crear una lista de nombres de productos no pendientes
        List<String> productNames = new ArrayList<>();
        for (Product product : noPendingProducts) {
            productNames.add(product.getName());
        }

        // Configurar el adaptador para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNoPendingProducts.setAdapter(adapter);


        //boton para volver a la lista de pendientes
        btnback.setOnClickListener(v -> {
            Intent intent = new Intent(NoPendingProductList.this, MainActivity.class);

            startActivity(intent);
        });
    }
}
