package com.github.kubatatami.expandablelistviewtest.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Kuba on 19/11/15.
 */
@EFragment
public class OkDialogFragment extends DialogFragment {

    @FragmentArg
    protected String groupName;

    @FragmentArg
    protected String itemName;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(groupName)
                .setMessage(itemName)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
    }
}
