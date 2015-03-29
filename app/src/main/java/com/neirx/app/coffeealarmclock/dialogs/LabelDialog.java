package com.neirx.app.coffeealarmclock.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.neirx.app.coffeealarmclock.R;

public class LabelDialog extends DialogFragment implements View.OnClickListener {
    EditText etInput;

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
        switch (v.getId()) {
            case R.id.btnOk:
                String newLabel = etInput.getText().toString();
                Toast.makeText(getActivity(), newLabel, Toast.LENGTH_SHORT).show();
                //tvLabel.setText(newLabel);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
