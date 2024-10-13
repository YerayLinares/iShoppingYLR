package com.example.ishppinglistyeraylinares.activities;

import static com.example.ishppinglistyeraylinares.database.database.productList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishppinglistyeraylinares.R;
import com.example.ishppinglistyeraylinares.adapters.ProductAdapter;
import com.example.ishppinglistyeraylinares.models.Product;

public class ProductsDetails extends AppCompatActivity {

    private TextView tvName, tvNote, tvstate, tvid;
    private Button btnback, btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);

        btnback = findViewById(R.id.back);
        btnEdit = findViewById(R.id.edit);
        tvid = findViewById(R.id.tvIddetail);
        tvName = findViewById(R.id.tvNamedetail);
        tvNote = findViewById(R.id.tvNotesdetail);
        tvstate = findViewById(R.id.tvStatedetail);

        //aquí se muestra la información de los productos de la lista

        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("product");

        if (p != null) {
            tvid.setText(p.getId());
            tvName.setText(p.getName());
            tvNote.setText(p.getNotes());

            if (p.isState()) {
                tvstate.setText("Pendiente");
            } else {
                tvstate.setText("No pendiente");
            }
        }

        //Boton para modiifcar la información del producto seleccionado

        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(ProductsDetails.this, EditProductActivity.class);
            editIntent.putExtra("product", p);
            startActivity(editIntent);
        });

        //boton para volver atrás a la lista de pendientes
        btnback.setOnClickListener(v ->{
            Intent backIntent = new Intent(this, MainActivity.class);

            startActivity(backIntent);
        });



    }


}