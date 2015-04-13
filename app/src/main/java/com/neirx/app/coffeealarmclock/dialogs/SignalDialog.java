package com.neirx.app.coffeealarmclock.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.neirx.app.coffeealarmclock.R;

public class SignalDialog extends DialogFragment implements View.OnClickListener {
    public enum Signals{Standart("Стандартный"), Music("Музыка");
        private String signal;
        private Signals(String signal) {
            this.signal = signal;
        }
        @Override
        public String toString() {
            return signal;
        }
    }
    OnSignalSetListener mCallback;
    static SignalDialog signalDialog;
    Signals defaultSignal;

    public interface OnSignalSetListener {

        void onSignalSet(Signals signal);
    }

    public SignalDialog(){
    }

    public static SignalDialog newInstance(OnSignalSetListener callback, Signals signal) {
        if(signalDialog == null) {
            signalDialog = new SignalDialog();
        }
        signalDialog.mCallback = callback;
        signalDialog.defaultSignal = signal;
        return signalDialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.dialog_signal, null);
        v.findViewById(R.id.rbMusic).setOnClickListener(this);
        v.findViewById(R.id.rbStandart).setOnClickListener(this);
        v.findViewById(R.id.btnCancel).setOnClickListener(this);
        RadioGroup rgSignals = (RadioGroup) v.findViewById(R.id.rgSignals);
        switch (defaultSignal){
            case Standart:
                rgSignals.check(R.id.rbStandart);
                break;
            case Music:
                rgSignals.check(R.id.rbMusic);
                break;
        }
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbMusic:
                mCallback.onSignalSet(Signals.Music);
                dismiss();
                break;
            case R.id.rbStandart:
                mCallback.onSignalSet(Signals.Standart);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
