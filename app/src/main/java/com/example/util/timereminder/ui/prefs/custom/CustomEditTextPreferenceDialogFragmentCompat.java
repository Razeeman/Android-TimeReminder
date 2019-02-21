package com.example.util.timereminder.ui.prefs.custom;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.preference.EditTextPreferenceDialogFragmentCompat;

public class CustomEditTextPreferenceDialogFragmentCompat
        extends EditTextPreferenceDialogFragmentCompat {

    public static CustomEditTextPreferenceDialogFragmentCompat newInstance(String key) {
        final CustomEditTextPreferenceDialogFragmentCompat
                fragment = new CustomEditTextPreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        EditText editText = view.findViewById(android.R.id.edit);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
}
