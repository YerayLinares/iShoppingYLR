package com.example.ishppinglistyeraylinares.models;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable
{

    private String id;

    private String Name;

    private String notes;

    private boolean state;

    public Product() {
        super();
        this.id = UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isState() {
        return state;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", notes='" + notes + '\'' +
                ", state=" + state +
                '}';
    }
}
