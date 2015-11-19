package com.github.kubatatami.expandablelistviewtest.beans;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kubatatami.expandablelistviewtest.models.Data;
import com.github.kubatatami.expandablelistviewtest.models.Group;
import com.github.kubatatami.expandablelistviewtest.models.Item;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kuba on 19/11/15.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ProductsBean {

    private final static String DATA_FILE_PATH = "data/data.json";

    @RootContext
    protected Context context;

    private Data data;

    public Data getProducts() {
        if (data == null) {
            try {
                InputStream inputStream = context.getAssets().open(DATA_FILE_PATH);
                ObjectMapper objectMapper = new ObjectMapper();
                data = objectMapper.readValue(inputStream, Data.class);
                inputStream.close();
                prepareData(data);
            } catch (IOException e) {
                throw new RuntimeException("Can't load data.json");
            }
        }
        return data;
    }

    private void prepareData(Data data) {
        Map<Long, Group> result = new HashMap<>(data.getGroups().size());
        for (Group group : data.getGroups()) {
            result.put(group.getId(), group);
        }
        for (Item item : data.getItems()) {
            result.get(item.getGroupId()).addItem(item);
        }
    }
}