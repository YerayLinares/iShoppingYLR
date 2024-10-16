package com.example.ishppinglistyeraylinares.activities;

import static com.example.ishppinglistyeraylinares.database.database.inicializeList;
import static com.example.ishppinglistyeraylinares.database.database.productList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishppinglistyeraylinares.R;
import com.example.ishppinglistyeraylinares.adapters.ProductAdapter;
import com.example.ishppinglistyeraylinares.database.database;
import com.example.ishppinglistyeraylinares.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProducts;
    private TextView tvSystem;
    private Button btnaddproduct, btnNoPending;
    private ProductAdapter adapter;
    private List<Product> pendingProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvProducts = findViewById(R.id.lvProduct);
        tvSystem = findViewById(R.id.tvSystem);
        btnaddproduct = findViewById(R.id.btnAdd);
        btnNoPending = findViewById(R.id.btnNoPending);

        inicializeList();

        initializePendingProductList();

        //lista de los productos que están pendientes por comprar
        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            Product p = (Product) adapter.getItem(position);



            Intent detailIntent = new Intent(MainActivity.this, ProductsDetails.class);
            detailIntent.putExtra("product", p);
            startActivity(detailIntent);




        });

        //boton para poder añadir productos
        btnaddproduct.setOnClickListener(V -> {
            Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
            startActivity(intent);
        });

        //boton para ver la lista de productos que no están pendientes
        btnNoPending.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NoPendingProductList.class);
            startActivity(intent);
        });
    }

    // Método para inicializar y actualizar la lista de productos pendientes
    private void initializePendingProductList() {
        pendingProducts.clear();

        // Solo añadir productos que están pendientes
        for (Product product : database.productList) {
            if (product.isState()) {
                pendingProducts.add(product);
            }
        }

        // Crear y configurar el adaptador con la lista de productos pendientes
        adapter = new ProductAdapter(MainActivity.this, R.layout.item_product, pendingProducts);
        lvProducts.setAdapter(adapter);
    }


}
