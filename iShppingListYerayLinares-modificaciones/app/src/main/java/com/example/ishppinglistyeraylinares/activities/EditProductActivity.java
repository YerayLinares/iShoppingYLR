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

public class EditProductActivity extends AppCompatActivity {

    private EditText etProductName, etProductNotes;
    private Button save, cancel;
    private Switch state, gluten, lactosa;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gluten = findViewById(R.id.glutenEdit);
        lactosa = findViewById(R.id.lactosaEdit);
        etProductName = findViewById(R.id.EtName);
        etProductNotes = findViewById(R.id.EtNotes);
        state = findViewById(R.id.stateEdit);
        save = findViewById(R.id.BtnSave);
        cancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");

        if (product != null) {
            // Mostrar la información del producto para editar el producto
            etProductName.setText(product.getName());
            etProductNotes.setText(product.getNotes());
            state.setChecked(product.isState());
            gluten.setChecked(product.isGluten());
            lactosa.setChecked(product.isLactosa());
        }

        save.setOnClickListener(v -> {
            String newName = etProductName.getText().toString();
            String newNotes = etProductNotes.getText().toString();
            boolean newState = state.isChecked();  // Estado del switch
            boolean newGluten = gluten.isChecked();
            boolean newLactosa = lactosa.isChecked();

            if (!newName.isEmpty() && !newNotes.isEmpty()) {
                // Actualizar los detalles del producto
                product.setName(newName);
                product.setNotes(newNotes);
                product.setState(newState);
                product.setGluten(newGluten);
                product.setLactosa(newLactosa);

                for (int i = 0; i < database.productList.size(); i++) {
                    if (database.productList.get(i).getId().equals(product.getId())) {
                        database.productList.set(i, product);  // Actualiza el producto en la lista
                        break;
                    }
                }

                if (newState) {
                    //condición donde si el producto está pendiente moverlo a la lista de pendientes
                    if (database.noPendingProductList.contains(product)) {
                        database.noPendingProductList.remove(product);  // Eliminar de no pendientes
                    }
                    if (!database.productList.contains(product)) {
                        database.productList.add(product);
                    }
                } else {
                    // condicion donde si el producto no esta pendiente moverlo a la lista de no pendientes
                    if (database.productList.contains(product)) {
                        database.productList.remove(product);
                    }
                    if (!database.noPendingProductList.contains(product)) {
                        database.noPendingProductList.add(product);
                    }
                }




                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedProduct", product);
                setResult(RESULT_OK, resultIntent);

                Intent mainIntent = new Intent(EditProductActivity.this, MainActivity.class);
                startActivity(mainIntent);

                finish();
            } else {
                Toast.makeText(EditProductActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            }
        });

        //boton para cancelar y volver atras
        cancel.setOnClickListener(v -> {
            Intent back = new Intent(EditProductActivity.this, MainActivity.class);
            startActivity(back);
        });
    }
}
