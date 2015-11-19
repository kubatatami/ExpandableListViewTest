package com.github.kubatatami.expandablelistviewtest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Kuba on 19/11/15.
 */
public class Item {

    private long id;

    private String name;

    @JsonProperty("group_id")
    private long groupId;

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

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
