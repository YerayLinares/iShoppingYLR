package com.example.ishppinglistyeraylinares.database;

import com.example.ishppinglistyeraylinares.models.Product;

import java.util.ArrayList;
import java.util.List;

public class database {
    public static List<Product> productList;
    public static List<Product> noPendingProductList;

    public static void inicializeList() {
        if (productList == null) {
            productList = new ArrayList<>();

            noPendingProductList = new ArrayList<>();


            for (int i = 0; i < 5; i++) {
                Product p2 = new Product();
                p2.setName("Producto: " + i);
                p2.setNotes("note: " + i);
                p2.setState(true);
                p2.setGluten(true);
                p2.setLactosa(true);
                productList.add(p2);
            }
        }
    }
}
