package com.github.kubatatami.expandablelistviewtest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 19/11/15.
 */
public class Group {

    private long id;

    private String name;

    private List<Item> items = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
