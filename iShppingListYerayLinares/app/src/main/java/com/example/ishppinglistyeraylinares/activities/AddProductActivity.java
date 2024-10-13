package com.example.ishppinglistyeraylinares.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishppinglistyeraylinares.R;
import com.example.ishppinglistyeraylinares.database.database;
import com.example.ishppinglistyeraylinares.models.Product;

public class AddProductActivity extends AppCompatActivity {

    private EditText addname, addnotes;
    private Switch addswitch;
    private Button btnAdd, btnAddCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addname = findViewById(R.id.AddName);
        addnotes = findViewById(R.id.AddNotes);
        addswitch = findViewById(R.id.AddSwitch);
        btnAdd = findViewById(R.id.btnAdd);
        btnAddCancel = findViewById(R.id.btnAddCancel);

        // Botón para añadir producto
        btnAdd.setOnClickListener(v -> {
            String name = addname.getText().toString();
            String notes = addnotes.getText().toString();
            boolean isPending = addswitch.isChecked();

            if (!name.isEmpty() && !notes.isEmpty()) {
                // Crear un nuevo producto
                Product newProduct = new Product();
                newProduct.setName(name);
                newProduct.setNotes(notes);
                newProduct.setState(isPending);

                //condición para que si el producto está pendiente añadirlo a la lista de pendientes
                //y si no añadirlo a la lista de no pendientes
                if (isPending) {
                    database.productList.add(newProduct);
                } else {
                    database.noPendingProductList.add(newProduct);
                }

                // Devuelve el resultado y cierra la actividad actual
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newProduct", newProduct);
                setResult(RESULT_OK, resultIntent);

                Intent mainIntent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(mainIntent);

                finish();
            } else {
                Toast.makeText(AddProductActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            }
        });


        // Botón para cancelar
        btnAddCancel.setOnClickListener(v -> finish());
    }
}
