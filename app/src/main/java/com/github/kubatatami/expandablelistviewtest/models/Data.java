package com.github.kubatatami.expandablelistviewtest.models;

import java.util.List;

/**
 * Created by Kuba on 19/11/15.
 */
public class Data {

    private List<Item> items;

    private List<Group> groups;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
