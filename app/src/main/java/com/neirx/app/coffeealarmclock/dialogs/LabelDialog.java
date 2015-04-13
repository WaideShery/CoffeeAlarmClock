package com.neirx.app.coffeealarmclock.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.neirx.app.coffeealarmclock.R;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;

public class LabelDialog extends DialogFragment implements View.OnClickListener {
    OnLabelSetListener mCallback;
    EditText etInput;
    String curLabel;
    static LabelDialog ld;

    public interface OnLabelSetListener {

        void onTimeSet(String Label);
    }

    public LabelDialog() {
    }

    public static LabelDialog newInstance(OnLabelSetListener callback, String curLabel) {
        if(ld == null) {
            ld = new LabelDialog();
        }
        ld.mCallback = callback;
        ld.curLabel = curLabel;
        return ld;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.dialog_label, null);
        v.findViewById(R.id.btnOk).setOnClickListener(this);
        v.findViewById(R.id.btnCancel).setOnClickListener(this);
        etInput = (EditText) v.findViewById(R.id.etInputLabel);
        return v;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btnOk:
                String newLabel = etInput.getText().toString();
                mCallback.onTimeSet(newLabel);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
