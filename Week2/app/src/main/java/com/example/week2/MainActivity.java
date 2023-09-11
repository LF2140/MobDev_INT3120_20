package com.example.week2;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.week2.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private Double total = (double) 0;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.donateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cal_total();
            }
        });

        // Set up a key listener on the EditText field to listen for "enter" button presses
        binding.amountOfDonate.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                return handleKeyEvent(view, keyCode);
            }
        });

        //do the number_picker
        NumberPicker numberPicker = findViewById(R.id.number_picker);
        if (numberPicker != null) {
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(1000);
            numberPicker.setWrapSelectorWheel(true);
        }
    }

    private View.OnClickListener Donate_Button = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            cal_total();
        }
    };
    private void cal_total() {
        EditText etext = (EditText) findViewById(R.id.amount_of_donate);
        String str = etext.getText().toString();
        if (str.length()>0)
        {
            Double value = Double.parseDouble(str);
            total += value;
        }
        display_donate(total);
    }

    private void display_donate(Double money) {
        String formattedmoney = NumberFormat.getCurrencyInstance().format(money);
        binding.donateResult.setText(getString(R.string.total_so_far, formattedmoney));
        binding.progressBar.setProgress(money.intValue());
    }
    private Boolean handleKeyEvent(View view, int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;
        }
        return false;
    }
}