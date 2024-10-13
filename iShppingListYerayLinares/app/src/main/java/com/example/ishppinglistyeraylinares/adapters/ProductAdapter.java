package com.example.ishppinglistyeraylinares.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ishppinglistyeraylinares.R;
import com.example.ishppinglistyeraylinares.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private List<Product> products;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.products = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product p = this.products.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        TextView tvProductName = convertView.findViewById(R.id.tvName);


        tvProductName.setText(p.getName());





        return convertView;
    }
}
