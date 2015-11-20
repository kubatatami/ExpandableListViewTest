package com.github.kubatatami.expandablelistviewtest.activities;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.github.kubatatami.expandablelistviewtest.R;
import com.github.kubatatami.expandablelistviewtest.adapters.ProductsAdapter;
import com.github.kubatatami.expandablelistviewtest.beans.ProductsBean;
import com.github.kubatatami.expandablelistviewtest.dialogs.OkDialogFragment;
import com.github.kubatatami.expandablelistviewtest.dialogs.OkDialogFragment_;
import com.github.kubatatami.expandablelistviewtest.models.Data;
import com.github.kubatatami.expandablelistviewtest.models.Group;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.expandable_list_view)
    protected ExpandableListView expandableListView;

    @Bean
    protected ProductsBean productsBean;

    private ProductsAdapter adapter;

    @ItemLongClick(R.id.expandable_list_view)
    protected void onItemLongClick(int position){
        long packagePosition = expandableListView.getExpandableListPosition(position);
        int groupPosition = ExpandableListView.getPackedPositionGroup(packagePosition);
        int childPosition = ExpandableListView.getPackedPositionChild(packagePosition);

        if (childPosition != -1) {
            showProductDialog(groupPosition, childPosition);
        } else {
            Group group = adapter.getGroup(groupPosition);
            float degree = group.getId() % 2 == 0 ? 360f : -360f;
            expandableListView.animate().rotationBy(degree).setDuration(1000).start();
        }
    }

    @AfterViews
    @Background
    protected void initLoad() {
        initAdapter(productsBean.getProducts());
    }

    @UiThread
    protected void initAdapter(Data data) {
        adapter = new ProductsAdapter(this, data);
        expandableListView.setAdapter(adapter);
    }

    private void showProductDialog(int groupPosition, int childPosition) {
        Group group = adapter.getGroup(groupPosition);
        OkDialogFragment dialog = OkDialogFragment_.builder()
                .groupName(group.getName())
                .itemName(group.getItems().get(childPosition).getName()).build();
        dialog.show(getSupportFragmentManager(), "OkDialog");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
