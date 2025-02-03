package com.example.anagramapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anagramapp.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final String KEY_INPUT_TEXT = "input_text";
    private static final String KEY_FILTER_TEXT = "filter_text";
    private static final String KEY_RESULT_TEXT = "result_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.anagramInput.addTextChangedListener(anagramTextWatcher);
        binding.anagramFilter.addTextChangedListener(anagramTextWatcher);

        if (savedInstanceState != null) {
            binding.anagramInput.setText(savedInstanceState.getString(KEY_INPUT_TEXT, ""));
            binding.anagramFilter.setText(savedInstanceState.getString(KEY_FILTER_TEXT, ""));
            binding.anagramResult.setText(savedInstanceState.getString(KEY_RESULT_TEXT, ""));
        }
    }

    // TextWatcher to update the result in real-time
    private final TextWatcher anagramTextWatcher = new TextWatcher() {
        private String previousInput = "";
        private String previousFilter = "";

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Not used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String currentInput = Objects.requireNonNull(binding.anagramInput.getText()).toString();
            String currentFilter = Objects.requireNonNull(binding.anagramFilter.getText()).toString();

            // Only update if input or filter has changed
            if (!currentInput.equals(previousInput) || !currentFilter.equals(previousFilter)) {
                updateAnagram();
                previousInput = currentInput;
                previousFilter = currentFilter;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Not used
        }
    };

    private void updateAnagram() {
        String input = binding.anagramInput.getText() != null ? binding.anagramInput.getText().toString() : "";
        String filter = binding.anagramFilter.getText() != null ? binding.anagramFilter.getText().toString() : "";
        String result = Anagram.build(input, filter);
        binding.anagramResult.setText(result);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_INPUT_TEXT, binding.anagramInput.getText() != null ? binding.anagramInput.getText().toString() : "");
        outState.putString(KEY_FILTER_TEXT, binding.anagramFilter.getText() != null ? binding.anagramFilter.getText().toString() : "");
        outState.putString(KEY_RESULT_TEXT, binding.anagramResult.getText().toString());
    }
}
